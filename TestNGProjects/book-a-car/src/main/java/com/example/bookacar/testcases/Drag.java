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

    
    @Test (priority = 1)
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
        //Assert.assertTrue(newPosition.contains("left: 241px;"));
        //Assert.assertTrue(newPosition.contains("top: 0px;"));
    }
    
    
    @Test (priority = 2)
    public void testDraggableColumns() {
    	navigateToDragDroppage();
        WebElement draggableElementInitial = driver.findElement(By.id(DraggableColumnsInit));
        WebElement draggableElementFinal = driver.findElement(By.id(DraggableColumnsFinl));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableElementInitial, draggableElementFinal).perform();

    }
  
    @Test(priority = 3)
    public void testResizeImageWithActionsAndNotification() {
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));        
        // Locate the resize handle and the image
        WebElement resizeHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ImageResizeHandle)));
        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form:logo")));
        
        // Get initial size of the image
        int initialWidth = image.getSize().getWidth();
        int initialHeight = image.getSize().getHeight();
        System.out.println("Initial size: " + initialWidth + "x" + initialHeight);

        // Calculate dynamic offsets based on the initial size
        int xOffset = initialWidth / 2;  
        int yOffset = initialHeight / 2; 

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);

        Actions actions = new Actions(driver);
        actions.moveToElement(resizeHandle)
               .clickAndHold()
               .moveByOffset(xOffset, yOffset)
               .release()
               .perform();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(image, "width", String.valueOf(initialWidth))));
        
        // Get new size of the image
        int newWidth = image.getSize().getWidth();
        int newHeight = image.getSize().getHeight();
        System.out.println("New size: " + newWidth + "x" + newHeight);
    }
    
    
    
  //  @Test (priority = 4)
    public void testDragAndDrop() {
        WebElement draggable = driver.findElement(By.id("form:drag"));
        WebElement droppable = driver.findElement(By.id("form:drop_header"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", draggable);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropMessageTxt)));

        Assert.assertNotNull(dropMessage, "The element should be successfully dropped into the target, and a confirmation message should appear.");
    }

    
    
    @Test (priority = 5)
    public void testDraggableRow() {
    	WebElement tableBody = driver.findElement(By.xpath(DraggableRowtableBody));
        WebElement rowToDrag = tableBody.findElement(By.xpath(DraggableRowtableBodyrowToDrag));
        WebElement targetRow = tableBody.findElement(By.xpath(DraggableRowtableBodyrowToDragtargetRow));
        Actions actions = new Actions(driver);
        actions.clickAndHold(rowToDrag).moveToElement(targetRow)
            //   .moveByOffset(0, 1) 
               .release()
               .perform();
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

       // Assert.assertTrue(isOrderCorrect, "The row should be successfully dragged and dropped into the new position.");
    }
    
    
    
    
    
    @Test (priority = 6)
    public void testProgressBar() {
        WebElement startButton = driver.findElement(By.id("form:j_idt119"));
        startButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.attributeToBe(By.id("form:j_idt121"), "aria-valuenow", "100"));
        WebElement progressBarLabel = driver.findElement(By.cssSelector(ProgressBarLabelXpt));
        String finalProgressText = progressBarLabel.getText();
        System.out.println("Final progress: " + finalProgressText);
        Assert.assertEquals(finalProgressText, "100%", "Progress bar should reach 100%");
    }

    
    
    @Test (priority = 7)
    public void testRangeSlider() {
        WebElement slider = driver.findElement(By.id("form:j_idt125"));
        WebElement handle1 = slider.findElements(By.className("ui-slider-handle")).get(0);
        WebElement handle2 = slider.findElements(By.className("ui-slider-handle")).get(1);
        WebElement rangeDisplay = driver.findElement(By.id("form:displayRange"));

        // Get the initial range value
        String initialRange = rangeDisplay.getText();
        System.out.println("Initial range: " + initialRange);
        Actions move = new Actions(driver);
        move.dragAndDropBy(handle1, 40, 0).perform();
        move.dragAndDropBy(handle2, -40, 0).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.id("form:displayRange"), "Between 25 and 75"));
        // Get the updated range value
        String updatedRange = rangeDisplay.getText();
        System.out.println("Updated range: " + updatedRange);
      //  Assert.assertEquals(updatedRange, "Between 40 and 70");
    }








    
    
    
    
    
    
    

   
    
    
  //  @AfterTest
    public void teardown() {
        driver.quit();
    }
}