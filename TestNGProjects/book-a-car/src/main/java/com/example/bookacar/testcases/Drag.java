package com.example.bookacar.testcases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
    
    
    @Test (priority = 2)
    public void testDraggableColumns() {
    	navigateToDragDroppage();
        WebElement draggableElementInitial = driver.findElement(By.id("form:j_idt94:j_idt95"));
        WebElement draggableElementFinal = driver.findElement(By.id("form:j_idt94:j_idt99"));
        
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableElementInitial, draggableElementFinal).perform();

        //new position of the element
//        String newPosition = draggableElement.getAttribute("style");
//        System.out.println("New position: " + newPosition);
//        Assert.assertTrue(newPosition.contains("left: 241px;"));
//        Assert.assertTrue(newPosition.contains("top: 0px;"));
    }
    
    
    
    
    
    
    

   
    
    
  //  @AfterTest
    public void teardown() {
        driver.quit();
    }
}