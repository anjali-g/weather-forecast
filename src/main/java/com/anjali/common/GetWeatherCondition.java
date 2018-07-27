/*
* An interface implemented by GetWeatheConditionImplementation where get request is made to Dark Sky API
*/

package com.anjali.common;

public interface GetWeatherCondition {

	String getWeatherConditions(double latitude, double longitude) throws Exception;
}
