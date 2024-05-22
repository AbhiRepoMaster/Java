package com.example.bookacar.testcases;

import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
//	@Test (priority=1)
	
	public void testCheckboxAndSpanElement() throws InterruptedException {
        WebElement checkboxDiv = driver.findElement(By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
        checkboxDiv.click();
        WebElement checkboxIcon = checkboxDiv.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-icon ui-c ui-icon-check']"));
        boolean isChecked = checkboxIcon.isDisplayed();
        Assert.assertTrue(isChecked, "Checkbox is not checked");
    }
	
	
//    @Test (priority=2)
    
    public void CheckboxNotificationCheck() {
        WebElement checkboxDiv = driver.findElement(By.xpath("//div[@id='j_idt87:j_idt91']//div[contains(@class,'ui-chkbox-box')]"));
        checkboxDiv.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='j_idt87:msg_container']//div[contains(@class,'ui-growl-item-container')]")));
        WebElement toastTitle = toastMessage.findElement(By.xpath(".//span[@class='ui-growl-title']"));
        String toastText = toastTitle.getText();
        Assert.assertEquals(toastText, "Checked", "The toast message does not contain the expected text 'Checked'");
    }
    
//    @Test (priority=3)
    public void testSelectCourses() {
        // Get the table containing the checkboxes
        WebElement table = driver.findElement(By.id("j_idt87:basic"));

        // Get all rows in the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Variables to store the checkboxes for Java and JavaScript
        WebElement javaCheckbox = null;
        WebElement jsCheckbox = null;

        // Iterate over rows to find "Java" and "JavaScript"
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                WebElement label = column.findElement(By.tagName("label"));
                String labelText = label.getText();
                if (labelText.equalsIgnoreCase("Java")) {
                    javaCheckbox = column.findElement(By.cssSelector("div.ui-chkbox-box"));
                } else if (labelText.equalsIgnoreCase("Javascript")) {
                    jsCheckbox = column.findElement(By.cssSelector("div.ui-chkbox-box"));
                }
            }
        }

        // Click Java checkbox if not already selected
        if (javaCheckbox != null && !javaCheckbox.findElement(By.tagName("span")).getAttribute("class").contains("ui-icon-check")) {
            javaCheckbox.click();
        }

        // Click JavaScript checkbox if not already selected
        if (jsCheckbox != null && !jsCheckbox.findElement(By.tagName("span")).getAttribute("class").contains("ui-icon-check")) {
            jsCheckbox.click();
        }

        // Verify that both checkboxes are selected
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement javaInput = driver.findElement(By.cssSelector("input[value='java']"));
        WebElement jsInput = driver.findElement(By.cssSelector("input[value='js']"));

        wait.until(ExpectedConditions.attributeToBe(javaInput, "aria-checked", "true"));
        wait.until(ExpectedConditions.attributeToBe(jsInput, "aria-checked", "true"));

        Assert.assertEquals(javaInput.getAttribute("aria-checked"), "true", "Java checkbox is not selected");
        Assert.assertEquals(jsInput.getAttribute("aria-checked"), "true", "JavaScript checkbox is not selected");
    }
  
//    @Test
//    public void testToggleSwitchTurnedOn() {
//        // Find the toggle switch element
//        WebElement toggleSwitch = driver.findElement(By.id("j_idt87:j_idt100"));
//
//        // Check if the toggle switch is turned on by examining its classes and aria-checked attribute
//        boolean isTurnedOn = toggleSwitch.getAttribute("class").contains("ui-toggleswitch-checked");
//        boolean ariaChecked = "true".equals(toggleSwitch.findElement(By.tagName("input")).getAttribute("aria-checked"));
//
//        // Assert that the toggle switch is turned on
//        Assert.assertTrue(isTurnedOn && ariaChecked, "Toggle switch is not turned on");
//    }


    
   // @Test
    public void testCheckboxDisabled() {
        
        WebElement checkbox = driver.findElement(By.id("j_idt87:j_idt102_input"));

        boolean isDisabled = checkbox.getAttribute("disabled") != null;

        Assert.assertTrue(isDisabled, "Checkbox is not disabled");
    }

    @Test
    public void testSelectMultipleOptions() {
        // Click on the component that triggers the appearance of the multiselect box
        WebElement multiselectBox = driver.findElement(By.xpath("//ul[@data-label='Cities']"));
        multiselectBox.click();

        // Wait for the multiselect box items to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement multiselectBoxPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-selectcheckboxmenu-items-wrapper")));

        // Find the checkboxes for "London" and "Paris" and select them
        WebElement londonCheckbox = multiselectBoxPanel.findElement(By.xpath("//label[text()='London']/preceding-sibling::div/input[@type='checkbox']"));
        WebElement parisCheckbox = multiselectBoxPanel.findElement(By.xpath("//label[text()='Paris']/preceding-sibling::div/input[@type='checkbox']"));
        londonCheckbox.click();
        parisCheckbox.click();

        // Verify that "London" and "Paris" checkboxes are selected
        Assert.assertTrue(londonCheckbox.isSelected(), "London checkbox is not selected");
        Assert.assertTrue(parisCheckbox.isSelected(), "Paris checkbox is not selected");
    }



	
	//@AfterClass
	   public void driverClose()
	  {
		  driver.quit();
	  }

	}
