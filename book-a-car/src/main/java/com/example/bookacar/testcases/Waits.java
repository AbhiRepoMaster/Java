package com.example.bookacar.testcases;

import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/waits.xhtml");
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
//	@Test (priority = 1)
//	public void testButtonVisibilityAfterClick() {
//	    // Assuming driver is initialized and navigated to the page
//
//	    // Locate and click the initial button
//	    WebElement initialButton = driver.findElement(By.xpath("//button[@id='j_idt87:j_idt89']//span[@class='ui-button-text ui-c'][normalize-space()='Click']"));
//	    initialButton.click();
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    WebElement newButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='I am here']")));
//
//	    // Verify the text on the new button
//	    String buttonText = newButton.getText();
//	    Assert.assertEquals(buttonText, "I am here", "Text on the new button is not as expected.");
//	}
	
	
	@Test (priority = 1)
	public void testButtonVisibilityAfterClick() {
	    WebElement initialButton = driver.findElement(By.xpath("//button[@id='j_idt87:j_idt89']//span[@class='ui-button-text ui-c'][normalize-space()='Click']"));
	    String initialButtonText = initialButton.getText(); 
	    initialButton.click();

	    // Start timing
	    long startTime = System.currentTimeMillis();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement newButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='I am here']")));

	    // Duration waited in milliseconds
	    long endTime = System.currentTimeMillis();
	    long duration = endTime - startTime; 

	    
	    String newButtonText = newButton.getText();

	    System.out.println("Text on 1st button: " + initialButtonText);
	    System.out.println("Text on 2nd button: " + newButtonText);
	    System.out.println("Waited for: " + duration + " milliseconds");

	    Assert.assertEquals(newButtonText, "I am here", "Text on the new button is not as expected.");
	}


	
	@Test (priority = 2)
	
	public void testButtonInvisibilityAfterClick() {
	    WebElement triggerButton = driver.findElement(By.xpath("//button[@id='j_idt87:j_idt92']//span[@class='ui-button-text ui-c'][normalize-space()='Click']"));
	    triggerButton.click();

	    long startTime = System.currentTimeMillis();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    boolean isButtonInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='I am about to hide']")));

	    long endTime = System.currentTimeMillis();
	    long duration = endTime - startTime; 

	    System.out.println("Waited for button to be invisible for: " + duration + " milliseconds");

	    Assert.assertTrue(isButtonInvisible, "Button did not become invisible within the specified time.");
	}


	@Test (priority = 4)
	
	public void testButtonTextChangedAfterClick() {
	    WebElement clickButton = driver.findElement(By.xpath("//button[@id='j_idt87:j_idt98']//span[@class='ui-button-text ui-c'][normalize-space()='Click']"));
	    clickButton.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement secondButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='I am going to change!']")));
	    Assert.assertNotNull(secondButton, "Second button text did not change to 'I am going to change!'");
	    String buttonText = secondButton.getText();
	    System.out.println("Text on second button after 10 seconds: " + buttonText);
	    WebElement thirdButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Did you notice?']")));
	    Assert.assertNotNull(thirdButton, "Third button text did not change to 'Did you notice?'");
	    String newText = thirdButton.getText();
	    System.out.println("Text on third button after text change: " + newText);
	}


	
	
	
@AfterClass
   public void driverClose()
  {
	  driver.quit();
  }

}
