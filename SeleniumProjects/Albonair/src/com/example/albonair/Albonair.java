package com.example.albonair;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Albonair {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver1.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://10.17.1.139/sites/albonair/");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] headerLinks = {
                "About us",
                "Product",
                "Services",
                "Customers",
                "Gallery",
                "Contact us"
        };

        for (String linkText : headerLinks) {
            driver.navigate().to(getLinkURL(linkText));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(getLinkURL(linkText))) {
                System.out.println("Link click behavior for '" + linkText + "' passed");
            } else {
                System.out.println("Link click behavior for '" + linkText + "' failed");
            }
        }

        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.sendKeys("Employee");
        searchInput.sendKeys(Keys.RETURN);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement searchResults = driver.findElement(By.cssSelector(".search-results"));
        String searchResultsText = searchResults.getText();
        if (searchResultsText.contains("Employee")) {
            System.out.println("Search behavior for 'Employee' passed");
        } else {
            System.out.println("Search behavior for 'Employee' failed");
        }

        driver.navigate().to("http://10.17.1.139/sites/albonair/contact_us/");

        WebElement salutationDropdown = driver.findElement(By.name("work_for_salutation"));
        salutationDropdown.sendKeys("Mr");

        driver.findElement(By.name("work_for_name")).sendKeys("Test");
        driver.findElement(By.name("work_for_phone")).sendKeys("1234567890");
        driver.findElement(By.name("work_for_email")).sendKeys("test@example.com");
        driver.findElement(By.name("work_for_message")).sendKeys("This is a test message");

        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys("C:\\Users\\19048\\Documents\\vb.docx");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("work_for_acceptance")).click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@title, 'recaptcha')]"));
        driver.switchTo().frame(iframe);

        WebElement checkboxLabel = driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']"));
        checkboxLabel.click();

        driver.switchTo().defaultContent();

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        scrollToElement(driver, sendButton);

        sendButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wpcf7-mail-sent-ok")));

        WebElement successMessage = driver.findElement(By.cssSelector(".wpcf7-mail-sent-ok"));
        if (successMessage.getText().contains("Your message has been sent successfully")) {
            System.out.println("Contact form submission successful");
        } else {
            System.out.println("Contact form submission failed");
        }

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
