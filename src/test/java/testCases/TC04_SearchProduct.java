package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage_POM;
import pageObjects.SearchPage_POM;
import testBase.BaseClass;

public class TC04_SearchProduct extends BaseClass{
	
	@Test
	void searchProd_Test() {
		logger.info("***Starting TC04_SearchProduct***");
		
		try {
		HomePage_POM hp=new HomePage_POM(driver);
		hp.search_Prod(rb.getString("prod"));
		hp.click_Search();
		
		SearchPage_POM sp= new SearchPage_POM(driver);
		String exp_heading="Search - "+rb.getString("prod");
		Assert.assertEquals(sp.get_heading_SearchPage(), exp_heading,"heading not matching");
		}
		catch(Exception e) {
			Assert.fail();
			logger.info("Heading not matching");
		}
		
		logger.info("***Finished TC04_SearchProduct***");
	}
	
}
