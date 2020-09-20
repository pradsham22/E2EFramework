package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	//	creating a constructor to initialise driver 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Creating objects for locators 
	
	private By email = By.xpath("//input[@id='user_email']");
	private By passwd = By.xpath("//input[@id='user_password']");
	private By login = By.xpath("//input[@name='commit']");
	
	//creating methods for above locators 
	public WebElement getEmail() {				// enter email address
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {				// enter password 
		return driver.findElement(passwd);
	}
	
	public WebElement getSubmmit() {				// login button
		return driver.findElement(login);
	}
	

	
	
	
	/*	 using Page Factory
	@FindBy(css = "a[href*='sign_in']")
	WebElement login;
	
	public WebElement login() {
		return login;
	}	*/
	
}
