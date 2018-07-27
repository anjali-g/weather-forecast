/**
 * 
 */
$(document).ready(function() {  
	var images = [];
	var chart;
	
	function getImageName(name) {
		switch(name) {
			case "cloudy":
				return "03d.png";
			case "rain":
				return "10d.png";
			case "sunny":
				return "01d.png";
			case "clear-day":
				return "01d.png";
			case "clear-night":
				return "01n.png";
			case "snow":
				return "13d.png";
			case "sleet":
				return "50d.png";
			case "wind":
				return "50d.png";
			case "fog":
				return "50d.png";
			case "partly-cloudy-day":
				return "02d.png";
			case "partly-cloudy-night":
				return "02n.png";
		}
	}
	function addImages(chart) {
		var url = "http://openweathermap.org/img/w/";
		for(var i = 0; i < chart.data[0].dataPoints.length; i++){
			var dataPointsName = chart.data[0].dataPoints[i].name;
			images.push($("<img>").attr("src", url + getImageName(dataPointsName)));
			images[i].attr("class", dataPointsName).appendTo($("#weatherChart>.canvasjs-chart-container"));
			positionImage(images[i], i);
		}
	}

	function positionImage(image, index) {
		var imageCenter = chart.axisX[0].convertValueToPixel(chart.data[0].dataPoints[index].x);
		var imageTop =  chart.axisY[0].convertValueToPixel(chart.axisY[0].maximum);

		image.width("40px")
		.css({ "left": imageCenter - 20 + "px",
		"position": "absolute","top":imageTop + "px",
		"position": "absolute"});
	}

	$( window ).resize(function() {
		var cloudyCounter = 0, rainyCounter = 0, sunnyCounter = 0;    
		var imageCenter = 0;
		for(var i=0;i<chart.data[0].dataPoints.length;i++) {
			imageCenter = chart.axisX[0].convertValueToPixel(chart.data[0].dataPoints[i].x) - 20;
			if(chart.data[0].dataPoints[i].name == "cloudy") {					
				$(".cloudy").eq(cloudyCounter++).css({ "left": imageCenter});
			} else if(chart.data[0].dataPoints[i].name == "rainy") {
				$(".rainy").eq(rainyCounter++).css({ "left": imageCenter});  
			} else if(chart.data[0].dataPoints[i].name == "sunny") {
				$(".sunny").eq(sunnyCounter++).css({ "left": imageCenter});  
			}                
		}
	});
	
	//formatter for graph legend tooltip
	function formatter(indexLabel) { 
		if(indexLabel.index === 0 && indexLabel.dataPoint.x === 0) {
			return " Min " + indexLabel.dataPoint.y[indexLabel.index];
		} else if(indexLabel.index == 1 && indexLabel.dataPoint.x === 0) {
			return " Max " + indexLabel.dataPoint.y[indexLabel.index];
		} else{
			return indexLabel.dataPoint.y[indexLabel.index];
		}
	}
	
	function getDayFromTime(time) {
		//converting time from sec to millisec
		var date = new Date(time*1000);
		//getting the date
		date = date.toDateString();
		date  = date.split(" ");
		//reading the day and returning 
		var day = date[0] + ", " + date[1] + " " + date[2] + ", " + date[3];
		return day;
	}
	
	/*function setting data from api response to 
	 * dataPoint object for displaying in the graph */ 
	function getData(data) {
		var dataPoint = [];
		for(var i=0; i<8; i++) {
			dataPoint.push({
				label: getDayFromTime(data[i].time),
				y: [data[i].temperatureMin, data[i].temperatureMax],
				name: data[i].icon
			});
		}
		return dataPoint;
	}
	
	//creating weather graph using canvasJS
	function createGraph(responseJson) {
		var dataPoints = getData(responseJson.data);
		chart = new CanvasJS.Chart("weatherChart", {
			animationEnabled: true,
			axisY: {
				title: "Temperature in Fahrenheit",
				maximum: 120,
				includeZero: false,
				gridThickness: 0
			},
			axisX: {
				title: "Day"
			},
			toolTip:{
				shared: true,
				content: "{name} </br> <strong>Temperature in Fahrenheit: </strong> </br> Min: {y[0]}, Max: {y[1]}"
			},
			data: [{
				type: "rangeSplineArea",
				fillOpacity: 0.1,
				color: "#91AAB1",
				indexLabelFormatter: formatter,
				dataPoints: dataPoints
			}]
		});
		chart.render();

		addImages(chart);
	}
	
	/*function calling the GetWeatherConditions 
	*servlet class where api call is made
	*/
	function getWeatherForLocation(location) {
		var latitude = location.lat;
		var longitude = location.lng;
		$.get('GetWeatherConditions/',{latitude:latitude,longitude:longitude},
				function(responseText) { 
			var responseJson = JSON.parse(responseText).daily;
			createGraph(responseJson);
		});
	}
	
	function validateCity(city) {
		//validation for city input
		if(city === "") {
    		$('#errorId').text("Invalid Location");
    		$('#weatherChart').hide();
    		return false;
		} else {
			$('#weatherChart').show();
			$('#errorId').text("");
			return true;
		}
	}
	
	/*submit action defined.
	 * reading the input city and fetching its location
	*/
    $('#btnSubmit').click(function(event) {
    	var city = $('#city').val();
    	if(validateCity(city)) {
    		//fetching location object for the city which has latitude and longitude
    		$.get('GetLocation/',{city:city},
    				function(responseText) { 
    			var location = JSON.parse(responseText).results[0].geometry.location;
    			getWeatherForLocation(location);
    		});
    	}
    	
    });
    
});