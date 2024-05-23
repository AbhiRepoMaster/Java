package com.example.bookacar.testcases;

import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
    
    public void CheckboxNotificationCheck() {
        WebElement checkboxDiv = driver.findElement(By.xpath("//div[@id='j_idt87:j_idt91']//div[contains(@class,'ui-chkbox-box')]"));
        checkboxDiv.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-growl-message")));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='j_idt87:msg_container']//div[contains(@class,'ui-growl-item-container')]")));
        WebElement toastTitle = toastMessage.findElement(By.xpath(".//span[@class='ui-growl-title']"));
        String toastText = toastTitle.getText();
        Assert.assertEquals(toastText, "Checked", "The toast message does not contain the expected text 'Checked'");
    }
    
    @Test (priority=3)
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
  
    @Test (priority=4)
    public void testClickElement() {
        WebElement element = driver.findElement(By.xpath("//*[@id='j_idt87:ajaxTriState']/div[2]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        while (true) {
            element.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-message")));

            String toastTitle = driver.findElement(By.className("ui-growl-title")).getText();
            String toastContent = driver.findElement(By.tagName("p")).getText().trim(); 
            System.out.println("Toast Message: " + toastTitle + " - " + toastContent);
            if (toastContent.equals("State = 0")) {
                System.out.println("Test Success! Found State = 0. Breaking loop.");
                break;
            } else {
                System.out.println("Continuing loop... Toast content: " + toastContent);
                System.out.println("Content length: " + toastContent.length());
            }

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-growl-message")));
        }

        System.out.println("Exited loop, test complete.");
    }

   @Test (priority=5)
    public void testToggleSwitch() {
        WebElement toggleKey = driver.findElement(By.xpath("//div[@class='ui-toggleswitch-slider']"));

        // Check if toggle switch is initially unchecked
        String ariaChecked = toggleKey.getAttribute("aria-checked");
        if ("true".equals(ariaChecked)) {
            // If toggle switch is already checked, click again to uncheck it
            toggleKey.click();
        }

        // Click on the toggle switch to check it
        toggleKey.click();
        try {
            WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='j_idt87:msg_container']//div[contains(@class,'ui-growl-item-container')]")));
             WebElement toastTitle = toastMessage.findElement(By.xpath(".//span[@class='ui-growl-title']"));
            String toastText = toastTitle.getText();
            Assert.assertEquals(toastText, "Checked", "The toast message does not contain the expected text 'Checked'");
        } catch (Exception e) {
            Assert.fail("Toast message did not appear after toggling the switch");
        }
    }
    
    @Test (priority=6)
    public void CheckboxDisabled() {
        
        WebElement checkbox = driver.findElement(By.id("j_idt87:j_idt102_input"));

        boolean isDisabled = checkbox.getAttribute("disabled") != null;

        Assert.assertTrue(isDisabled, "Checkbox is not disabled");
    }

  @Test (priority=7)
    public void MultiSelectCheckboxes() {
        try {
            WebElement citiesDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@data-label='Cities']")));
            citiesDropdown.click();
            List<String> citiesToSelect = Arrays.asList("London", "Paris", "Rome");
            for (String city : citiesToSelect) {
                selectCity(city);
            }
            
            List<String> selectedCities = Arrays.asList("London", "Paris", "Rome");
            boolean allSelected = selectedCities.stream().allMatch(this::isCitySelected);
            
            if (allSelected) {
                System.out.println("London, Paris, and Rome are selected.");
            } else {
                System.out.println("London, Paris, and Rome are not selected.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void selectCity(String cityName) {
        WebElement cityCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'" + cityName + "')]/preceding-sibling::div/div[contains(@class,'ui-chkbox-box')]")));
        cityCheckbox.click();
    }

    private boolean isCitySelected(String cityName) {
        WebElement cityCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'" + cityName + "')]/preceding-sibling::div/div[contains(@class,'ui-chkbox-box')]")));
        String isChecked = cityCheckbox.findElement(By.xpath("..//input")).getAttribute("aria-checked");
        return "true".equals(isChecked);
    }


	@AfterClass
	   public void driverClose()
	  {
		  driver.quit();
	  }

	}
