package com.TimesheetDemo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.TimesheetDemo.Commons.WebConfigurations;
import com.TimesheetDemo.DataFetch.Timesheet_details;

import ExplicitWaitFunctions.ExplicitWaitFunction;

public class HomePage implements Timesheet_details {
	
	WebDriver browser;
	ExplicitWaitFunction waitFunction;
	WebConfigurations config;
	
	public HomePage(WebDriver driver) {
		browser =driver;
		waitFunction = new ExplicitWaitFunction(browser);
		config = new WebConfigurations(browser);
	}

	public void moveInToTimesheetEntry()
	{
		System.out.println("Inside timesheet entry");
		config.clickOnElement(timesheetEntrypath);
		waitFunction.waitURLContains(timesheetEntryURL);
		waitFunction.waitElementToBeInvisible(By.xpath(loaderpath));
		Assert.assertEquals(browser.getCurrentUrl(), timesheetEntryURL);
	}

}
