package com.example.selinium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Selenium {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Launch website
        driver.navigate().to("http://ideal.hindujatech.com/users/login");

        // Wait for the login page to load (you may need to enhance this wait)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate and fill in the username and password fields
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='userName']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='passWord']"));

        usernameField.sendKeys("19048");
        passwordField.sendKeys("Kia@2024");

        // Click on the login button
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='submitButton']"));
        loginButton.click();

        // Wait for the login to complete (you may need to enhance this wait)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        

        // Scroll to the logout button
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='last']"));
        scrollToElement(driver, logoutButton);
//
        // Wait for 2 seconds (you may need to enhance this wait)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the logout button
        logoutButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

    private static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
    }
}