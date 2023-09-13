package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints java class to perform CRUD requests on user api

public class UserEndPoints 
{
	public static Response createUsers(User payload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	public static Response getUsers(String userName)
	{
		Response res = given()
			.pathParam("username",userName)
		
		.when()
			.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUsers(User payload, String userName)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username",userName)
		
		.when()
			.put(Routes.put_url);
		
		return res;
	}
	public static Response deleteUsers(String userName)
	{
		Response res = given()
			.pathParam("username",userName)
		
		.when()
			.delete(Routes.delete_url);
		
		return res;
	}
	
}
