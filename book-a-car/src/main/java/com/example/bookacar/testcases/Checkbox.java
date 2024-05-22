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

public class Checkbox {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/checkbox.xhtml");
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
	@Test (priority=1)
	
	public void testCheckboxAndSpanElement() throws InterruptedException {
        WebElement checkboxDiv = driver.findElement(By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
        checkboxDiv.click();
        WebElement checkboxIcon = checkboxDiv.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-icon ui-c ui-icon-check']"));
        boolean isChecked = checkboxIcon.isDisplayed();
        Assert.assertTrue(isChecked, "Checkbox is not checked");
    }
	
	
    @Test (priority=2)
    
    public void Notification() {
        WebElement checkboxDiv = driver.findElement(By.xpath("//div[@id='j_idt87:j_idt91']//div[contains(@class,'ui-chkbox-box')]"));
        checkboxDiv.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='j_idt87:msg_container']//div[contains(@class,'ui-growl-item-container')]")));
        WebElement toastTitle = toastMessage.findElement(By.xpath(".//span[@class='ui-growl-title']"));
        String toastText = toastTitle.getText();
        Assert.assertEquals(toastText, "Checked", "The toast message does not contain the expected text 'Checked'");
    }
	    
	
	@AfterClass
	   public void driverClose()
	  {
		  driver.quit();
	  }

	}
