package com.anjali.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

class GetLocation {

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
	private URL getUrl(String city) throws MalformedURLException {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city;
		return new URL(url);
	}

	protected String getLocation(String city) throws Exception {
		try {
			URL requestUrl = getUrl(city);
			
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
