package com.TimesheetDemo.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.TimesheetDemo.Commons.WebConfigurations;
import com.TimesheetDemo.DataFetch.Timesheet_details;

import ExplicitWaitFunctions.ExplicitWaitFunction;

public class TimesheetEntry implements Timesheet_details {

	WebDriver browser;
	ExplicitWaitFunction waitFunction;
	WebConfigurations config;

	public TimesheetEntry(WebDriver driver) {
		browser = driver;
		waitFunction = new ExplicitWaitFunction(browser);
		config = new WebConfigurations(browser);
	}

	public void enterDetails() {

		int framesize = browser.findElements(By.tagName("iframe")).size();

		for (int framecount = 0; framecount < framesize; framecount++)
		{
			browser.switchTo().frame(framecount);
			waitFunction.waitElementToBeVisible(By.xpath(timesheetHeader));
			List<WebElement> validentryrows = browser.findElements(By.xpath(validTimesheetEntryPath));
			System.out.println("Rowcount: "+validentryrows.size());
			if (validentryrows.size() > 0)
			{
				for (int rowcount = 1; rowcount <= validentryrows.size(); rowcount++)
				{
					String rowno = (config.retriveAttribute("(" + validTimesheetEntryPath + ")[" + rowcount + "]", "id"));
					
					String rownumber =rowno.replaceAll("[^0-9]", "");
					
					System.out.println("RowNo: "+rownumber);
					String checkboxPath = "//td//input[@type='checkbox' and contains(@id,'"+rownumber+"')]";
					String  dropdownPath ="//select[@name='res_project' and contains(@id,'"+rownumber+"')]";
					String attendedhours = "//td//input[@name='res_attended_hours' and contains(@id,'"+rownumber+"')]";
					String workedhourspath = "//td//input[@name='res_worked_hours' and contains(@id,'"+rownumber+"')]";
					String workedminpath = "//td//input[@name='res_worked_minutes' and contains(@id,'"+rownumber+"')]";
					String regularizationpath = "//td//select[@name='res_shift' and contains(@id,'regularize')and contains(@id,'"+rownumber+"')]";
					
					config.clickOnElement(checkboxPath);
					config.selectDropdownValue(dropdownPath, 1);
					config.clearAndSetValue(workedhourspath, "09");
					config.clearAndSetValue(workedminpath, "30");
					
					String attendedhoursc = config.retriveAttribute(attendedhours, "value");
					if(!(attendedhoursc.equals("09:30")))
					{
						config.selectDropdownValue(regularizationpath, 0);
					}

				}
			}
			else
			{
				browser.switchTo().defaultContent();
				logout();
			}

		}
	}

	public void saveDetails() {
		
		config.clickOnElement(timesheetSaveButton);
	}

	public void logout()  {
		
		browser.navigate().back();
		List<WebElement> logout = browser.findElements(By.xpath(logoutButton));
		//Thread.sleep(2000);
		for(WebElement each:logout)
		{
			if(each.getText().equals("Logout"))
			{
				each.click();
				break;
			}
		}

	}

}
