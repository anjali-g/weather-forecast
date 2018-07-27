/* GetLocation class returns location
 * of a CITY.
 * */
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

	//read response data and return in String format
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
	
	// generate url for fetching location of a city
	private URL getUrl(String city) throws MalformedURLException {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city;
		return new URL(url);
	}

	//get call to googleapis for fetching city location
	protected String getLocation(String city) throws Exception {
		try {
			URL requestUrl = getUrl(city);
			
			HttpURLConnection connection;
			connection = (HttpURLConnection) requestUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if(connection.getResponseCode() != HttpServletResponse.SC_OK) {
				throw new Exception("Failed to fetch city location " + connection.getResponseCode());
			}
			return readResponseData(connection);
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}
	}

}
