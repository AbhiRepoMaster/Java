package com.example.bookacar.testcases;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
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
     
   // @Test
    public void typeName() {
    	WebElement inputField = driver.findElement(By.id("j_idt88:name"));
    	inputField.clear();
        inputField.sendKeys("Abhishek Singh");
    }
    
  
  //  @Test
    public void appendText() throws InterruptedException {
    	List<WebElement> test = driver.findElements(By.xpath("//input[@id='j_idt88:j_idt91']"));
		test.get(0).sendKeys(", India");
		Thread.sleep(3000);
		System.out.println("Size:" + test.size());

    }
    
  //  @Test
    public void verifyTextBoxIsDisabled() {
        WebElement inputField = driver.findElement(By.xpath("//form[@id='j_idt88']//div[3]//div[1]//input[@type='text']"));
        boolean isDisabled = !inputField.isEnabled();
        Assert.assertTrue(isDisabled, "The text box should be disabled.");
    }

  //  @Test
    public void clearText() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt95']"));

        inputField.clear();
    }
    
    //@Test
    public void clearAndRetrieveText() {
        // Locate the input field to retrieve text from using the provided XPath
        WebElement inputFieldToRetrieve = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt97']"));

        // Retrieve the text from the input field
        String retrievedText = inputFieldToRetrieve.getAttribute("value");

        // Print the retrieved text
        System.out.println("Retrieved text: " + retrievedText);
    }
    
   // @Test
    public void typeEmailAndTab() {
        WebElement emailInputField = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt99']"));

        emailInputField.sendKeys("test@example.com");

        emailInputField.sendKeys(Keys.TAB);

        WebElement nextElement = driver.switchTo().activeElement();

        Assert.assertNotEquals(nextElement, emailInputField, "The control did not move to the next element.");
    }
    
    
  //  @Test
    public void typeAboutYourself() {
        WebElement textArea = driver.findElement(By.xpath("//textarea[@id='j_idt88:j_idt101']"));

        textArea.sendKeys("This is a brief description about myself.");
    }

  //  @Test
    public void testErrorMessageDisplayedOnEnter() {
        WebElement ageInput = driver.findElement(By.id("j_idt106:thisform:age"));

        ageInput.sendKeys(Keys.ENTER);

        WebElement errorMessage = driver.findElement(By.id("j_idt106:thisform:j_idt110_error-detail"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed after pressing Enter.");
    }
    
//    
//    @Test
//    public void selectMonospaceOption() {
//        // Find the font picker element
//        WebElement fontPicker = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-font ql-picker')]"));
//
//        // Click on the font picker to expand options
//        fontPicker.click();
//        
//        WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(60));
//        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-font ql-picker')]"), "class", "ql-font ql-picker ql-expanded"));
//
//        // Find and click the "monospace" option
//        WebElement monospaceOption = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[@class='ql-picker-options']//span[@data-value='monospace']"));
//        monospaceOption.click();
//        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-font ql-picker')]"), "class", "ql-font ql-picker ql-expanded"));
//    
//    }
    
    
    @Test(priority=1)
    public void selectMonospaceOptionAndVerify() {
        WebElement fontPicker = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-font ql-picker')]"));

        fontPicker.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(fontPicker, "class", "ql-expanded"));

        WebElement monospaceOption = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[@class='ql-picker-options']//span[@data-value='monospace']"));
        monospaceOption.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(fontPicker, "class", "ql-expanded")));

        // Type some text into the editor
        WebElement editor = driver.findElement(By.xpath("//div[@id='j_idt88:j_idt103_editor']//div[@class='ql-editor ql-blank']"));
        editor.click();
        editor.sendKeys("This is a test text");

        // Verify that the typed text is in monospace font
        WebElement typedText = driver.findElement(By.xpath("//p/span"));
        String fontFamily = typedText.getCssValue("font-family");
        System.out.println(fontFamily);
        // Check if the font-family is monospace
        if (fontFamily.contains("monospace")) {
            System.out.println("Monospace selected");
        } else {
            System.out.println("Monospace not selected");
        }

        // Assert that the font-family is monospace
       // Assert.assertTrue(fontFamily.contains("monospace"), "The font is monospace");
    }
    
    
   // @Test(priority=2)
    public void selectHugeOptionAndVerify() throws InterruptedException {
    	
    	Thread.sleep(2000);
        WebElement sizePicker = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-size ql-picker')]"));

        sizePicker.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-size ql-picker')]"), "class", "ql-expanded"));

        WebElement hugeOption = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[@class='ql-picker-options']//span[@data-value='huge']"));
        hugeOption.click();

        WebElement editor = driver.findElement(By.xpath("//div[@id='j_idt88:j_idt103_editor']//div[@class='ql-editor']"));
        
        Actions act = new Actions(this.driver);
        act.doubleClick(editor);
        act.sendKeys(Keys.DELETE);
        act.sendKeys("Huge Option");
        Thread.sleep(2000);
//        editor.click();
//        editor.clear();
//        editor.sendKeys("77878");

        // Verify that the typed text is in huge size
        String fontSize = driver.findElement(By.xpath("//div[@id='j_idt88:j_idt103_editor']//div[@class='ql-editor']")).getCssValue("font-size");
        System.out.println(fontSize);
        if (fontSize.equals("32.5px")) {
            System.out.println("Huge size selected");
        } else {
            System.out.println("Huge size not selected");
        }

        Assert.assertEquals(fontSize, "32.5px", "The font size is not huge");
    }
    
    //@Test(priority = 3)
    public void verifyLabelPositionChanges() throws InterruptedException {
        Thread.sleep(2000);

        try {
            WebElement inputField = driver.findElement(By.id("j_idt106:float-input"));
            System.out.println("Input field found.");

            WebElement label = driver.findElement(By.xpath("//label[@for='j_idt106:float-input']"));
            System.out.println("Label found.");

            Point initialPosition = label.getLocation();
            System.out.println("Initial label position: " + initialPosition);

            inputField.click();
            System.out.println("Input field clicked.");

            Thread.sleep(1000);  
            Point newPosition = label.getLocation();
            System.out.println("New label position: " + newPosition);

            Assert.assertNotEquals(newPosition, initialPosition, "The label position did not change.");

            System.out.println("Label position changed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    //@Test(priority = 4)
    public void typeAndSelectFromDropdown() throws InterruptedException {
        Thread.sleep(2000);

        try {
            WebElement inputField = driver.findElement(By.id("j_idt106:auto-complete_input"));
            System.out.println("Input field found.");

            inputField.sendKeys("Abhi");
            System.out.println("Typed 'Abhi' into the input field.");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'ui-autocomplete-items')]")));
            System.out.println("Dropdown options populated.");

            WebElement thirdOption = driver.findElement(By.xpath("//ul[contains(@class,'ui-autocomplete-items')]//li[3]"));
            System.out.println("Third option found in the dropdown.");

            thirdOption.click();
            System.out.println("Third option selected from the dropdown.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    
   // @Test(priority = 5)
    public void typeDOBAndConfirmDateChosen() throws InterruptedException {
        Thread.sleep(2000);

        try {
            WebElement dobInputField = driver.findElement(By.id("j_idt106:j_idt116_input"));
            System.out.println("DOB input field found.");

            String dob = "22/03/1996";
            dobInputField.sendKeys(dob);
            System.out.println("Typed DOB '" + dob + "' into the input field.");

            String chosenDate = dobInputField.getAttribute("value");
            System.out.println("Date chosen: " + chosenDate);

            Assert.assertEquals(chosenDate, dob, "The chosen date does not match the input value.");

            System.out.println("Chosen date confirmed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    
    //@Test(priority = 6)
    public void typeNumberAndSpinToConfirmValue() throws InterruptedException {
        Thread.sleep(2000);

        try {
            WebElement numberInputField = driver.findElement(By.id("j_idt106:j_idt118_input"));
            System.out.println("Number input field found.");

            String number = "10"; 
            numberInputField.sendKeys(number);
            System.out.println("Typed number '" + number + "' into the input field.");

            String initialValue = numberInputField.getAttribute("value");
            System.out.println("Initial value: " + initialValue);

            WebElement spinUpButton = driver.findElement(By.xpath("//a[contains(@class,'ui-spinner-up')]"));
            spinUpButton.click();
            System.out.println("Clicked spin up button.");

            String newValue = numberInputField.getAttribute("value");
            System.out.println("New value: " + newValue);

            Assert.assertNotEquals(newValue, initialValue, "The value did not change after spinning.");

            System.out.println("Value changed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void typeNumberAndConfirmSliderMoves() {
        // Wait for the page to load
        //WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            WebElement sliderInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt106:slider")));
            System.out.println("Slider input field found.");

            String inputValue = "51";
            sliderInputField.clear();
            sliderInputField.sendKeys(inputValue);
            System.out.println("Typed number '51' into the input field.");

            Thread.sleep(1000); 

            WebElement sliderHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='j_idt106:j_idt120']//span[contains(@class,'ui-slider-handle')]")));
            System.out.println("Slider handle found.");

            String handleStyle = sliderHandle.getAttribute("style");
            System.out.println("Slider handle style attribute: " + handleStyle);

            String percentageStr = handleStyle.replaceAll("[^0-9.]", "");
            double sliderValue = Double.parseDouble(percentageStr);
            System.out.println("Slider value (as percentage): " + sliderValue);

            double expectedSliderValue = (51 / 100.0) * 100;
            System.out.println("Expected slider value (as percentage): " + expectedSliderValue);

            Assert.assertEquals(sliderValue, expectedSliderValue, 1.0, "The slider position does not reflect the typed value.");

            System.out.println("Slider position confirmed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void clickAndConfirmKeyboardAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt106:j_idt122")));
            System.out.println("Input field found.");

            inputField.click();
            System.out.println("Clicked on the input field.");

            Actions actions = new Actions(driver);
            actions.sendKeys(inputField, "A").perform(); 

            Thread.sleep(1000);

            // Locate the on-screen keyboard element 
            WebElement keyboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'ui-keyboard')]")));
            System.out.println("On-screen keyboard element found.");

            // Verify that the keyboard element is displayed
            Assert.assertTrue(keyboardElement.isDisplayed(), "The on-screen keyboard did not appear.");

            System.out.println("On-screen keyboard confirmed successfully.");

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