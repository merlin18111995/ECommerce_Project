package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.AccountRegPage_POM;
import pageObjects.CheckoutPage_POM;
import pageObjects.HomePage_POM;
import pageObjects.LoginPage_POM;
import pageObjects.MyAccountPage_POM;
import pageObjects.ProductPage_POM;
import pageObjects.SearchPage_POM;
import pageObjects.ViewCartPage_POM;
import testBase.BaseClass;

public class TC06_EndToEndTest extends BaseClass{
	
	
	@Test(priority=2)
	void test_EndToEnd() {
		
		SoftAssert myassert=new SoftAssert();
		
		logger.info("***Starting TC06_EndToEndTest - test_EndToEnd***");
		
		try {
		HomePage_POM hp= new HomePage_POM(driver);
		
		hp.click_MyAcct();
		logger.info("Clicked on the MyAcct link");
		hp.click_Register();
		logger.info("Clicked on the Register link");
		
		AccountRegPage_POM actreg=new AccountRegPage_POM(driver);
		
		logger.info("Providing customer data to create an account");
		actreg.set_FName("FName01");
		actreg.set_LName("Lname01");
		actreg.set_Email(rb.getString("login_Email"));
		actreg.set_telephonenum("9876543210");
		actreg.set_Password(rb.getString("login_Password"));
		actreg.set_confirmPassword(rb.getString("login_Password"));
		actreg.click_Agree();
		actreg.click_Continue();
		logger.info("Clicked on the Continue button");
		Thread.sleep(2000);
		
		String confirmmsg=actreg.get_ConfirmationMsg();
		logger.info("Validating the successmessage after account creation");
		
		myassert.assertEquals(confirmmsg, "Your Account Has Been Created!","Titles are not matching");
		logger.info("Success message validated"); 
		
		hp.click_Logout();
		logger.info("Clicked on the Logout link");
		hp.click_MyAcct();
		hp.click_Login();
		logger.info("Clicked on the Login link");
				
		LoginPage_POM lp= new LoginPage_POM(driver);
		
		logger.info("Providing details to login");
		lp.set_LoginEmail(rb.getString("login_Email"));
		lp.set_LoginPassword(rb.getString("login_Password"));
		lp.click_Login();
			
		MyAccountPage_POM mp=new MyAccountPage_POM(driver);
		
		logger.info("Verifying whether login successful or not");
		Assert.assertEquals(mp.is_MyAccntPageExist(), true,"Heading not matching in My Account page");
		logger.info("Logged in successfully");
			
		logger.info("Searching for a product");
		hp.search_Prod(rb.getString("prod"));
		hp.click_Search();
		
		SearchPage_POM sp= new SearchPage_POM(driver);
		
		logger.info("Validating whether search is successful or not");
		String exp_heading="Search - "+rb.getString("prod");
		Assert.assertEquals(sp.get_heading_SearchPage(), exp_heading,"heading not matching in search page");
		logger.info("Search product is successful");
		
		boolean status=sp.isProductExists(rb.getString("prod_Name"));
		if(status==true) {
			sp.select_product(rb.getString("prod_Name"));
			logger.info("clicked on the product");
		}
		
		ProductPage_POM pm = new ProductPage_POM(driver);
				
		String prdName_PrdPage=pm.validate_prdPageHeading();
		
		boolean addproduct_status=false;
		String orginal_price="";
		if(prdName_PrdPage.contains(rb.getString("prod_Name"))) {
			logger.info("Navigated to the product page & filling details");
			orginal_price=pm.get_originalPrice();
			pm.clear_QuantityBox();
			pm.set_Quantity(rb.getString("quantity"));
			pm.click_addToCart();
			
			logger.info("Validing whether success message displayed after adding product to cart");
			addproduct_status=pm.is_successMsgDisplayed();
			myassert.assertEquals(addproduct_status, true, "success message not displayed after adding product to cart");			
		}
			String exp_totalPrice=pm.get_expectedTotalPrice(rb.getString("quantity"));
		
			Thread.sleep(3000);
			//if(addproduct_status==true) {
				logger.info("Product added successfully to the cart & message also displayed");
				pm.click_cart();
				pm.click_viewcart();
				logger.info("Clicked on Cart-->View cart");
					
				ViewCartPage_POM vp= new ViewCartPage_POM(driver);
				
				logger.info("Validating product details displayed on the view cart page");
				
				System.out.println("Expected product name="+rb.getString("prod_Name")+" , "+"Actual product name="+vp.validate_productName());
				System.out.println("Expected quantity="+rb.getString("quantity")+" , "+"Actual quantity="+vp.validate_qty());
				System.out.println("Expected Price="+orginal_price+" , "+"Actual price="+vp.validate_Price());
				System.out.println("Expected Total Price="+exp_totalPrice+" , "+"Actual Total price="+vp.get_actualTotalPrice());
			
				myassert.assertEquals(vp.validate_productName(), rb.getString("prod_Name"));
				myassert.assertEquals(vp.validate_qty(),rb.getString("quantity"));
				myassert.assertEquals(vp.validate_Price(), orginal_price);
				myassert.assertEquals(vp.get_actualTotalPrice(), exp_totalPrice);
				vp.click_checkout();
			//}
		
		CheckoutPage_POM cp=new CheckoutPage_POM(driver);
		
		String exp_heading_checkoutpage="Checkout";
		logger.info("validating whether navigated to checkout page or not");
		Assert.assertEquals(cp.get_heading_checkoutpage(), exp_heading_checkoutpage,"heading not matching in checkout page");		
		logger.info("navigated to checkout page");
		
		if(cp.is_BillingDetSectionEnabled().equals("false")) {
			logger.info("Billing Details Section is not Enabled");
			cp.click_BillingDetSection();
			logger.info("Billing Details Section is Enabled now");
		}
				
		if(cp.is_newAddressOptionPresent()==true) {
			cp.click_newAddressOption();
		}
		else {
			System.out.println("Option to select existing and new address is not visible");
		}
		
		logger.info("Entering data in Billing Details Section");
		cp.set_fName("fname01");
		cp.set_lname("lname01");
		cp.set_address1("Flat no: 101, Bldg:05");
		cp.set_city("Banglore");
		cp.set_postCode("689512");
		cp.set_Country("India");
		cp.set_Region("Karnataka");
		cp.click_Continue();
		logger.info("Completed entering details in Billing Details Section");
		
		if(cp.is_PaymentMethodSectionEnabled().equals("false")) {
			logger.info("Payment method Section is not Enabled");
			cp.click_BillingDetSection();
			logger.info("Payment method Section is Enabled now");
		}
		cp.set_comments("Payment added");
		cp.click_agree();
		cp.click_Continue_Payment();
		logger.info("Completed payment");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
		
		logger.info("***Finished TC06_EndToEndTest - test_EndToEnd***");
	
	
}
}
