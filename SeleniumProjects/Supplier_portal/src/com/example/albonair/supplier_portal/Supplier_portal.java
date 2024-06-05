package com.example.albonair.supplier_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Supplier_portal {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver1.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.get("http://192.168.1.68/supplier_portal/al-login/");

        // Add any other actions or assertions you need here

        driver.quit(); // Close the browser
    }
}
