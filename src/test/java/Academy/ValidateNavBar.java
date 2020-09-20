package Academy;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.LandingPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import resources.base;

public class ValidateNavBar extends base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest	// This will run after each test script (methods)
	public void browserInitialise() throws IOException {
		driver = initialiseBrowser();
		driver.get(prop.getProperty("url"));	// getting url from data.properties file
	}
	
	// following method validates title 'Featured Courses'
	@Test
	public void getNavBar(){
		LandingPage l = new LandingPage(driver);	// create object for landing page and pass 'driver' to its constructor
		assertTrue(l.getNavBar().isDisplayed());
		log.info("NavBar tested");
	}
	
	// @AfterMethod: This will run after each test script (methods)
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
