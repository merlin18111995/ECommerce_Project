package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage_POM;
import pageObjects.LoginPage_POM;
import pageObjects.MyAccountPage_POM;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	void test_Login() {

		try {
		logger.info("***Starting TC02_LoginTest***");
		
		HomePage_POM hp= new HomePage_POM(driver);
		hp.click_MyAcct();
		logger.info("Clicked on My Account link");
		hp.click_Login();
		logger.info("Clicked on Login link");
		
		LoginPage_POM lp= new LoginPage_POM(driver);
		lp.set_LoginEmail(rb.getString("login_Email"));
		lp.set_LoginPassword(rb.getString("login_Password"));
		lp.click_Login();
		logger.info("Clicked on Login button");
				
		MyAccountPage_POM mp=new MyAccountPage_POM(driver);
		
		Assert.assertEquals(mp.is_MyAccntPageExist(), true,"Heading not matching");
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***Finished TC02_LoginTest***");
		
	}
}
