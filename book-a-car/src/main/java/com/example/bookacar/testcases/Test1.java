package com.example.bookacar.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1 {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
	  @Test
	    public void testRedirectToWebsite() throws InterruptedException {
	        driver.get("https://leafground.com/input.xhtml");
	        String expectedURL = "https://leafground.com/input.xhtml";
	        String actualURL = driver.getCurrentUrl();
	        if ("Input Components".equalsIgnoreCase(driver.getTitle()) && expectedURL.equals(actualURL)) {
	            System.out.println("Redirected to website successfully");
	        } else {
	            System.out.println("Launch failure");
	        }
    		List<WebElement> test = driver.findElements(By.xpath("//input[@id='j_idt88:j_idt91']"));
    		test.get(0).sendKeys("TN");
    		Thread.sleep(3000);
    		System.out.println("Size:" + test.size());
	    }
	  
	  @AfterClass
	  public void driverClose()
	  {
		  driver.quit();
	  }

}
