package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 
{
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		
		faker = new Faker();
		userPayload = new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser()
	{
		
		logger.info("-------Creating User------------");
		Response response = UserEndPoints2.createUsers(userPayload);
		
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("Post User--------------");
		logger.info("-------User is Created------------");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	
	{
		logger.info("-------Getting User------------");
		Response response = UserEndPoints2.getUsers(this.userPayload.getUsername());
		
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		System.out.println("Get User--------------");
		logger.info("-------Geted User------------");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("-------Updating User------------");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		
		Response response = UserEndPoints2.updateUsers(userPayload,this.userPayload.getUsername());
		
		response.then().log().body().statusCode(200); //use this assertion(this is restassured assertion)
		
		//OR
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200); //or this one both are same(this is testng assertion)
		
		//Checking data after update
		Response responseAfterUpdate = UserEndPoints2.getUsers(this.userPayload.getUsername());
		
		logger.info("-------Updated User Info------------");
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		System.out.println("Update User--------------");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("-------Deleting User------------");
		Response response = UserEndPoints2.deleteUsers(this.userPayload.getUsername());
		
		logger.info("-------User deleted------------");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		System.out.println("Delete User--------------");
	}
}
