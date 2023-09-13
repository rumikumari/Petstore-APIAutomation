package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints java class to perform CRUD requests on user api

public class UserEndPoints2 
{
	//Method created for getting urls from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load the properties file and by default resourceBundle will take the path of routes file
		return routes;
	}
	
	public static Response createUsers(User payload)
	{
		String postURL = getURL().getString("post_url"); //getURL is returning the routes and from that routes we are getting the string - "post_url"
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(postURL);
		
		return res;
	}
	
	public static Response getUsers(String userName)
	{
		String getURL = getURL().getString("get_url");
		
		Response res = given()
			.pathParam("username",userName)
		
		.when()
			.get(getURL);
		
		return res;
	}
	
	public static Response updateUsers(User payload, String userName)
	{
		String updateURL = getURL().getString("update_url");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username",userName)
		
		.when()
			.put(updateURL);
		
		return res;
	}
	public static Response deleteUsers(String userName)
	{
		String deleteURL = getURL().getString("delete_url");
		
		Response res = given()
			.pathParam("username",userName)
		
		.when()
			.delete(deleteURL);
		
		return res;
	}
	
}
