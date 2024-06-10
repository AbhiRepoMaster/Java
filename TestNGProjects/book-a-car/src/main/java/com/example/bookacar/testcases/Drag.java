package com.example.bookacar.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.bookacar.datafetch.BaseDetails;

public class Drag implements BaseDetails {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
        driver.manage().window().maximize();
        navigateToDragDroppage();
    }

    private String getBaseUrl() {
        return BaseUrlDragLnk;
    }

    public void navigateToDragDroppage() {
        driver.get(getBaseUrl());
    }

    
  //  @Test (priority = 1)
    public void testDragAction() {
    	navigateToDragDroppage();
        WebElement draggableElement = driver.findElement(By.id("form:conpnl"));
        
        Actions actions = new Actions(driver);
        int xOffset = 100;
        int yOffset = 0;
        actions.dragAndDropBy(draggableElement, xOffset, yOffset).perform();

        //new position of the element
        String newPosition = draggableElement.getAttribute("style");
        System.out.println("New position: " + newPosition);
        Assert.assertTrue(newPosition.contains("left: 241px;"));
        Assert.assertTrue(newPosition.contains("top: 0px;"));
    }
    
    
   // @Test (priority = 2)
    public void testDraggableColumns() {
    	navigateToDragDroppage();
        WebElement draggableElementInitial = driver.findElement(By.id("form:j_idt94:j_idt95"));
        WebElement draggableElementFinal = driver.findElement(By.id("form:j_idt94:j_idt99"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableElementInitial, draggableElementFinal).perform();

    }
    
    
   // @Test
    public void testResizeImageWithActionsAndNotification() {
        // Locate the resizable handle (bottom-right corner) using its class name
        WebElement resizeHandle = driver.findElement(By.className("ui-resizable-se"));

        // Get the initial size of the image
        WebElement image = driver.findElement(By.id("form:logo"));
        int initialWidth = image.getSize().getWidth();
        int initialHeight = image.getSize().getHeight();
        System.out.println("Initial size: " + initialWidth + "x" + initialHeight);

        // Set the fixed offsets for resizing
        int xOffset = 50;  // Resize width by 50 pixels
        int yOffset = 30;  // Resize height by 30 pixels

        // Scroll the image into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Perform the resize action by dragging the handle
        actions.clickAndHold(resizeHandle)
               .moveByOffset(xOffset, yOffset)
               .release()
               .perform();

        // Get the new size of the image
        int newWidth = image.getSize().getWidth();
        int newHeight = image.getSize().getHeight();
        System.out.println("New size: " + newWidth + "x" + newHeight);

        // Add assertions to verify the size change
        Assert.assertEquals(newWidth, initialWidth + xOffset, "Width should have increased by 50");
        Assert.assertEquals(newHeight, initialHeight + yOffset, "Height should have increased by 30");

        // Wait for the notification to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-message")));

        // Assert the notification text
        String notificationTitle = notification.findElement(By.className("ui-growl-title")).getText();
        String notificationText = notification.findElement(By.tagName("p")).getText();

        // Expected notification content
        String expectedNotificationTitle = "Image resized";
        String expectedNotificationText = "Width:" + newWidth + ",Height:" + newHeight;

        Assert.assertEquals(notificationTitle, expectedNotificationTitle, "Notification title should match expected title.");
        Assert.assertEquals(notificationText, expectedNotificationText, "Notification text should match expected text.");
    }
    
    
   // @Test
    public void testDragAndDrop() {
        // Locate the draggable element using its ID
        WebElement draggable = driver.findElement(By.id("form:drag"));

        // Locate the droppable target using its ID
        WebElement droppable = driver.findElement(By.id("form:drop"));

        // Scroll the draggable element into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", draggable);

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Perform the drag-and-drop action
        actions.dragAndDrop(draggable, droppable).perform();

        // Verify the drag-and-drop action
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='form:drop_content']/p[contains(text(), 'Dropped!')]")));

        Assert.assertNotNull(dropMessage, "The element should be successfully dropped into the target, and a confirmation message should appear.");
    }

   // @Test
    public void testDraggableRow() {
        // Locate the table body
        WebElement tableBody = driver.findElement(By.id("form:j_idt94_data"));

        // Locate the row to be dragged using its data-ri attribute
        WebElement rowToDrag = tableBody.findElement(By.cssSelector("tr[data-ri='0']"));

        // Locate the target row using its data-ri attribute
        WebElement targetRow = tableBody.findElement(By.cssSelector("tr[data-ri='4']"));

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Perform the drag-and-drop action
        actions.clickAndHold(rowToDrag)
               .moveToElement(targetRow)
               .moveByOffset(0, 1) // Move slightly beyond the target row to ensure it drops in the desired position
               .release()
               .perform();

        // Verify the drag-and-drop action by checking the new order of rows
        // In this case, verify that the row with "Bamboo Watch" is now after "Bracelet"
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        boolean isOrderCorrect = false;
        for (int i = 0; i < rows.size() - 1; i++) {
            String firstRowText = rows.get(i).findElement(By.tagName("td")).getText();
            String secondRowText = rows.get(i + 1).findElement(By.tagName("td")).getText();
            if (firstRowText.equals("Bracelet") && secondRowText.equals("Bamboo Watch")) {
                isOrderCorrect = true;
                break;
            }
        }

        Assert.assertTrue(isOrderCorrect, "The row should be successfully dragged and dropped into the new position.");
    }
    
    
    @Test
    public void testProgressBar() {
        // Locate the Start button
        WebElement startButton = driver.findElement(By.id("form:j_idt119"));

        // Click the Start button to start the progress bar
        startButton.click();

        // Wait for the progress bar to complete (wait until it reaches 100%)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.attributeToBe(By.id("form:j_idt121"), "aria-valuenow", "100"));

        // Locate the progress bar label to assert the final progress
        WebElement progressBarLabel = driver.findElement(By.cssSelector("#form\\:j_idt121 .ui-progressbar-label"));

        // Get the final progress text
        String finalProgressText = progressBarLabel.getText();

        // Print the final progress text
        System.out.println("Final progress: " + finalProgressText);

        // Assert the progress bar reaches 100%
        Assert.assertEquals(finalProgressText, "100%", "Progress bar should reach 100%");
    }

    
    
    //@Test
    public void testRangeSlider() {
        // Locate the range slider elements
        WebElement slider = driver.findElement(By.id("form:j_idt125"));
        WebElement handle1 = slider.findElements(By.className("ui-slider-handle")).get(0);
        WebElement handle2 = slider.findElements(By.className("ui-slider-handle")).get(1);
        WebElement rangeDisplay = driver.findElement(By.id("form:displayRange"));

        // Get the initial range value
        String initialRange = rangeDisplay.getText();
        System.out.println("Initial range: " + initialRange);

        // Create Actions instance to perform slider drag
        Actions move = new Actions(driver);

        // Move the first handle (left handle) to 25%
        move.dragAndDropBy(handle1, 40, 0).perform();

        // Move the second handle (right handle) to 75%
        move.dragAndDropBy(handle2, -40, 0).perform();

        // Wait for the range display to update
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.id("form:displayRange"), "Between 25 and 75"));

        // Get the updated range value
        String updatedRange = rangeDisplay.getText();
        System.out.println("Updated range: " + updatedRange);

        // Assert the updated range value
        Assert.assertEquals(updatedRange, "Between 25 and 75", "The range should be between 25 and 75");
    }








    
    
    
    
    
    
    

   
    
    
  //  @AfterTest
    public void teardown() {
        driver.quit();
    }
}