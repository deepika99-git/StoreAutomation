package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayLoad;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayLoad = new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		//for logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********Before Creating User********");
		Response response= UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********After Creating User********");
	}

	@Test(priority=2)
	public void testGetUser()
	{
		Response response= UserEndPoints.getUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		Response response= UserEndPoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response resaftupdate = UserEndPoints.getUser(this.userPayLoad.getUsername());
		Assert.assertEquals(resaftupdate.getStatusCode(),200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response response= UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
