package com.example.bookacar.testcases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.bookacar.datafetch.BaseDetails;

public class Alerts implements BaseDetails{

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }
    
    private String getBaseUrl() {
        return BaseUrlAlertLnk;
    }

    public void navigateToFramepage() {
        driver.get(getBaseUrl());
    }
    
    @Test (priority = 1)
    public void SimpleDialogAlert() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(SimpleAleretButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(alertText, "I am simple alert.");
    }
    
    
    @Test (priority = 2)
    public void ConfirmDialogAlert() {
    	navigateToFramepage();
    	
        WebElement button = driver.findElement(By.xpath(ConfirmDialogButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(alertText, "Did you call me?");
    }
    
 //   @Test (priority = 3)
    public void SweetAlertAlert() {
    	navigateToFramepage();
    	
        WebElement button = driver.findElement(By.xpath(SweetAlertButton));
        button.click();
        WebElement sweetAlert = driver.findElement(By.className("ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top ui-draggable-handle"));
        String alertText = sweetAlert.findElement(By.className("j_idt88:j_idt96_title")).getText();
        System.out.println("Sweet Alert text: " + alertText);
        WebElement DismissButton = sweetAlert.findElement(By.className("ui-button-text ui-c"));
        DismissButton.click();
        Assert.assertEquals(alertText, "Expected Sweet Alert message");
    }
    
    @Test (priority = 4)
    public void testSweetModalDialog() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt100']"));
        button.click();
        WebElement sweetModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='j_idt88:j_idt101_title']")));
        String modalText = sweetModal.findElement(By.xpath("//span[@id = 'j_idt88:j_idt101_title']")).getText();
        System.out.println("Sweet Modal Dialog text: " + modalText);
        WebElement closeButton = sweetModal.findElement(By.xpath("//div[(@id = 'j_idt88:j_idt101')] //span[@class ='ui-icon ui-icon-closethick']"));
        closeButton.click();
        Assert.assertEquals(modalText, "Modal Dialog (Sweet Alert)");
    }
    
    @Test (priority = 5)
    public void testPromplDialog() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt100']"));
        button.click();
        
        WebElement PromplDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='j_idt88:j_idt101_title']")));
        String modalText = PromplDialog.findElement(By.xpath("//span[@id = 'j_idt88:j_idt101_title']")).getText();
        System.out.println("Sweet Modal Dialog text: " + modalText);
        WebElement closeButton = PromplDialog.findElement(By.xpath("//div[(@id = 'j_idt88:j_idt101')] //span[@class ='ui-icon ui-icon-closethick']"));
        closeButton.click();
        Assert.assertEquals(modalText, "Modal Dialog (Sweet Alert)");
    }
    
    @Test (priority = 6)
    public void testSweetAlertConfirmation() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath("//button[(@id = 'j_idt88:j_idt104')]"));
        button.click();
        driver.switchTo().alert().sendKeys("Abhishek");
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }  
    
    
  //  @Test (priority = 7)
    public void testMinimizeAndMaximize() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt111']"));
        button.click();
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'ui-icon ui-icon-minus']")));
        String initialState = modalDialog.getAttribute("style");
        System.out.println("Initial dialog state: " + initialState);
        
        // Minimize the dialog
        WebElement minimizeButton = modalDialog.findElement(By.xpath("//span[@class = 'ui-icon ui-icon-minus']"));
        minimizeButton.click();
        String minimizedState = modalDialog.getAttribute("style");
        Assert.assertTrue(minimizedState.contains("display: none;"), "Dialog should be minimized");
        System.out.println("Dialog minimized state: " + minimizedState);

        // Maximize the dialog
        WebElement maximizeButton = modalDialog.findElement(By.xpath("//span[@class = 'ui-icon ui-icon-extlink']"));
        maximizeButton.click();

        WebDriverWait waitForMaximize = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForMaximize.until(ExpectedConditions.not(ExpectedConditions.attributeContains(modalDialog, "style", "display: none;")));

        String maximizedState = modalDialog.getAttribute("style");
        Assert.assertTrue(maximizedState.contains("display: none;"), "Dialog should be maximized");
        System.out.println("Dialog maximized state: " + maximizedState);
    }
 
    
   // @AfterTest
    public void teardown() {
        driver.quit();
    }
}