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
    
  //  @Test (priority = 1)
    public void SimpleDialogAlert() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(SimpleAleretButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(alertText, "I am simple alert.");
    }
    
    
 //   @Test (priority = 2)
    public void ConfirmDialogAlert() {
    	navigateToFramepage();
    	
        WebElement button = driver.findElement(By.xpath(ConfirmDialogButton));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(alertText, "Did you call me?");
    }
    
   
 //  @Test (priority = 3)
   public void SweetAlertAlert() {
   	navigateToFramepage();
   	
       WebElement button = driver.findElement(By.xpath(SweetAlertButton));
       button.click();
       WebElement sweetAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SweetAlertDLg)));
       String modalText = sweetAlert.findElement(By.xpath(SweetAlertTxt)).getText();
       System.out.println("Sweet Alert text: " + modalText);
       WebElement DismissButton = sweetAlert.findElement(By.xpath(DismissBTN));
       DismissButton.click();
       Assert.assertEquals(modalText, "Dialog");
   } 
   
     
  //  @Test (priority = 4)
    public void testSweetModalDialog() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(SweetModalDialogBTN));
        button.click();
        WebElement sweetModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SweetModal)));
        String modalText = sweetModal.findElement(By.xpath(ModalText)).getText();
        System.out.println("Sweet Modal Dialog text: " + modalText);
        WebElement closeButton = sweetModal.findElement(By.xpath(CloseButton));
        closeButton.click();
        Assert.assertEquals(modalText, "Modal Dialog (Sweet Alert)");
    }
    
  //  @Test (priority = 5)
    public void testPromplDialog() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(PromplDialogBTN));
        button.click();
        
        WebElement PromplDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PromplDialogTxt)));
        String modalText = PromplDialog.findElement(By.xpath(ModalText)).getText();
        System.out.println("Sweet Modal Dialog text: " + modalText);
        WebElement closeButton = PromplDialog.findElement(By.xpath(PromplcloseButton));
        closeButton.click();
        Assert.assertEquals(modalText, "Modal Dialog (Sweet Alert)");
    }
    
  //  @Test (priority = 6)
    public void testSweetAlertConfirmation() {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(SweetAlertConfirmationBTN));
        button.click();
        driver.switchTo().alert().sendKeys("Abhishek");
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }  
    
    
    
    
    @Test (priority = 7)
    public void testMinimizeAndMaximize() throws InterruptedException {
    	navigateToFramepage();
        WebElement button = driver.findElement(By.xpath(MinimizeAndMaximizeBTN));
        button.click();
        
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MinimizeBTN)));
        // Minimize the dialog
        WebElement minimizeButton = modalDialog.findElement(By.xpath(MinimizeBTN));
        minimizeButton.click();
        Thread.sleep(2000); 
        // Maximize the dialog
        WebElement maximizeButton = modalDialog.findElement(By.xpath(MaximizeBTN));
        maximizeButton.click();
        Thread.sleep(2000); 

    }
 
    
    @AfterTest
    public void teardown() {
        driver.quit();
    }
}