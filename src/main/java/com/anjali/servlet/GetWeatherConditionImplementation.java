package com.anjali.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import com.anjali.common.GetWeatherCondition;

public class GetWeatherConditionImplementation implements GetWeatherCondition{

	// declaring a constant key and initializing with the secret Key
	private static final String key = "277e4cd6917c4b96f781beec1bf7ca45";

	//read response data and store into a String
	private String readResponseData(HttpURLConnection connection)
			throws UnsupportedEncodingException, IOException {
		
		StringBuilder responseData = new StringBuilder();
		String line = "";
		
		BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		while ((line = data.readLine()) != null) {
			responseData.append(line);
		}
		
		return responseData.toString();
		
	}
	
	// generate api url using key, latitude and longitude
	private URL getUrl(double latitude, double longitude) throws MalformedURLException {
		String url = "https://api.darksky.net/forecast/" 
					+ key + "/" + latitude + "," + longitude
					+"?exclude=currently,minutely,hourly,flags";
		return new URL(url);
	}


	public String getWeatherConditions(double latitude, double longitude) throws Exception{
		try {
			URL requestUrl = getUrl(latitude, longitude);
			
			HttpURLConnection connection;
			connection = (HttpURLConnection) requestUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if(connection.getResponseCode() != HttpServletResponse.SC_OK) {
				throw new Exception("Failed to fetch weather details " + connection.getResponseCode());
			}
			return readResponseData(connection);
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}
	}

}
