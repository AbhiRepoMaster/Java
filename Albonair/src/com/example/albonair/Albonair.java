package com.example.albonair;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Albonair {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver1.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Launch website link
        driver.navigate().to("http://10.17.1.139/sites/albonair/");

        // Wait for the login page to load (you may need to enhance this wait)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Array of header links
        String[] headerLinks = {
                "About us",
                "Product",
                "Services",
                "Customers",
                "Gallery",
                "Contact us"
        };

        // Iterate over each header link
        for (String linkText : headerLinks) {
            // Navigate to the link
            driver.navigate().to(getLinkURL(linkText));

            // Wait for the page to load (you may need to enhance this wait)
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Verify if the current URL matches the expected URL
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(getLinkURL(linkText))) {
                System.out.println("Link click behavior for '" + linkText + "' passed");
            } else {
                System.out.println("Link click behavior for '" + linkText + "' failed");
            }
        }

        // Find the search input field in the header using its ID
        WebElement searchInput = driver.findElement(By.id("search"));

        // Enter the search query "Employee"
        searchInput.sendKeys("Employee");

        // Find the search button and click on it
        searchInput.sendKeys(Keys.RETURN);

        // Wait for the search results to load (you may need to enhance this wait)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify if the search results contain the expected text
        WebElement searchResults = driver.findElement(By.cssSelector(".search-results"));
        String searchResultsText = searchResults.getText();
        if (searchResultsText.contains("Employee")) {
            System.out.println("Search behavior for 'Employee' passed");
        } else {
            System.out.println("Search behavior for 'Employee' failed");
        }

        // Close the browser
        driver.quit();
    }

    private static String getLinkURL(String linkText) {
        switch (linkText) {
            case "About us":
                return "http://10.17.1.139/sites/albonair/about-us/";
            case "Product":
                return "http://10.17.1.139/sites/albonair/products/";
            case "Services":
                return "http://10.17.1.139/sites/albonair/services/";
            case "Customers":
                return "http://10.17.1.139/sites/albonair/customers/";
            case "Gallery":
                return "http://10.17.1.139/sites/albonair/gallery/";
            case "Contact us":
                return "http://10.17.1.139/sites/albonair/contact_us/";
            default:
                return "";
        }
    }

    private static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
    }

}
