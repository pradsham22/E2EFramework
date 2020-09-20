package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class base {
	
	public WebDriver driver;
	public Properties prop;			//defining global variable so that this prop can be invoked from other classes once inherited
	
	public WebDriver initialiseBrowser()  throws IOException {
		
		// we need to call data.properties attributes
		prop = new Properties(); 	// this properties class helps read properties file
//		FileInputStream fis = new FileInputStream("C:\\Users\\Pradeep\\eclipse-workspace\\E2EFramework\\src\\main\\java\\resources\\data.properties");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
//		String browserName = prop.getProperty("browser");  	//This will get browsername from data.properties file
		String browserName = System.getProperty("browser");	// Jenkins and maven can read file using System.getproperty without any dependency.
		System.out.println(browserName);
		
		//	We can initialise browser using two ways 
	 	//	1) If Else
	
		if (browserName.contains("chrome")) {		
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))		// will execute headless if browsername contians headless.
			{
				options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
		
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\BrowserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			
		} else if (browserName.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\BrowserDrivers\\operadriver.exe");
			driver = new OperaDriver();
			
		}

	/*	// 	2) Switch= case
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\BrowserDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "D:\\BrowserDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "D:\\BrowserDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		}  */
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	// Method for taking screenshots
	public String TakeSreenshots(String TestCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot sc = (TakesScreenshot)driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\reports\\FailedEvidences\\"+ TestCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationPath));
		
		return destinationPath;
		
		
	}
}
