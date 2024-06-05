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

public class Select {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/select.xhtml");

    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
	
	@Test
    public void selectTool() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 

        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='ui-selectonemenu']")));
            System.out.println("Dropdown found.");

            dropdown.click();
            System.out.println("Dropdown clicked.");

            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@class='ui-selectonemenu']/option")));
            System.out.println("Number of options found: " + options.size());

            boolean seleniumFound = false;
            for (WebElement option : options) {
                if (option.getText().equals("Selenium")) {
                    option.click();
                    seleniumFound = true;
                    break;
                }
            }

            Assert.assertTrue(seleniumFound, "Option 'Selenium' not found in the dropdown.");
            System.out.println("Option 'Selenium' selected.");

            String selectedOptionText = dropdown.getAttribute("value");
            System.out.println("Selected option: " + selectedOptionText);
            Assert.assertEquals(selectedOptionText, "Selenium", "Selected option is not as expected.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

	
	 @Test
	    public void selectCountry() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	        try {
	            WebElement dropdownLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:country_label']")));
	            System.out.println("Dropdown label found.");
	            dropdownLabel.click();
	            System.out.println("Dropdown label clicked.");

	            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='j_idt87:country_items']/li[contains(@class, 'ui-selectonemenu-item')]")));
	            System.out.println("Number of options found: " + options.size());

	            boolean indiaFound = false;
	            for (WebElement option : options) {
	                if (option.getText().equals("India")) {
	                    option.click();
	                    indiaFound = true;
	                    break;
	                }
	            }
	            Assert.assertTrue(indiaFound, "Option 'India' not found in the dropdown.");
	            System.out.println("Option 'India' selected.");

	            WebElement selectedOptionLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:country_label']")));
	            String selectedOptionText = selectedOptionLabel.getText();
	            System.out.println("Selected option: " + selectedOptionText);
	            Assert.assertEquals(selectedOptionText, "India", "Selected option is not as expected.");

	        } catch (Exception e) {
	            e.printStackTrace();
	            Assert.fail("Test failed due to an exception: " + e.getMessage());
	        }
	    }

	 
	 @Test(dependsOnMethods = "selectCountry")
	 public void confirmAndSelectCity() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 

	     try {
	        
	         WebElement cityDropdownLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:city_label']")));
	         System.out.println("City dropdown label found.");
	         cityDropdownLabel.click();
	         System.out.println("City dropdown label clicked.");

	         
	         List<WebElement> cityOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='j_idt87:city_items']/li[contains(@class, 'ui-selectonemenu-item')]")));
	         System.out.println("Number of city options found: " + cityOptions.size());

	         for (WebElement option : cityOptions) {
	             System.out.println("City option: " + option.getText());
	         }

	         boolean bengaluruFound = false;
	         for (WebElement option : cityOptions) {
	             if (option.getText().equals("Bengaluru")) {
	                 option.click();
	                 bengaluruFound = true;
	                 break;
	             }
	         }

	         Assert.assertTrue(bengaluruFound, "Option 'Bengaluru' not found in the dropdown.");
	         System.out.println("Option 'Bengaluru' selected.");

	         WebElement selectedCityLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:city_label']")));
	         String selectedCityText = selectedCityLabel.getText();
	         System.out.println("Selected city: " + selectedCityText);
	         Assert.assertEquals(selectedCityText, "Bengaluru", "Selected city is not as expected.");

	     } catch (Exception e) {
	         e.printStackTrace();
	         Assert.fail("Test failed due to an exception: " + e.getMessage());
	     }
	 }
	 
	 @Test
	 public void selectReactJs() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 

	     try {
	         WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ui-button-icon-primary ui-icon ui-icon-triangle-1-s']")));
	         System.out.println("Dropdown button found.");
	         dropdownButton.click();
	         System.out.println("Dropdown button clicked.");
	         List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='ui-autocomplete-items ui-autocomplete-list ui-widget-content ui-widget ui-corner-all ui-helper-reset']/li[contains(@class, 'ui-autocomplete-item')]")));
	         System.out.println("Number of dropdown options found: " + dropdownOptions.size());

	         boolean reactJsFound = false;
	         for (WebElement option : dropdownOptions) {
	             if (option.getText().equals("ReactJs")) {
	                 option.click();
	                 reactJsFound = true;
	                 break;
	             }
	         }

	         
	         Assert.assertTrue(reactJsFound, "Option 'ReactJs' not found in the dropdown.");
	         System.out.println("Option 'ReactJs' selected.");

	       
	         WebElement selectedOptionLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class, 'ui-autocomplete-token')]/span[contains(@class, 'ui-autocomplete-token-label')]")));
	         String selectedOptionText = selectedOptionLabel.getText();
	         System.out.println("Selected option: " + selectedOptionText);
	         Assert.assertTrue(selectedOptionText.contains("ReactJs"), "Selected option is not as expected.");

	     } catch (Exception e) {
	         e.printStackTrace();
	         Assert.fail("Test failed due to an exception: " + e.getMessage());
	     }
	 }


	 @Test
	 public void selectEnglishLanguage() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	     try {
	         
	         WebElement langLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:lang_label']")));
	         System.out.println("Language dropdown label found.");
	         langLabel.click();
	         System.out.println("Language dropdown label clicked.");

	        
	         List<WebElement> langOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='j_idt87:lang_items']/li")));

	         boolean englishFound = false;
	         for (WebElement option : langOptions) {
	             if (option.getText().equals("English")) {
	                 option.click();
	                 englishFound = true;
	                 break;
	             }
	         }
	      
	         Assert.assertTrue(englishFound, "Option 'English' not found in the dropdown.");
	         System.out.println("Option 'English' selected.");

	         WebElement selectedLangLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:lang_label']")));
	         String selectedLangText = selectedLangLabel.getText();
	         System.out.println("Selected language: " + selectedLangText);
	         Assert.assertEquals(selectedLangText, "English", "Selected language is not as expected.");

	     } catch (Exception e) {
	         e.printStackTrace();
	         Assert.fail("Test failed due to an exception: " + e.getMessage());
	     }
	 } 
	 
	@Test(dependsOnMethods = "selectEnglishLanguage")
	 public void selectTwoValue() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 

	     try {
	        
	         WebElement valueLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:value_label']")));
	         System.out.println("Value dropdown label found.");
	         valueLabel.click();
	         System.out.println("Value dropdown label clicked.");

	         List<WebElement> valueOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='j_idt87:value_items']/li")));

	         boolean twoFound = false;
	         for (WebElement option : valueOptions) {
	             if (option.getText().equals("Two")) {
	                 option.click();
	                 twoFound = true;
	                 break;
	             }
	         }

	         Assert.assertTrue(twoFound, "Option 'Two' not found in the dropdown.");
	         System.out.println("Option 'Two' selected.");

	         WebElement selectedValueLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='j_idt87:value_label']")));
	         String selectedValueText = selectedValueLabel.getText();
	         System.out.println("Selected value: " + selectedValueText);
	         Assert.assertEquals(selectedValueText, "Two", "Selected value is not as expected.");

	     } catch (Exception e) {
	         e.printStackTrace();
	         Assert.fail("Test failed due to an exception: " + e.getMessage());
	     }
	 }


	 
	@AfterClass
	   public void driverClose()
	  {
		  driver.quit();
	  }

	}
