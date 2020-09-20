package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.base;

public class LoginHome extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	LandingPage l;
	
	@BeforeTest		// This will run after each test script (methods)
	public void browserInitialise() throws IOException {
		driver = initialiseBrowser();
		driver.get(prop.getProperty("url"));	// getting url from data.properties file
		l = new LandingPage(driver);	// create object for landing page and pass 'driver' to its constructor
		l.login().click();
	}

	@Test(dataProvider = "getData")
	public void LoginPage(String username, String password) throws IOException, InterruptedException{
//		driver = initialiseBrowser();
//		driver.get(prop.getProperty("url"));	// getting url from data.properties file
		//above usrnanme and passwword input is only for DataProvider
		
		
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getSubmmit().click();
		log.info("Logged in with " + username);
	}
	
	@AfterTest		// run after every method (test script)
	public void teardown() {
		driver.close();
	}

		
	@DataProvider
	public Object[][] getData() 
	{
		Object[][] obj = new Object[3][2];		//	create 3 rows and 2 columns array for user name & password
		
		//	1st user
		obj[0][0] = "user1@gmail.com";
		obj[0][1] = "User1";
		
		//	2nd user
		obj[1][0] = "user2@gmail.com";
		obj[1][1] = "User2";
		
		//	2nd user
		obj[2][0] = "user3@gmail.com";
		obj[2][1] = "User3";	
		
		return obj;
	}
}
