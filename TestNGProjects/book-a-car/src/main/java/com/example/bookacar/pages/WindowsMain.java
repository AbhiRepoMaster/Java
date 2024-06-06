package com.example.bookacar.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.bookacar.datafetch.BaseDetails;

public class WindowsMain implements BaseDetails {

    private WebDriver driver;
    private WebDriverWait wait;

    public WindowsMain(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void navigateToHomePage() {
        driver.get(getBaseUrl());
    }

    private String getBaseUrl() {
        return BaseUrlWindowLnk;
    }

    public void clickNewWindowButton() {
        WebElement button = driver.findElement(By.id(NewWindowButton));
        button.click();
    }

    public void switchToNewWindow() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void switchToMainWindow() {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

    public void clickOpenMultipleButton() {
        WebElement openMultipleButton = driver.findElement(By.xpath(MultipleButton));
        openMultipleButton.click();
    }

    public void printTabNamesAndCount() {
        // wait.until(ExpectedConditions.numberOfWindowsToBe(3));
        Set<String> handles = driver.getWindowHandles();
        System.out.println("Total number of windows opened: " + handles.size());

        for (String handle : handles) {
            driver.switchTo().window(handle);
            String tabName = driver.getTitle();
            System.out.println("Tab Name: " + tabName);
        }
    }

    public void closeAllWindowsAndReturnToDefault() {
        String mainWindow = driver.getWindowHandles().iterator().next();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }
    
    
    public void clickOpenMultipleButtonTwo() {
        WebElement openMultipleButton = driver.findElement(By.xpath(MultipleButtonTwo));
        openMultipleButton.click();
    }
    
    
    public void clickTestTwoNewTabToOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement testLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(clickTestTwoNewTabToOpen)));
        testLink.click();
    }
    
    
}