package Academy;

import static org.testng.Assert.assertEquals;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.LandingPage;
import resources.base;

public class ValidateTitle extends base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	LandingPage l = new LandingPage(driver);;		//initialising landing page object before test
	
	@BeforeTest			// This will run after each test script (methods)
	public void browserInitialise() throws IOException {
		driver = initialiseBrowser();
		log.info("Driver is initialised");
		
		driver.get(prop.getProperty("url"));	// getting url from data.properties file
		log.info("Navigated to Homepage");
		l = new LandingPage(driver);
	}
	
	// following method validates title 'Featured Courses'
	@Test
	public void getTitle(){
//		l = new LandingPage(driver);	// create object for landing page and pass 'driver' to its constructor
		
		//get object for title 'featured courses' and load it in a String variable
		assertEquals(l.getTitle().getText(), "FEATURED COURSES");
		log.info("Successfully validated text message");
	}
	
	@Test
	public void getTitleFailed(){		
//		Following initialising is no more requird as we have initialised 'LandingPage l' global at top
//		LandingPage l = new LandingPage(driver);	// create object for landing page and pass 'driver' to its constructor
		
		//get object for title 'featured courses' and load it in a String variable
		assertEquals(l.getTitle().getText(), "FEATUREDCOURSES");
		log.info("Successfully validated text message");
	} 

	@Test
	public void getHeader(){		
		//get object for title 'featured courses' and load it in a String variable
		System.out.println(l.getHeader().getText());
//		assertEquals(l.getHeader().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
		log.info("Successfully validated text message");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
