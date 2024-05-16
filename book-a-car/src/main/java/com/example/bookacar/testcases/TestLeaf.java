package com.example.bookacar.testcases;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        // Locate the input field using the provided XPath
        WebElement inputField = driver.findElement(By.xpath("//form[@id='j_idt88']//div[3]//div[1]//input[@type='text']"));

        // Check if the input field is disabled
        boolean isDisabled = !inputField.isEnabled();

        // Verify that the input field is disabled
        Assert.assertTrue(isDisabled, "The text box should be disabled.");
    }

  //  @Test
    public void clearText() {
        // Locate the input field using the provided XPath
        WebElement inputField = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt95']"));

        // Clear the text in the input field
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
    
    
  //  @Test
    public void typeAboutYourself() {
        // Locate the textarea using the provided XPath
        WebElement textArea = driver.findElement(By.xpath("//textarea[@id='j_idt88:j_idt101']"));

        // Type a description into the textarea
        textArea.sendKeys("This is a brief description about myself.");
    }

  //  @Test
    public void testErrorMessageDisplayedOnEnter() {
        // Find the input element
        WebElement ageInput = driver.findElement(By.id("j_idt106:thisform:age"));

        // Press Enter key
        ageInput.sendKeys(Keys.ENTER);

        // Check if the error message is displayed
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
        // Find the font picker element
        WebElement fontPicker = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-font ql-picker')]"));

        // Click on the font picker to expand options
        fontPicker.click();

        // Wait for the font picker to expand
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(fontPicker, "class", "ql-expanded"));

        // Find and click the "monospace" option
        WebElement monospaceOption = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[@class='ql-picker-options']//span[@data-value='monospace']"));
        monospaceOption.click();

        // Wait for the font picker to collapse back
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
    
    
    @Test(priority=2)
    public void selectHugeOptionAndVerify() throws InterruptedException {
    	
    	Thread.sleep(2000);
        // Find the size picker element
        WebElement sizePicker = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-size ql-picker')]"));

        // Click on the size picker to expand options
        sizePicker.click();

        // Wait for the size picker to expand
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='grid formgrid']//span[contains(@class,'ql-size ql-picker')]"), "class", "ql-expanded"));

        // Find and click the "huge" option
        WebElement hugeOption = driver.findElement(By.xpath("//div[@class='grid formgrid']//span[@class='ql-picker-options']//span[@data-value='huge']"));
        hugeOption.click();

        // Type some text into the editor
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
        // Check if the font-size is huge (typically 'huge' size is around 26px)
        if (fontSize.equals("32.5px")) {
            System.out.println("Huge size selected");
        } else {
            System.out.println("Huge size not selected");
        }

        // Assert that the font-size is huge
        Assert.assertEquals(fontSize, "32.5px", "The font size is not huge");
    }
    
    //@AfterClass
	  public void driverClose()
	  {
		  driver.quit();
	  }

}