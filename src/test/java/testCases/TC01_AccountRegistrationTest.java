package testCases;


import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegPage_POM;
import pageObjects.HomePage_POM;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass {
//since BaseClass is the parent, this class can access all methods and var. And testng identifies all the tags from parent &child, executes
	
	@Test(groups = {"Regression","Master"})
	void test_accnt_reg() throws InterruptedException {
		try {
			
			logger.debug("Application logs....");
			logger.info("***Starting TC01_AccountRegistrationTest***");
		HomePage_POM hp= new HomePage_POM(driver);
		
		hp.click_MyAcct();
		logger.info("Clicked on the MyAcct link");
		hp.click_Register();
		logger.info("Clicked on the Register link");
		
		AccountRegPage_POM actreg=new AccountRegPage_POM(driver);
		
		logger.info("Providing customer data");
		actreg.set_FName("FName01");
		actreg.set_LName("Lname01");
		actreg.set_Email("test0623@gmail.com");
		actreg.set_telephonenum("9876543210");
		actreg.set_Password("Test@0623");
		actreg.set_confirmPassword("Test@0623");
		actreg.click_Agree();
		actreg.click_Continue();
		logger.info("Clicked on the Continue");
		Thread.sleep(3000);
		
		String confirmmsg=actreg.get_ConfirmationMsg();
		logger.info("Verifying the title of the page");
		
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!","Titles are not matching");
		}
		catch(Exception e) {
			Assert.fail();
			logger.error("Test failed");
		}
		logger.info("***Finished TC01_AccountRegistrationTest***");
		
	
		
	}
	
}
