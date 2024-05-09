package com.TimesheetDemo.TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TimesheetDemo.DataFetch.InputTestData;
import com.TimesheetDemo.Extentreports.MyScreenRecorder;
import com.TimesheetDemo.Extentreports.TestReports;
import com.TimesheetDemo.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;





public class FillInTimesheet {
	
	WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	TestReports reportinstance = new TestReports();
	Login login = new Login();

	@BeforeClass
	public void screenrecord() throws Exception
	{
		MyScreenRecorder.startRecording("TimeSheetDemo");

	}
	@BeforeMethod
	public void getReportInstance() {
		report = reportinstance.getInstance();
	}


	@Test
	
	public void launchURL()
	{
		login.launchLoginScreen();
	}
	
	@Test(dataProvider="TestData",dataProviderClass=InputTestData.class,dependsOnMethods="launchURL")
	public void login(String userName,String password)
	{
		driver = login.loginWithValidCrendentials(userName, password);
	}
	
	@Test(dependsOnMethods="login")
	public void clickOnTimesheetEntry()
	{
		HomePage home = new HomePage(this.driver); 
		home.moveInToTimesheetEntry();
	}
	
	@Test(dependsOnMethods="clickOnTimesheetEntry")
	public void enterTimesheet()
	{
		TimesheetEntry timesheetentry = new TimesheetEntry(this.driver);
		timesheetentry.enterDetails();
	}
	
	@Test(dependsOnMethods="enterTimesheet")
	public void timesheetSave()
	{

		TimesheetEntry timesheetentry = new TimesheetEntry(this.driver);
		timesheetentry.saveDetails();
	}
	
	@AfterMethod

	public void TestResult(ITestResult result) {
		test = report.startTest(result.getName());

		if (result.getStatus() == 1) {
			test.log(LogStatus.PASS, "Test case is passed" + result.getName());
		} else if (result.getStatus() == 2) {
			test.log(LogStatus.FAIL, "Test case is Failed" + result.getName());
		} else if (result.getStatus() == 3) {
			test.log(LogStatus.SKIP, "Test case is skipped" + result.getName());
		}
		reportinstance.endTestInstance();
	
	}
	
	@AfterClass
	public void stoprec() throws Exception
	{
		TimesheetEntry timesheetentry =new TimesheetEntry(this.driver);
		timesheetentry.logout();
		MyScreenRecorder.stopRecording();

	}


}


