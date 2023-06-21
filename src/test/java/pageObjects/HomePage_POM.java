package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage_POM extends BasePage {
	public HomePage_POM(WebDriver driver) {
		super(driver);    //super keyword can invoke parent class constructor, and parent class constructor is expecting a parameter
	}

	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement link_myAccnt;
	@FindBy(linkText="Register")
	WebElement link_register;
	@FindBy(linkText="Login")
	WebElement link_login;
	
	public void click_MyAcct() {
		link_myAccnt.click();
	}
	
	public void click_Register() {
		link_register.click();
	}
	
	public void click_Login() {
		link_login.click();
	}
	
	
	
}
