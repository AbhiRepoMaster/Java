package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Selenium {
    public static void main(String[] args) {
        try {
            System.setProperty("webdriver.edge.driver", "D:\\Selenium_Projects\\msedgedriver.exe");
            WebDriver driver = new EdgeDriver();

            // Launch website
            driver.navigate().to("https://www.google.com/");

            // Click on the search text box and send value
            driver.findElement(By.name("q")).sendKeys("javatpoint tutorial");

            // Click on the search button
            driver.findElement(By.name("btnK")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
