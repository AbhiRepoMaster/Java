package com.example.bookacar.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookACarForm {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	//System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testRedirectToWebsite() {
        driver.get("https://clicktobuy.hyundai.co.in/#/bookACar?modelCode=0Y");
        String hyundaiTitle = driver.getTitle();
        if("Hyundai Car, Sedan, SUV, Hatchback, EV | Hyundai Motor India".equalsIgnoreCase(hyundaiTitle))
        {
        	System.out.println("Redirected to website successfully");

        }
        else
        {
        	System.out.println("Launch failure");
        }
        
    }
    
}
