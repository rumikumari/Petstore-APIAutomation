package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.utilities.DataProviders;
import io.restassured.response.Response;
import api.payload.*;
import api.endpoints.UserEndPoints;

public class DataDrivenTest 
{
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id, String userName, String fname, String lname, String email, String pwd, String phone)
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.createUsers(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserbyName(String userName)
	{
		Response response = UserEndPoints.deleteUsers(userName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
