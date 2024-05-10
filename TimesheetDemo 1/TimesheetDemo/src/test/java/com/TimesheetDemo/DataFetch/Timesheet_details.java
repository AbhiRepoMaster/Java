package com.TimesheetDemo.DataFetch;

public interface Timesheet_details {
	
	public String loginHeader= "iDeal - Integrated Hinduja Tech Enterprise Portal";
	public String userNamePath = "//input[@id='userName']";
	public String passwordPath = "//input[@id='passWord']";
	public String loginButton = "//input[@class='login_button']";
	public String homePageURL = "http://ideal.hindujatech.com/";
	public String timesheetEntrypath = "//div[contains(@class,'home_section_left')]//a[text()='Timesheet Entry']";
	public String loaderpath ="//div[@id='loader']";
	public String timesheetEntryURL ="http://ideal.hindujatech.com/timesheet_entries/new_timesheet_entry/";
	public String validTimesheetEntryPath = "//font[@color='green']//parent::td//parent::tr";
	public String timesheetHeader ="//div[@id='timesheet']";
	public String timesheetSaveButton ="//div[@class='actionToSave']//input[@name='buttonSave']";
	public String logoutButton = "//div[@class='header-container']//div[@id='header']//div[@id='rightpanel']//div[@id='menu']//li//a";
	public String loginURL ="http://ideal.hindujatech.com/users/login";
	
}