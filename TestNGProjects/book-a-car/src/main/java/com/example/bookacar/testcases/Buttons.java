package com.example.bookacar.testcases;

import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Buttons {
	
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/button.xhtml");

    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
@Test(priority = 11)
	public void clickAndConfirmButtonTitle() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    try {
	        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='j_idt88:j_idt90']")));
	        System.out.println("Button found.");

	        // Click the button
	        button.click();
	        System.out.println("Button clicked.");

	        WebElement buttonTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='j_idt88:j_idt90']/span[contains(@class, 'ui-button-text')]")));
	        System.out.println("Button title found.");

	        String titleText = buttonTitle.getText();
	        Assert.assertEquals(titleText, "Click", "The button title is not as expected.");
	        System.out.println("Button title confirmed successfully.");
	
		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Test failed due to an exception: " + e.getMessage());
		    }
		}
	
		
@Test(priority = 2)
	public void confirmButtonIsDisabled() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    try {
	        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='j_idt88:j_idt92']")));
	        System.out.println("Button found.");

	        boolean isDisabled = button.getAttribute("disabled") != null || "true".equals(button.getAttribute("aria-disabled"));
	        Assert.assertTrue(isDisabled, "The button is not disabled.");
	        System.out.println("Button is confirmed to be disabled.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
	    }
	}
	
	
@Test (priority = 3)
	
	public void findAndVerifySubmitButtonPosition() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    try {
	    	
	        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='j_idt88:j_idt94']//span[@class='ui-button-text ui-c'][normalize-space()='Submit']")));
	        System.out.println("Submit button found.");

	        // Get the position of the Submit button
	        Point position = submitButton.getLocation();
	        int x = position.getX();
	        int y = position.getY();

	        // Print the coordinates of the button
	        System.out.println("Submit button position: (" + x + ", " + y + ")");

	        // Verify the position
	        Assert.assertTrue(x >= 0, "X coordinate is not valid.");
	        Assert.assertTrue(y >= 0, "Y coordinate is not valid.");
	        System.out.println("Submit button position verified.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
	    }
	}
	
@Test(priority = 4)
	public void findAndVerifySaveButtonColor() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    try {
	        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
	        System.out.println("Save button found.");

	        String backgroundColor = saveButton.getCssValue("background-color");

	        System.out.println("Save button background color: " + backgroundColor);
	        String expectedBackgroundColor = "rgba(0, 0, 0, 0)";
	        Assert.assertEquals(backgroundColor, expectedBackgroundColor, "The background color of the Save button is not as expected.");
	        System.out.println("Save button background color verified.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
	    }
	}

@Test(priority = 5)
	public void findHeightAndWidthOfButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    try {
	        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-button-icon-right ui-icon ui-c pi pi-bookmark']")));
	        System.out.println("Button found.");

	        Dimension size = button.getSize();
	        int height = size.getHeight();
	        int width = size.getWidth();

	        System.out.println("Button height: " + height + " pixels");
	        System.out.println("Button width: " + width + " pixels");

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
	    }
	}

@Test(priority = 6)
	public void verifyColorChangeOnMouseOver() {
	    WebElement successButton = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt100']"));

	    String colorBeforeMouseover = successButton.getCssValue("background-color");

	    Actions actions = new Actions(driver);
	    actions.moveToElement(successButton).perform();
	    try {
	        Thread.sleep(1000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    String colorAfterMouseover = successButton.getCssValue("background-color");

	    assertNotEquals(colorAfterMouseover, colorBeforeMouseover, "The color did not change upon mouseover");
	}

@Test(priority = 7)
public void clickImageButtonAndHiddenButton() {
    WebElement imageButton = driver.findElement(By.xpath("//span[normalize-space()='Image']"));
    imageButton.click();
    WebElement hiddenButton = driver.findElement(By.xpath("//span[normalize-space()='Image']"));
    Actions actions = new Actions(driver);
    actions.moveToElement(hiddenButton).click().perform();
}

//	@Test
//	public void testRoundedButtonsCount() {
//	    int expectedCount = 4; // Change this value based on the number of buttons you expect
//	    int actualCount = driver.findElements(By.className("rounded-button")).size(); // Assuming you have WebDriver instance named 'driver'
//	    Assert.assertEquals(actualCount, expectedCount, "Number of rounded buttons is not as expected");
//	}

@Test(priority = 8)
public void countRoundedButtons() {
	
    // Locate all button elements with class 'rounded-button'
    List<WebElement> buttons = driver.findElements(By.cssSelector("button.rounded-button"));

    // Iterate over each button and print its text
    for (WebElement button : buttons) {
        System.out.println(button.getText());
    }

    // Get the count of rounded buttons
    int count = buttons.size();
    System.out.println("Number of rounded buttons: " + count);
}
	
	
  @AfterClass
   public void driverClose()
  {
	  driver.quit();
  }

}
