package com.TimesheetDemo.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.TimesheetDemo.Commons.IdentifyDriver;
import com.TimesheetDemo.Commons.WebConfigurations;
import com.TimesheetDemo.DataFetch.*;

import ExplicitWaitFunctions.ExplicitWaitFunction;

public class Login implements Timesheet_details{
	
	WebDriver driver;
	public  static ExplicitWaitFunction waitFunction;
	
	public void launchLoginScreen()
	{
		IdentifyDriver id = new IdentifyDriver();
		String browserName = id.findDriver();
		driver = id.launchDriver(browserName);
		String URL = id.findURL();
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		waitFunction = new ExplicitWaitFunction(this.driver);
		waitFunction.waitTitleContains(loginHeader);
		Assert.assertEquals(driver.getTitle(), loginHeader);
	}

	public WebDriver loginWithValidCrendentials(String username,String password)
	{
		WebConfigurations config = new WebConfigurations(this.driver);
		config.clickOnElement(userNamePath);
		config.clearAndSetValue(userNamePath,username );
		config.clickOnElement(passwordPath);
		config.clearAndSetValue(passwordPath, password);
		config.clickOnElement(loginButton);
		waitFunction.waitURLContains(homePageURL);
		waitFunction.waitElementToBeInvisible(By.xpath(loaderpath));
		Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
		return this.driver;
	}
}
