package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage_POM extends BasePage {

	public MyAccountPage_POM(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='content']//h2[normalize-space()='My Account']")
	WebElement msg_Heading;
	//@FindBy(xpath="//div[@class='list-group mb-3']//a[text()='Logout']")
	//WebElement link_Logout;
	@FindBy(xpath="//*[@id=\"column-right\"]/div/a[13]")
	WebElement link_Logout;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement link_myAccnt;
	@FindBy(xpath="//a[text()='Logout']")
	WebElement link_Logout2;
	
	
	public boolean is_MyAccntPageExist() {
		try {
		boolean status=msg_Heading.isDisplayed();
		return status;
		}
		catch(Exception e) {
			return false;
		}
		}
	
	public void click_Logout() {
		link_Logout.click();
	}
	
	public void click_MyAcct() {
		link_myAccnt.click();
	}
	public void click_Logout2() {
		link_Logout2.click();
	}
	

	
}
