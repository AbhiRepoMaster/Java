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

public class Radio {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/radio.xhtml");
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
	@Test (priority = 1)
	public void favoriteBrowserRadioButton() {
	    WebElement FirefoxRadioButton =
	    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Firefox']/preceding-sibling::div/div[contains(@class, 'ui-radiobutton-box')]"))); //explicit wait
	    FirefoxRadioButton.click();
	    WebElement selectedState = 
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Firefox']/preceding-sibling::div/div[contains(@class, 'ui-state-active')]")));
	    Assert.assertNotNull(selectedState, "Firefox radio button is not selected"); 
	}
	
	//@Test (priority = 1)
	public void testSelectSafariRadioButton() {
	    WebElement safariRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Safari']/preceding-sibling::div/div[contains(@class, 'ui-radiobutton-box')]")));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(safariRadioButton).click().perform();
	    WebElement selectedState = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Safari']/preceding-sibling::div/div[contains(@class, 'ui-state-active')]")));
	    Assert.assertNotNull(selectedState, "Safari radio button is not selected");
	}
	
	 @Test(priority = 2)
	    public void testUnselectRadioButton() {
	        WebElement radioButton = driver.findElement(By.xpath("//label[text()='Bengaluru']/preceding-sibling::div/div[contains(@class, 'ui-radiobutton-box')]"));

	        boolean isRadioButtonSelectedInitially = radioButton.isSelected();

	        // check if not already selected)
	        if (!isRadioButtonSelectedInitially) {
	            radioButton.click();
	        }

	        // Attempt to deselect the radio button
	        radioButton.click();

	        // Check if the radio button is still selected after attempting to deselect it
	        boolean isRadioButtonSelectedAfterDeselect = radioButton.isSelected();

	        // Assert that the radio button is not selected after attempting to deselect it
	        Assert.assertFalse(isRadioButtonSelectedAfterDeselect, "Radio button is still selected after attempting to deselect it");
	    }
		
	    
//	    @Test(priority = 2)
//	    public void testUnselectRadioButton1() {
//	        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Bengaluru']/preceding-sibling::div/div[contains(@class, 'ui-radiobutton-box')]")));
//
//	        // Select the radio button if it's not already selected
//	        if (!radioButton.isSelected()) {
//	            radioButton.click();
//	        }
//
//	        // Wait for 5 seconds
//	        try {
//	            Thread.sleep(5000);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//
//	        // Unselect the radio button
//	        radioButton.click();
//
//	        // Check if the radio button is unselected
//	        boolean isRadioButtonSelected = radioButton.isSelected();
//	        Assert.assertFalse(isRadioButtonSelected, "Radio button is still selected after attempting to deselect it");
//	    }

	    
	    @Test(priority = 2)
	    public void testUnselectRadioButton1() {
	        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Bengaluru']/preceding-sibling::div/div[contains(@class, 'ui-radiobutton-box')]")));

	        // Select the radio button if it's not already selected
	        if (!radioButton.isSelected()) {
	            radioButton.click();
	            System.out.println("Selected");
	        } else {
	            System.out.println("Already selected");
	        }

	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Unselect the radio button if it's selected
	        if (radioButton.isSelected()) {
	            radioButton.click();
	            System.out.println("Unselected");
	        } else {
	            System.out.println("Already unselected");
	        }

	        // Check if the radio button is unselected
	        boolean isRadioButtonSelected = radioButton.isSelected();
	        Assert.assertFalse(isRadioButtonSelected, "Radio button is still selected after attempting to deselect it");
	    }


	    @Test
	    public void testDefaultSelectedRadioButton() {
	        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));

	        WebElement defaultSelectedRadioButton = null;
	        for (WebElement radioButton : radioButtons) {
	            if (radioButton.isSelected()) {
	                defaultSelectedRadioButton = radioButton;
	                break;
	            }
	        }
	        if (defaultSelectedRadioButton != null) {
	            System.out.println("Default selected radio button: " + defaultSelectedRadioButton.getAttribute("id"));
	        } else {
	            System.out.println("No radio button is selected by default.");
	        }
	        Assert.assertNotNull(defaultSelectedRadioButton, "No radio button is selected by default.");
	    }
	    
//need work on it
//	    @Test
//	    public void testSelectAgeGroup() {
//	        // Find all radio buttons for age group
//	        List<WebElement> radioButtons = driver.findElements(By.xpath("//div[@id='j_idt87:age']//input[@type='radio']"));
//
//	        // Iterate through radio buttons
//	        for (WebElement radioButton : radioButtons) {
//	            // Wait for the radio button to be clickable
//	            wait.until(ExpectedConditions.elementToBeClickable(radioButton));
//	            
//	            // Check if the radio button is not selected
//	            if (!radioButton.isSelected()) {
//	                // Select the radio button
//	                radioButton.click();
//	                System.out.println("Selected age group: " + radioButton.getAttribute("value"));
//	            }
//	        }
//	    }
	    
	    
	    
	@AfterClass
   public void driverClose()
  {
	  driver.quit();
  }

}
