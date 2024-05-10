package com.example.bookacar.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class BookACarForm {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testRedirectToWebsite() {
        driver.get("https://clicktobuy.hyundai.co.in/#/bookACar?modelCode=0Y");
        String expectedURL = "https://clicktobuy.hyundai.co.in/#/bookACar?modelCode=0Y";
        String actualURL = driver.getCurrentUrl();
        if ("Hyundai Car, Sedan, SUV, Hatchback, EV | Hyundai Motor India".equalsIgnoreCase(driver.getTitle()) && expectedURL.equals(actualURL)) {
            System.out.println("Redirected to website successfully");
        } else {
            System.out.println("Launch failure");
        }
    }

    @Test(dependsOnMethods = "testRedirectToWebsite")
    public void clickOnModel() throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='inputmodel-01']"));
        Select select = new Select(dropdown);
        Thread.sleep(5000);
        select.selectByValue("SQ");
        String selectedOptionText = select.getFirstSelectedOption().getText();
        System.out.println("Selected model: " + selectedOptionText);
        Assert.assertEquals(selectedOptionText, "Venue N line", "Selected model is not as expected");
    }

    @Test(dependsOnMethods = "clickOnModel")
    public void clickOnFuelType() throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='inputfuel-01']"));
        Select select = new Select(dropdown);
        Thread.sleep(5000);
        select.selectByValue("U");
        String selectedOptionText = select.getFirstSelectedOption().getText();
        System.out.println("Selected fuel type: " + selectedOptionText);
        Assert.assertEquals(selectedOptionText, "Petrol", "Selected fuel type is not as expected");
    }

    @Test(dependsOnMethods = "clickOnFuelType")
    public void selectVariant() throws InterruptedException {
        WebElement variantDropdown = driver.findElement(By.xpath("//select[@id='inputvrnt-01']"));
        Select variantSelect = new Select(variantDropdown);
        Thread.sleep(5000);
        variantSelect.selectByIndex(1);
        String selectedVariantText = variantSelect.getFirstSelectedOption().getText();
        System.out.println("Selected Variant: " + selectedVariantText);
        Assert.assertNotNull(selectedVariantText, "Selected Variant is not as expected");
    }

    @Test(dependsOnMethods = "selectVariant")
    public void selectExteriorColor() throws InterruptedException {
        WebElement exteriorColorDropdown = driver.findElement(By.xpath("//select[@id='inputext-01']"));
        Select exteriorColorSelect = new Select(exteriorColorDropdown);
        Thread.sleep(5000);
        exteriorColorSelect.selectByIndex(1);
        String selectedExteriorColor = exteriorColorSelect.getFirstSelectedOption().getText();
        System.out.println("Selected Exterior Color: " + selectedExteriorColor);
        Assert.assertNotNull(selectedExteriorColor, "Selected exterior color is not as expected");
    }

    @Test(dependsOnMethods = "selectExteriorColor")
    public void selectInteriorColor() throws InterruptedException {
        WebElement interiorDropdown = driver.findElement(By.xpath("//select[@id='inputinter-01']"));
        Select interiorColorSelect = new Select(interiorDropdown);
        Thread.sleep(5000);
        interiorColorSelect.selectByIndex(1);
        String selectedInteriorColor = interiorColorSelect.getFirstSelectedOption().getText();
        System.out.println("Selected Interior Color: " + selectedInteriorColor);
        Assert.assertNotNull(selectedInteriorColor, "Selected interior color is not as expected");
    }

    @Test(dependsOnMethods = "selectInteriorColor")
    public void selectDealerState() throws InterruptedException {
        WebElement stateDropdown = driver.findElement(By.xpath("//select[@id='state-01']"));
        Select dealerStateSelect = new Select(stateDropdown);
        Thread.sleep(5000);
        dealerStateSelect.selectByIndex(1);
        String selectedDealerState = dealerStateSelect.getFirstSelectedOption().getText();
        System.out.println("Selected Dealer State: " + selectedDealerState);
        Assert.assertNotNull(selectedDealerState, "Selected dealer state is not as expected");
    }

    @Test(dependsOnMethods = "selectDealerState")
    public void selectDealerCity() throws InterruptedException {
        WebElement cityDropdown = driver.findElement(By.xpath("//select[@id='dealer-city-01']"));
        Select dealerCitySelect = new Select(cityDropdown);
        Thread.sleep(5000);
        dealerCitySelect.selectByIndex(1);
        String selectedDealerCity = dealerCitySelect.getFirstSelectedOption().getText();
        System.out.println("Selected Dealer City: " + selectedDealerCity);
        Assert.assertNotNull(selectedDealerCity, "Selected dealer city is not as expected");
    }

    @Test(dependsOnMethods = "selectDealerCity")
    public void selectDealerName() throws InterruptedException {
        WebElement dealerNameDropdown = driver.findElement(By.xpath("//select[@id='bookacardelar']"));
        Select dealerNameSelect = new Select(dealerNameDropdown);
        Thread.sleep(5000);
        dealerNameSelect.selectByIndex(1);
        String selectedDealerName = dealerNameSelect.getFirstSelectedOption().getText();
        System.out.println("Selected Dealer Name: " + selectedDealerName);
        Assert.assertNotNull(selectedDealerName, "Selected dealer name is not as expected");
    }

    @Test(dependsOnMethods = "selectDealerName")
    public void clickProceedButton() throws InterruptedException {
        WebElement proceedButton = driver.findElement(By.xpath("//button[@class='btn btn-blue']"));
        proceedButton.click();
        Thread.sleep(5000);
    }
}
