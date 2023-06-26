package testCases;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage_POM;
import pageObjects.ProductPage_POM;
import pageObjects.SearchPage_POM;
import testBase.BaseClass;

public class Demo {
	public String check() {
		String exp_price="$602.19";
		Double d=Double.parseDouble((exp_price.replace("$", "")));
		
		DecimalFormat toTheFormat = new DecimalFormat("#.00");
		toTheFormat.setGroupingUsed(true);
		toTheFormat.setGroupingSize(3);
        System.out.println("$"+toTheFormat.format(d));
        return (toTheFormat.format(d));
}
	
	@Test
	void addtocart() throws InterruptedException {
		
		String quantity="2";
		Demo dm=new Demo();
		//(dm.check())*(Integer.parseInt(quantity));
		/*String exp_price="$602.19";
		
		Double d=(Double.parseDouble((exp_price.replace("$", ""))))*(Integer.parseInt(quantity));
		System.out.println(d);
		String exp_totPrice="$"+(Double.parseDouble((exp_price.replace("$", ""))))*(Integer.parseInt(quantity));
		System.out.println(exp_totPrice);
		
		NumberFormat myFormat = NumberFormat.getInstance(); 
		myFormat.setGroupingUsed(true); 
		//System.out.println(myFormat.format(d));
		System.out.println("$"+myFormat.format(d));
		
		DecimalFormat decimalFormat = new DecimalFormat("#.##");	
		decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        System.out.println("$"+decimalFormat.format(d));
        
        float g=(Float.parseFloat((exp_price.replace("$", ""))))*(Integer.parseInt(quantity));
		System.out.println(g);
		String exp_totPrice2="$"+(Float.parseFloat((exp_price.replace("$", ""))))*(Integer.parseInt(quantity));
		System.out.println(exp_totPrice2);
		
		DecimalFormat toTheFormat = new DecimalFormat("#.00");
		toTheFormat.setGroupingUsed(true);
		toTheFormat.setGroupingSize(3);
        System.out.println("$"+toTheFormat.format(g));
        
        System.out.println("$"+toTheFormat.format(d));
		
		//Expected total Price=1204.0 , Actual total price=$1,204.00*/
		
	}
}
		
	


