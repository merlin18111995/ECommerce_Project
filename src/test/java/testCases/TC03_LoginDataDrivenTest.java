package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage_POM;
import pageObjects.LoginPage_POM;
import pageObjects.MyAccountPage_POM;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class) //since the dataprovider is in another class &package, 
																			// we have to mention the class
	void test_LoginDD(String mail, String password, String exp) { //these data comes from the excel-->array-->method
		
			logger.info("***Starting TC03_LoginDataDrivenTest***");
			
			try {
			HomePage_POM hp= new HomePage_POM(driver);
			
			hp.click_MyAcct();
			logger.info("Clicked on My Account link");
			hp.click_Login();
			logger.info("Clicked on Login link");
			
			LoginPage_POM lp= new LoginPage_POM(driver);
			
			lp.set_LoginEmail(mail);  //data comes from dataprovider array
			lp.set_LoginPassword(password);
			lp.click_Login();
			logger.info("Clicked on Login button");
					
			MyAccountPage_POM mp=new MyAccountPage_POM(driver);
			boolean tgtpage_status=mp.is_MyAccntPageExist();
						
			if(exp.equals("Valid")) {
				if(tgtpage_status==true) { //this means login is successful with valid data
					mp.click_MyAcct();
					mp.click_Logout2();
					Assert.assertTrue(true);
				}
				else { //this means login is not successful with valid data
					Assert.assertTrue(false);
				}
			}
			if(exp.equals("InValid")) { 
				if((tgtpage_status==true)) { //this means login is successful with invalid data
					mp.click_MyAcct();
					mp.click_Logout2();
					Assert.assertTrue(false);
				}
				else {	//this means login is not successful with invalid data
					Assert.assertTrue(true); 
				}
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
			
			logger.info("***Finished TC03_LoginDataDrivenTest***");
	}
	
	
}
