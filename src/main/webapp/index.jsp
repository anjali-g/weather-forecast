<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Weather Conditions</title>

<!-- Including CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>  
	<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
</head>
<body>
	<script type="text/javascript" src="index.js"></script>
	<div class="container-fluid" >
		<div class="form-group" style="text-align:center;padding:50px 5px">
			<h2>Weather Forecast</h2>
		    <form id="frmUser">
				<label for="city">Enter City:</label>
				<input type="text" id="city" required="true" />
				<input type="button" id="btnSubmit"	value="Submit" />
			</form>	
			<p id="errorId"></p>
 		</div>
 		<div id="formattedLocation" style="text-align:center"></div>
	
 	</div>
 	<div id="weatherChart" style="height: 300px; width: 100%;"></div>
</body>
</html>