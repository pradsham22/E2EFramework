 package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
	//	creating a constructor to initialise driver 
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	private By login = By.cssSelector("a[href*='sign_in']");
	private By title = By.xpath("//h2[contains(text(),'Featured Courses')]");
	private By navBar = By.xpath("//nav[@class='navbar-collapse collapse']");	// this is xpath for navigation bar at top
	private By header = By.cssSelector("div[class*='video'] h3");
	
	
	

	public WebElement login() {
		return driver.findElement(login);
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getNavBar() {
		return driver.findElement(navBar);
	}
	
	public WebElement getHeader() {
		return driver.findElement(header);
	}
	
	/*	 using Page Factory
	@FindBy(css = "a[href*='sign_in']")
	WebElement login;
	
	public WebElement login() {
		return login;
	}	*/
	
}
