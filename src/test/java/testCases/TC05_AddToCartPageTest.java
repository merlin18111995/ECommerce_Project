package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage_POM;
import pageObjects.ProductPage_POM;
import pageObjects.SearchPage_POM;
import testBase.BaseClass;

public class TC05_AddToCartPageTest extends BaseClass{
		@Test
		void test_addToCart() {
			try {
				logger.info("***Starting TC05_AddToCartPageTest***");
				
				HomePage_POM hp= new HomePage_POM(driver);
							
				hp.search_Prod(rb.getString("prod"));
				hp.click_Search();
				
				SearchPage_POM sp=new SearchPage_POM(driver);
				
				boolean status=sp.isProductExists(rb.getString("prod_Name"));
				if(status==true) {
					sp.select_product(rb.getString("prod_Name"));
									}
				
				ProductPage_POM pm = new ProductPage_POM(driver);
				String prdName_PrdPage=pm.validate_prdPageHeading();
				Assert.assertEquals(prdName_PrdPage, rb.getString("prod_Name"),"Products are not matching");
				
				if(prdName_PrdPage.equals(rb.getString("prod_Name"))) {
					pm.clear_QuantityBox();
					pm.set_Quantity("2");
					pm.click_addToCart();
					Assert.assertTrue(pm.is_successMsgDisplayed());
				}
				}
				catch(Exception e) {
					Assert.fail();
				}
				
				logger.info("***Finished TC05_AddToCartPageTest***");
		}
}
