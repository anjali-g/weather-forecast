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

public class GetLocationCallTest {

	//dependency
	GetLocation getLocation = mock(GetLocation.class);
	//class to be tested
	GetLocationCall subject= new GetLocationCall();;
	
	HttpServletRequest request = mock(HttpServletRequest.class);       
    HttpServletResponse response = mock(HttpServletResponse.class);
		
	@Before
	public void setUp() throws Exception {
		when(request.getParameter("city")).thenReturn("Seattle");
		
	}
    /*
     * Test for validating user input value for CITY
     */
  	@Test
  	public final void testCityValidationException() throws ServletException, IOException {
  		when(request.getParameter("city")).thenReturn("");
		subject.doGet(request, response);
  		verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid city");
  	}
 
	/**
	 * Test method for doGet method
	 */
	@Test
	public final void testDoGet() throws Exception {
		String responseData = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"Seattle\",               \"short_name\" : \"Seattle\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"King County\",               \"short_name\" : \"King County\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"Washington\",               \"short_name\" : \"WA\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"United States\",               \"short_name\" : \"US\",               \"types\" : [ \"country\", \"political\" ]            }         ],         \"formatted_address\" : \"Seattle, WA, USA\",         \"geometry\" : {            \"bounds\" : {               \"northeast\" : {                  \"lat\" : 47.734145,                  \"lng\" : -122.2244331               },               \"southwest\" : {                  \"lat\" : 47.4919119,                  \"lng\" : -122.4596959               }            },            \"location\" : {               \"lat\" : 47.6062095,               \"lng\" : -122.3320708            },            \"location_type\" : \"APPROXIMATE\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 47.734145,                  \"lng\" : -122.2244331               },               \"southwest\" : {                  \"lat\" : 47.4919119,                  \"lng\" : -122.4596959               }            }         },         \"place_id\" : \"ChIJVTPokywQkFQRmtVEaUZlJRA\",         \"types\" : [ \"locality\", \"political\" ]      }   ],   \"status\" : \"OK\"}";
		String city = "Seattle";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(getLocation.getLocation(city)).thenReturn(responseData);
		when(response.getWriter()).thenReturn(pw);
		subject.doGet(request, response);
		
		String result = sw.getBuffer().toString();
		assertEquals(result.substring(0), responseData);
		
	}

}
