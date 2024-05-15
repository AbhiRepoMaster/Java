package com.example.bookacar.testcases;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLeaf {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/input.xhtml");

    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }

    @Test
    public void testRedirectToWebsite() {
        String expectedURL = "https://leafground.com/input.xhtml";
        String actualURL = driver.getCurrentUrl();
        if ("Input Components".equalsIgnoreCase(driver.getTitle()) && expectedURL.equals(actualURL)) {
            System.out.println("Redirected to website successfully");
        } else {
            System.out.println("Launch failure");
        }
    }
     
    @Test
    public void typeName() {
    	WebElement inputField = driver.findElement(By.id("j_idt88:name"));
    	inputField.clear();
        inputField.sendKeys("Abhishek Singh");
    }
    
  
    @Test
    public void appendText() throws InterruptedException {
    	List<WebElement> test = driver.findElements(By.xpath("//input[@id='j_idt88:j_idt91']"));
		test.get(0).sendKeys(", India");
		Thread.sleep(3000);
		System.out.println("Size:" + test.size());

    }
    
    @Test
    public void verifyTextBoxIsDisabled() {
        // Locate the input field using the provided XPath
        WebElement inputField = driver.findElement(By.xpath("//form[@id='j_idt88']//div[3]//div[1]//input[@type='text']"));

        // Check if the input field is disabled
        boolean isDisabled = !inputField.isEnabled();

        // Verify that the input field is disabled
        Assert.assertTrue(isDisabled, "The text box should be disabled.");
    }

    @Test
    public void clearText() {
        // Locate the input field using the provided XPath
        WebElement inputField = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt95']"));

        // Clear the text in the input field
        inputField.clear();
    }
    
    @Test
    public void clearAndRetrieveText() {
        // Locate the input field to retrieve text from using the provided XPath
        WebElement inputFieldToRetrieve = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt97']"));

        // Retrieve the text from the input field
        String retrievedText = inputFieldToRetrieve.getAttribute("value");

        // Print the retrieved text
        System.out.println("Retrieved text: " + retrievedText);
    }
    
    @Test
    public void typeEmailAndTab() {
        // Locate the email input field using the provided XPath
        WebElement emailInputField = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt99']"));

        // Type an email into the input field
        emailInputField.sendKeys("test@example.com");

        // Send a Tab key to move to the next element
        emailInputField.sendKeys(Keys.TAB);

        // Locate the next element after the email input field
        WebElement nextElement = driver.switchTo().activeElement();

        // Confirm that the control has moved to the next element
        // This is a simple check assuming the next element is a known element like a text input or button
        Assert.assertNotEquals(nextElement, emailInputField, "The control did not move to the next element.");
    }
    
    
    @Test
    public void typeAboutYourself() {
        // Locate the textarea using the provided XPath
        WebElement textArea = driver.findElement(By.xpath("//textarea[@id='j_idt88:j_idt101']"));

        // Type a description into the textarea
        textArea.sendKeys("This is a brief description about myself.");
    }

    @Test
    public void testErrorMessageDisplayedOnEnter() {
        // Find the input element
        WebElement ageInput = driver.findElement(By.id("j_idt106:thisform:age"));

        // Press Enter key
        ageInput.sendKeys(Keys.ENTER);

        // Check if the error message is displayed
        WebElement errorMessage = driver.findElement(By.id("j_idt106:thisform:j_idt110_error-detail"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed after pressing Enter.");
    }
    
    
    //@AfterClass
	  public void driverClose()
	  {
		  driver.quit();
	  }

}