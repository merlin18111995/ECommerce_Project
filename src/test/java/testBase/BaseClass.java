package testBase;

import java.io.File;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver; //in ExtentReportManager class, we r creating an obj for baseclass under onTestFailure(), so there will be 2
									//drivers created, to avoid that mark this driver as static
	public Logger logger;
	public ResourceBundle rb;
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setUp(String br) {
	//	public void setUp() {	
		logger=LogManager.getLogger(this.getClass());
		rb=ResourceBundle.getBundle("config");
		
	//	ChromeOptions opt=new ChromeOptions();
	//	opt.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"}); //this will disable 'chrome being automated' msg from browser
		
	//	WebDriverManager.chromedriver().setup();  //from 4.6.0 selenium version this is not required&we can remove dependency from po.xml
		if(br.equals("edge")) {
		driver=new EdgeDriver(); 
		}
		else if (br.equals("chrome")) {
			driver=new ChromeDriver(); 
			}
		
		//driver=new ChromeDriver(opt);  //we need to pass options ref value here
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(rb.getString("AppURL"));
		
		
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	} 
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	public String randomNumber() {
		String generatedString2=RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	public String randomAlphaNumeric() {
		String a=RandomStringUtils.randomAlphabetic(3);
		String n=RandomStringUtils.randomNumeric(2);
		return (a+n);
	}
	
}
