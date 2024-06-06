package com.example.bookacar.testcases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.bookacar.pages.HomePage;

public class Window {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

   @Test(priority = 1)
    public void testWindowSwitch() {
        homePage.navigateToHomePage();
        homePage.clickNewWindowButton();
        homePage.switchToNewWindow();
        homePage.closeCurrentWindow();
        homePage.switchToMainWindow();
    }

   @Test(priority = 2)
   public void testWindowCountAndTabs() {
       homePage.navigateToHomePage();
       homePage.clickOpenMultipleButton();
       homePage.printTabNamesAndCount();
       homePage.closeAllWindowsAndReturnToDefault();
   }
    
   @Test(priority = 3)
   public void testCloseWindowsExceptPrimary() {
       homePage.navigateToHomePage();
       homePage.clickOpenMultipleButtonTwo();
       homePage.printTabNamesAndCount();
       homePage.closeAllWindowsAndReturnToDefault();
   }
   
   @Test(priority = 4)
   public void testTwoNewTabToOpen() {
       homePage.navigateToHomePage();
       homePage.clickTestTwoNewTabToOpen();
       homePage.printTabNamesAndCount();
       homePage.closeAllWindowsAndReturnToDefault();
   }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}