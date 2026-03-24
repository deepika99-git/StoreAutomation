package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String email, String pw,String pn)
	{
		User userPayLoad = new User();
		userPayLoad.setId(Integer.parseInt(userID));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstName(fname);
		userPayLoad.setLastName(lname);
		userPayLoad.setEmail(email);
		userPayLoad.setPassword(pw);
		userPayLoad.setPhone(pn);
		
		Response response= UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByNames(String userName)
	{
		Response response= UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
