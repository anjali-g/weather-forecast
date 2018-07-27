/*
 * Reads location of a city i.e. latitude and longitude;
 * Passes the parameters to GetWeatherConditionImplementation;
 * Finally send backs to the index.js file the weather conditions
 */
package com.anjali.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetWeatherConditionsCall extends HttpServlet {
	
	GetWeatherConditionImplementation getWeatherConditionImplementation = new GetWeatherConditionImplementation();

	private static final long serialVersionUID = 1L;

	//send response back to client
	private void sendResponseData(String responseData, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(responseData);
	}

	/*
	 * calling darkSky forecast API to fetch current weather conditions for the next
	 * week
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// reading latitude and longitude from request
		double latitude = Double.parseDouble(req.getParameter("latitude"));
		double longitude = Double.parseDouble(req.getParameter("longitude"));
		
		if(validateLatitude(latitude) && validateLongitude(longitude)) {
			String responseData;
			try {
				responseData = getWeatherConditionImplementation.getWeatherConditions(latitude, longitude);
				sendResponseData(responseData, resp);
			} catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid location");
		}
	}

	private boolean validateLongitude(double longitude) {
		//validation for longitude
		return longitude >= -180 && longitude <= 180;
	}

	private boolean validateLatitude(double latitude) {
		//validation for latitude
		return latitude >= -90 && latitude <= 90;	
	}

}
