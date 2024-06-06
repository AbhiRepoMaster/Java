package com.example.bookacar.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import com.example.bookacar.datafetch.BaseDetails;
import com.example.bookacar.pages.WindowsMain;

public class Frame implements BaseDetails {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    private String getBaseUrl() {
        return BaseUrlFrameLnk;
    }

    public void navigateToFramepage() {
        driver.get(getBaseUrl());
    }

    
    @Test (priority = 1)
    public void testButtonInNestedFrame() {
        navigateToFramepage();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        // Switch to the first iframe
        driver.switchTo().frame(0); 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Click")));
        WebElement button = driver.findElement(By.id("Click"));
        button.click();

        // Verify that the button's text has changed
        wait.until(ExpectedConditions.textToBe(By.id("Click"), "Hurray! You Clicked Me."));
        String buttonText = button.getText();
        Assert.assertEquals(buttonText, "Hurray! You Clicked Me.");
    }
    
    @Test (priority = 2)
    public void testCountIFrames() {
        navigateToFramepage();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        int iframeCount = iframes.size();
        System.out.println("Number of iframes on the page: " + iframeCount);
    }
    
   
    
    @Test (priority = 3)
    public void testButtonInSingleFrame() {
        navigateToFramepage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));

        driver.switchTo().frame(0);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Click")));

        WebElement button = driver.findElement(By.id("Click"));
        button.click();
        wait.until(ExpectedConditions.textToBe(By.id("Click"), "Hurray! You Clicked Me."));
        String buttonText = button.getText();
        Assert.assertEquals(buttonText, "Hurray! You Clicked Me.");
    }
    
    
    @AfterTest
    public void teardown() {
        driver.quit();
    }
}