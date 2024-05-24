package com.example.bookacar.testcases;

import static org.testng.Assert.assertNotEquals;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HyperLink {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
   public void setUp() {
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://leafground.com/link.xhtml;jsessionid=node01s21lgbpv5zfydzex7yrtiuvl1055948.node0");
    	wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));

    }
	
	
	@Test (priority = 4)
	
	public void testRedirectionToDashboard() {
	    WebElement goToDashboardLink = driver.findElement(By.xpath("//div[@class='grid ui-fluid']//div[1]//div[1]//div[1]//div[1]//a[1]"));
	    goToDashboardLink.click();try {
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    String currentUrl = driver.getCurrentUrl();
	    if (currentUrl.contains(";jsessionid")) {
	        currentUrl = currentUrl.split(";jsessionid")[0];
	    }
	    Assert.assertEquals(currentUrl, "https://leafground.com/dashboard.xhtml", "The redirection to the dashboard failed.");
	}

	@Test (priority = 2)
	public void testFindLinkDestination() {
	    WebElement linkElement = driver.findElement(By.xpath("//a[normalize-space()='Find the URL without clicking me.']"));
	   
	    String linkDestination = linkElement.getAttribute("href");
	    if (linkDestination.contains(";jsessionid")) {
	        linkDestination = linkDestination.split(";jsessionid")[0];
	    }
	    String expectedUrl = "https://leafground.com/grid.xhtml";
	    Assert.assertEquals(linkDestination, expectedUrl, "The link destination is not as expected.");
	}
	
//	@Test(priority = 3)
//	public void testBrokenLink() {
//	    WebElement brokenLinkElement = driver.findElement(By.xpath("//a[normalize-space()='Broken?']"));
//	    String href = brokenLinkElement.getAttribute("href");
//	    Assert.assertNotNull(href, "The 'href' attribute is null or empty.");
//	    Assert.assertTrue(href.startsWith("http") || href.startsWith("https"), "The link URL is invalid.");
//	    try {
//	        HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
//	        connection.setRequestMethod("HEAD");
//	        connection.connect();
//	        int responseCode = connection.getResponseCode();
//	        
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
//	        wait.until(ExpectedConditions.stalenessOf(brokenLinkElement)); // Wait until the link becomes stale or disappears
//	        
//	        Assert.assertNotEquals(responseCode, HttpURLConnection.HTTP_NOT_FOUND, "The link is broken (HTTP response code: " + responseCode + ").");
//	    } catch (Exception e) {
//	        Assert.fail("An exception occurred while checking the link status: " + e.getMessage());
//	    }
//	}

	@Test(priority = 1)
	public void testFindDuplicateLinksInForm() {
	    List<WebElement> cards = driver.findElements(By.cssSelector(".grid.ui-fluid .card"));
	    Set<String> seenUrls = new HashSet<>();
	    Set<String> duplicateUrls = new HashSet<>();

	    for (WebElement card : cards) {
	        List<WebElement> links = card.findElements(By.tagName("a"));

	        for (WebElement link : links) {
	            String url = link.getAttribute("href");
	            String text = link.getText();

	            if (url != null && !url.isEmpty()) {
	                // Check if URL has been seen before
	                if (!seenUrls.add(url)) {
	                    // If URL is already in seenUrls set, it's a duplicate
	                    duplicateUrls.add(url + " - Text: " + text);
	                }
	            }
	        }
	    }

	    if (!duplicateUrls.isEmpty()) {
	        System.out.println("Duplicate URLs found:");
	        for (String url : duplicateUrls) {
	            System.out.println(url);
	        }
	        Assert.fail("Duplicate URLs found. Test failed.");
	    } else {
	        System.out.println("No duplicate URLs found.");
	    }
	}


	 
	@Test(priority = 5)	
    public void testLinkCount() {
        String url = "https://leafground.com/grid.xhtml";
        driver.get(url);
        
        List<WebElement> links = driver.findElements(By.tagName("a"));
        int linkCount = links.size();
        System.out.println("Number of links on the page grid.xhtml: " + linkCount);
        Assert.assertTrue(linkCount > 0, "No links found on the page.");
	driver.navigate().back();
    }
	
	
//	@Test(priority = 5)	
//    public void testLinkCountAndPrintText() {
//        // Set the URL
//        String url = "https://leafground.com/grid.xhtml";
//        driver.get(url);
//        
//        List<WebElement> links = driver.findElements(By.cssSelector(".ui-column-title"));
//        int linkCount = links.size();
//        System.out.println("Number of links with class 'ui-column-title': " + linkCount);
//        
//        // Print text for each link
//        for (WebElement link : links) {
//            System.out.println("Link Text: " + link.getText());
//        }
//        
//        Assert.assertTrue(linkCount > 0, "No links found with class 'ui-column-title' on the page.");
//
//        // Navigate back to the previous page
//        driver.navigate().back();
//    }
	
	
	 @Test(priority = 6)	
	    public void testLayoutLinkCountAndPrintText() {
		 String url = "https://leafground.com/list.xhtml";
	        driver.get(url);
	        List<WebElement> layoutLinks = driver.findElements(By.cssSelector(".product-name"));
	        int layoutLinkCount = layoutLinks.size();
	        System.out.println("Number of layout links on the page: " + layoutLinkCount);
	        
	        for (WebElement layoutLink : layoutLinks) {
	            System.out.println("Layout Link Text: " + layoutLink.getText());
	        }
	        
	        Assert.assertTrue(layoutLinkCount > 0, "No layout links found on the page.");
	    }
	
  @AfterClass
   public void driverClose()
  {
	  driver.quit();
  }

}
