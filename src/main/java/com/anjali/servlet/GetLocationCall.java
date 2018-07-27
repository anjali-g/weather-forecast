/*
* Reads user input for city from the request parameters and passes it to GetLocation class
* GetLocation returns geographic coordinates which this class returns to client
*/
package com.anjali.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLocationCall extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	GetLocation getLocationInstance = new GetLocation();

	/*
	 * reads user input for city and passes to GetLocation class 
	 * which returns city location
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// reading city from request
		String city = req.getParameter("city");
		
		if(validate(city)) {
			String responseData;
			try {
				responseData = getLocationInstance.getLocation(city);
				
				resp.setContentType("text/plain");  
				resp.setCharacterEncoding("UTF-8"); 
				resp.getWriter().write(responseData);
			} catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid city");
		}
	}

	//Validating city value
	private boolean validate(String city) {
		return city != "";
	}

}
