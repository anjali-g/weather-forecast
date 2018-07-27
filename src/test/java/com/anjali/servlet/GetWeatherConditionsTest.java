package com.anjali.servlet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GetWeatherConditionsTest {

	//dependency
	GetWeatherConditionImplementation getWeatherConditionImplementation = mock(GetWeatherConditionImplementation.class);
	//class to be tested
	GetWeatherConditionsCall subject= new GetWeatherConditionsCall();;
	
	HttpServletRequest request = mock(HttpServletRequest.class);       
    HttpServletResponse response = mock(HttpServletResponse.class);
		
	@Before
	public void setUp() throws Exception {
		when(request.getParameter("latitude")).thenReturn("12.00");
        when(request.getParameter("longitude")).thenReturn("13.00");
		
	}
    
  	@Test
  	public final void testLatitudeLongitudeValidationException() throws ServletException, IOException {
  		when(request.getParameter("latitude")).thenReturn("-100.00");
  		when(request.getParameter("longitude")).thenReturn("181");
		subject.doGet(request, response);
  		verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid location");
  	}

	/**
	 * Test method for doGet method
	 */
	@Test
	public final void testDoGet() throws Exception {
		String responseData = "{\"latitude\":12,\"longitude\":13,\"timezone\":\"Africa/Lagos\",\"daily\":{\"summary\":\"Rain throughout the week, with high temperatures peaking at 94°F on Monday.\",\"icon\":\"rain\",\"data\":[{\"time\":1532559600,\"summary\":\"Rain in the morning and afternoon.\",\"icon\":\"rain\",\"sunriseTime\":1532580896,\"sunsetTime\":1532626589,\"moonPhase\":0.46,\"precipIntensity\":0.0159,\"precipIntensityMax\":0.1634,\"precipIntensityMaxTime\":1532617200,\"precipProbability\":0.51,\"precipType\":\"rain\",\"temperatureHigh\":92.95,\"temperatureHighTime\":1532610000,\"temperatureLow\":73.91,\"temperatureLowTime\":1532667600,\"apparentTemperatureHigh\":97.05,\"apparentTemperatureHighTime\":1532610000,\"apparentTemperatureLow\":75,\"apparentTemperatureLowTime\":1532667600,\"dewPoint\":67.93,\"humidity\":0.62,\"pressure\":1011.55,\"windSpeed\":7.89,\"windGust\":21.72,\"windGustTime\":1532577600,\"windBearing\":248,\"cloudCover\":0.93,\"uvIndex\":9,\"uvIndexTime\":1532606400,\"visibility\":10,\"ozone\":275.29,\"temperatureMin\":73.91,\"temperatureMinTime\":1532581200,\"temperatureMax\":92.95,\"temperatureMaxTime\":1532610000,\"apparentTemperatureMin\":75.14,\"apparentTemperatureMinTime\":1532581200,\"apparentTemperatureMax\":97.05,\"apparentTemperatureMaxTime\":1532610000},{\"time\":1532646000,\"summary\":\"Mostly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1532667309,\"sunsetTime\":1532712976,\"moonPhase\":0.49,\"precipIntensity\":0.0032,\"precipIntensityMax\":0.0345,\"precipIntensityMaxTime\":1532703600,\"precipProbability\":0.26,\"precipType\":\"rain\",\"temperatureHigh\":93.7,\"temperatureHighTime\":1532700000,\"temperatureLow\":74.01,\"temperatureLowTime\":1532754000,\"apparentTemperatureHigh\":97.5,\"apparentTemperatureHighTime\":1532696400,\"apparentTemperatureLow\":75.09,\"apparentTemperatureLowTime\":1532754000,\"dewPoint\":67.22,\"humidity\":0.59,\"pressure\":1010.96,\"windSpeed\":10.44,\"windGust\":31.51,\"windGustTime\":1532664000,\"windBearing\":247,\"cloudCover\":0.87,\"uvIndex\":10,\"uvIndexTime\":1532689200,\"visibility\":10,\"ozone\":274.93,\"temperatureMin\":73.91,\"temperatureMinTime\":1532667600,\"temperatureMax\":93.7,\"temperatureMaxTime\":1532700000,\"apparentTemperatureMin\":75,\"apparentTemperatureMinTime\":1532667600,\"apparentTemperatureMax\":97.5,\"apparentTemperatureMaxTime\":1532696400},{\"time\":1532732400,\"summary\":\"Mostly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1532753722,\"sunsetTime\":1532799363,\"moonPhase\":0.52,\"precipIntensity\":0.0095,\"precipIntensityMax\":0.0431,\"precipIntensityMaxTime\":1532772000,\"precipProbability\":0.74,\"precipType\":\"rain\",\"temperatureHigh\":87,\"temperatureHighTime\":1532786400,\"temperatureLow\":72.03,\"temperatureLowTime\":1532840400,\"apparentTemperatureHigh\":91.43,\"apparentTemperatureHighTime\":1532786400,\"apparentTemperatureLow\":72.98,\"apparentTemperatureLowTime\":1532840400,\"dewPoint\":68.54,\"humidity\":0.69,\"pressure\":1011.79,\"windSpeed\":9.9,\"windGust\":30.32,\"windGustTime\":1532746800,\"windBearing\":196,\"cloudCover\":0.87,\"uvIndex\":8,\"uvIndexTime\":1532775600,\"visibility\":10,\"ozone\":272.38,\"temperatureMin\":74.01,\"temperatureMinTime\":1532754000,\"temperatureMax\":87,\"temperatureMaxTime\":1532786400,\"apparentTemperatureMin\":75.09,\"apparentTemperatureMinTime\":1532754000,\"apparentTemperatureMax\":91.43,\"apparentTemperatureMaxTime\":1532786400},{\"time\":1532818800,\"summary\":\"Mostly cloudy until evening.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1532840135,\"sunsetTime\":1532885748,\"moonPhase\":0.55,\"precipIntensity\":0.0195,\"precipIntensityMax\":0.4661,\"precipIntensityMaxTime\":1532872800,\"precipProbability\":0.19,\"precipType\":\"rain\",\"temperatureHigh\":92.93,\"temperatureHighTime\":1532872800,\"temperatureLow\":77.4,\"temperatureLowTime\":1532926800,\"apparentTemperatureHigh\":97.17,\"apparentTemperatureHighTime\":1532872800,\"apparentTemperatureLow\":78.16,\"apparentTemperatureLowTime\":1532926800,\"dewPoint\":67.71,\"humidity\":0.63,\"pressure\":1011.56,\"windSpeed\":9.58,\"windGust\":19.85,\"windGustTime\":1532847600,\"windBearing\":217,\"cloudCover\":0.51,\"uvIndex\":10,\"uvIndexTime\":1532862000,\"visibility\":10,\"ozone\":271.31,\"temperatureMin\":72.03,\"temperatureMinTime\":1532840400,\"temperatureMax\":92.93,\"temperatureMaxTime\":1532872800,\"apparentTemperatureMin\":72.98,\"apparentTemperatureMinTime\":1532840400,\"apparentTemperatureMax\":97.17,\"apparentTemperatureMaxTime\":1532872800},{\"time\":1532905200,\"summary\":\"Partly cloudy overnight.\",\"icon\":\"partly-cloudy-night\",\"sunriseTime\":1532926547,\"sunsetTime\":1532972132,\"moonPhase\":0.58,\"precipIntensity\":0.001,\"precipIntensityMax\":0.01,\"precipIntensityMaxTime\":1532930400,\"precipProbability\":0.07,\"precipType\":\"rain\",\"temperatureHigh\":93.72,\"temperatureHighTime\":1532959200,\"temperatureLow\":77.28,\"temperatureLowTime\":1533013200,\"apparentTemperatureHigh\":98.04,\"apparentTemperatureHighTime\":1532959200,\"apparentTemperatureLow\":78.16,\"apparentTemperatureLowTime\":1533013200,\"dewPoint\":67.01,\"humidity\":0.55,\"pressure\":1010.61,\"windSpeed\":13.12,\"windGust\":28.06,\"windGustTime\":1532923200,\"windBearing\":241,\"cloudCover\":0.07,\"uvIndex\":14,\"uvIndexTime\":1532948400,\"visibility\":10,\"ozone\":270.32,\"temperatureMin\":77.4,\"temperatureMinTime\":1532926800,\"temperatureMax\":93.72,\"temperatureMaxTime\":1532959200,\"apparentTemperatureMin\":78.16,\"apparentTemperatureMinTime\":1532926800,\"apparentTemperatureMax\":98.04,\"apparentTemperatureMaxTime\":1532959200},{\"time\":1532991600,\"summary\":\"Rain starting in the evening.\",\"icon\":\"rain\",\"sunriseTime\":1533012959,\"sunsetTime\":1533058516,\"moonPhase\":0.61,\"precipIntensity\":0.0168,\"precipIntensityMax\":0.0712,\"precipIntensityMaxTime\":1533070800,\"precipProbability\":0.74,\"precipType\":\"rain\",\"temperatureHigh\":93.21,\"temperatureHighTime\":1533045600,\"temperatureLow\":73.49,\"temperatureLowTime\":1533088800,\"apparentTemperatureHigh\":98.02,\"apparentTemperatureHighTime\":1533042000,\"apparentTemperatureLow\":74.59,\"apparentTemperatureLowTime\":1533088800,\"dewPoint\":68.31,\"humidity\":0.6,\"pressure\":1009.62,\"windSpeed\":11.81,\"windGust\":28.81,\"windGustTime\":1532998800,\"windBearing\":248,\"cloudCover\":0.82,\"uvIndex\":8,\"uvIndexTime\":1533034800,\"visibility\":10,\"ozone\":265.49,\"temperatureMin\":77.28,\"temperatureMinTime\":1533013200,\"temperatureMax\":93.21,\"temperatureMaxTime\":1533045600,\"apparentTemperatureMin\":78.16,\"apparentTemperatureMinTime\":1533013200,\"apparentTemperatureMax\":98.02,\"apparentTemperatureMaxTime\":1533042000},{\"time\":1533078000,\"summary\":\"Rain starting in the afternoon, continuing until evening.\",\"icon\":\"rain\",\"sunriseTime\":1533099371,\"sunsetTime\":1533144899,\"moonPhase\":0.64,\"precipIntensity\":0.0246,\"precipIntensityMax\":0.1414,\"precipIntensityMaxTime\":1533124800,\"precipProbability\":0.85,\"precipType\":\"rain\",\"temperatureHigh\":85.92,\"temperatureHighTime\":1533117600,\"temperatureLow\":73.85,\"temperatureLowTime\":1533171600,\"apparentTemperatureHigh\":91.29,\"apparentTemperatureHighTime\":1533117600,\"apparentTemperatureLow\":75.03,\"apparentTemperatureLowTime\":1533171600,\"dewPoint\":70.19,\"humidity\":0.77,\"pressure\":1008.91,\"windSpeed\":13.29,\"windGust\":29.34,\"windGustTime\":1533085200,\"windBearing\":233,\"cloudCover\":0.9,\"uvIndex\":8,\"uvIndexTime\":1533121200,\"visibility\":10,\"ozone\":263.56,\"temperatureMin\":73.49,\"temperatureMinTime\":1533088800,\"temperatureMax\":85.92,\"temperatureMaxTime\":1533117600,\"apparentTemperatureMin\":74.59,\"apparentTemperatureMinTime\":1533088800,\"apparentTemperatureMax\":91.29,\"apparentTemperatureMaxTime\":1533117600},{\"time\":1533164400,\"summary\":\"Rain overnight.\",\"icon\":\"rain\",\"sunriseTime\":1533185782,\"sunsetTime\":1533231281,\"moonPhase\":0.68,\"precipIntensity\":0.0077,\"precipIntensityMax\":0.0606,\"precipIntensityMaxTime\":1533243600,\"precipProbability\":0.56,\"precipType\":\"rain\",\"temperatureHigh\":90.51,\"temperatureHighTime\":1533222000,\"temperatureLow\":74.95,\"temperatureLowTime\":1533272400,\"apparentTemperatureHigh\":95.49,\"apparentTemperatureHighTime\":1533218400,\"apparentTemperatureLow\":75.99,\"apparentTemperatureLowTime\":1533272400,\"dewPoint\":69.4,\"humidity\":0.69,\"pressure\":1009.25,\"windSpeed\":9.71,\"windGust\":23.71,\"windGustTime\":1533164400,\"windBearing\":220,\"cloudCover\":0.91,\"uvIndex\":9,\"uvIndexTime\":1533207600,\"visibility\":10,\"ozone\":263.81,\"temperatureMin\":73.85,\"temperatureMinTime\":1533171600,\"temperatureMax\":90.51,\"temperatureMaxTime\":1533222000,\"apparentTemperatureMin\":75.03,\"apparentTemperatureMinTime\":1533171600,\"apparentTemperatureMax\":95.49,\"apparentTemperatureMaxTime\":1533218400}]},\"offset\":1}";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(getWeatherConditionImplementation.getWeatherConditions(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(responseData);
		when(response.getWriter()).thenReturn(pw);
		subject.doGet(request, response);
		
		String result = sw.getBuffer().toString();
		assertEquals(result.toString()!= "", true);
		
	}

}
