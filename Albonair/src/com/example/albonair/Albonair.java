package com.example.albonair;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;


public class Albonair {
	
	public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver1.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Launch website link
        driver.navigate().to("https://albonair.co.in/");

        // Wait for the login page to load (you may need to enhance this wait)
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
