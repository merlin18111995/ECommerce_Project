package testCases;

import java.math.RoundingMode;  


import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

  
public class Test1 {
	static WebDriver driver;
	//static final DecimalFormat decfor = new DecimalFormat("0.00");  
	public static void main(String[] args) throws InterruptedException {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		
		String prod="mac";
		String prod_Name="MacBook";
		String quantity="2";
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(prod);	
		driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-default btn-lg']")).click();
		
		String act_heading=driver.findElement(By.xpath("//div[@id='content']//h1")).getText();
		System.out.println(act_heading);
		String exp_heading="Search - "+prod;
		Assert.assertEquals(act_heading, exp_heading,"heading not matching");
		
		boolean flag=false;		
		List<WebElement> img_products = driver.findElements(By.xpath("//div[@class='image']//img"));

		for (WebElement x : img_products) {
			System.out.println("different product are===> " + x.getAttribute("title"));
			if (prod_Name.equals(x.getAttribute("title"))) {
				flag = true;
				;
			}
		}

		if(flag==true) {
			System.out.println("flag is true");
		for (WebElement pr : img_products) {
			if (prod_Name.equals(pr.getAttribute("title"))) {
				System.out.println("selecting product:"+pr.getAttribute("title"));
				pr.click();
				break;
			}
		}
		}
		String prdnameinprdpage = driver.findElement(By.xpath("//div[@id='content']//h1")).getText();
		String exp_price=driver.findElement(By.xpath("//div[@class='col-sm-4']//ul[2]//li//h2")).getText();
		boolean status=false;
		if (prdnameinprdpage.equals(prod_Name)) {
			driver.findElement(By.xpath("//input[@name='quantity']")).clear();
			driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
			driver.findElement(By.xpath("//button[@id='button-cart']")).click();
			
			status=driver.findElement(By.xpath("//div[@id='product-product']//div[contains(@class,'alert')]")).isDisplayed();
			System.out.println("success message status:"+status);
		}
		
		if(status==true) {
			WebElement cart=driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-inverse btn-block btn-lg dropdown-toggle']"));
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", cart);
			WebElement viewcart=driver.findElement(By.xpath("//ul[@class='dropdown-menu pull-right']//p//a[1]"));
			js.executeScript("arguments[0].click();", viewcart);
			
			String act_prdName=driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[2]//a")).getText();
			System.out.println("Expected product name="+prod_Name+" , "+"Actual product name="+act_prdName);
			
			String act_quantity=driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[4]//input")).getAttribute("value");
			System.out.println("Expected quantity="+quantity+" , "+"Actual quantity="+act_quantity);
			
			String act_price=driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[5]")).getText();
			System.out.println("Expected Price="+exp_price+" , "+"Actual price="+act_price);
			
			Double exp_totPrice=(Double.parseDouble((exp_price.replace("$", ""))))*(Integer.parseInt(quantity));
			DecimalFormat toTheFormat = new DecimalFormat("#.00");
			toTheFormat.setGroupingUsed(true);
			toTheFormat.setGroupingSize(3);
	        //System.out.println("$"+toTheFormat.format(g));
						
			String act_totPrice=driver.findElement(By.xpath("//div[@class='table-responsive']//table//tbody//tr//td[6]")).getText();
			System.out.println("Expected total Price="+"$"+toTheFormat.format(exp_totPrice)+" , "+"Actual total price="+act_totPrice);
						
			driver.findElement(By.xpath("//a[text()='Checkout']")).click();
		
		
		}
					
		
		
	/*	if(flag==true) {
			for(WebElement pr:img_products) {
				if(prod_Name.equals(pr.getAttribute("title"))) {
					System.out.println("clicking on "+pr.getText());
				pr.click();
				}
			}
		}
		
		String heading=driver.findElement(By.xpath("//div[@id='product-info']//h1")).getText();
		
		System.out.println("Heading of product page is:"+heading);
		if(heading.equals(prod_Name)) {
			System.out.println("heading matching");
		}
		else {
			System.out.println("heading not matching");
		}
			*/
				
		
		
		
		
		/*String a="$241.00";
		String b="$241.99"; //241.99
		String c="$241.55";
		String h="$241.45";
		String i="$241.34";	
		double d=Double.parseDouble(i.replace("$", ""));
		
		//double g=decfor.format(d);
		System.out.println(d);
		
		decfor.setRoundingMode(RoundingMode.DOWN);  
		System.out.println("\nBefore Rounding: " + decfor.format(d)+10); */
		
		
		
		}

	

}
