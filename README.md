# Weather Forecast

An application displaying weather forecast using [Dark Sky API](https://darksky.net/dev) and [Google Geocoding API](https://developers.google.com/maps/documentation/geocoding/start).
On application load user will be requested to provide a city name. As soon as user submits the city name, the Google Geocoding API reads the city/address and converts into geographic coordinates (like latitude and longitude). The Dark Sky Api uses these coordinates to read the weather conditions.

## Table of Contents
* [Technologies used](#technologies-used)
* [Tool and Platform used](#tool-and-platform-used)
* [Setup](#setup)
* [Project Structure](#project-structure)
* [How to Deploy](#how-to-deploy)
* [Browser Support](#browser-support)

## Technologies used
- Servlet
- Javascript
- JQuery
- JUnit
- [DarkSky API](https://darksky.net/dev/)
- [CanvasJs](https://canvasjs.com/)
- [Google Maps Geocoding API](https://developers.google.com/maps/documentation/geocoding/start)

## Tool and Platform Used
- Java 8 with Tomcat 8
   * Tomcat server is used for running application 
- Apache Maven
   * Maven is used for building the war file
- Eclipse IDE latest version
   * IDE used for developing the maven project
- AWS Elastic Beanstalk
   * Used for running and managing the web application

## Setup
* Download and install latest version of eclipse
* [Setup Apache Tomcat 8 server for locally running the application](https://javatutorial.net/run-tomcat-from-eclipse) 
* Import git repository into ecipse: [check this](http://craigmart.in/2012/01/17/import-an-existing-git-repo-to-eclipse/) 
* Add this project to the server
  * Right click server->Add and Remove->add the project->finish
* Start the server to run application locally.

## Project Structure
- Weather
  * pom.xm //Includes all the dependencies  
  * target
    * weather.war //war generate on maven build, is used to deploy application on aws elastic beanstalk  
  * src
    * main/java //contains servlet classes
      * com/anjali/common
        * [GetWeatheCondition.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/common/GetWeatherCondition.java)
      * com/anjali/servlet
        * [GetLocation.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/servlet/GetLocation.java)
        * [GetLocationCall.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/servlet/GetLocationCall.java) 
        * [GetWeatherConditionImplementation.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/servlet/GetWeatherConditionImplementation.java)
        * [GetWeatherConditionsCall.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/servlet/GetWeatherConditionsCall.java)
    * main/webapps
        * index.js //javasript file
        * index.jsp //welcome file
        * WEB-INF
            * web.xml //contains details of web-apps and servlet mappings
    * test/java //unit test files for java classes
      * com/anjali/servlet
        * [GetLocationCallTest.java](https://github.com/anjali-g/weather-forecast/blob/master/src/test/java/com/anjali/servlet/GetLocationCallTest.java)
        * [GetWeatherConditionsTest.java](https://github.com/anjali-g/weather-forecast/blob/master/src/test/java/com/anjali/servlet/GetWeatherConditionsTest.java)
        

## How to Deploy
Before starting with deployment, we need a *secret key*.
For that we need to sigin up a Dark Sky API secret key and replace it in [GetWeatherConditionImplementation.java](https://github.com/anjali-g/weather-forecast/blob/master/src/main/java/com/anjali/servlet/GetWeatherConditionImplementation.java) class.

Build application:
* Build the project using maven clean install: This will create a weather.war file under target folder
* Go to [aws](https://aws.amazon.com/) and sign up
* Open aws console home page and search for *Elastic Beanstalk*
* Create a new environment->Select Environment Tier as *Web Server Environment*->click select
* Give an available domain name, choose *tomcat* as the platform and upload the weather.war file
* Click *create environment*
* After couple of minutes you will see the link to application

## Browser Support
- Chrome
- Firefox
- Internet Explorer 10+
