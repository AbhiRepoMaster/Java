package com.example.bookacar.datafetch;

import java.io.File;

import com.example.bookacar.commons.ExcelOperations;
import org.testng.annotations.DataProvider; 

public class InputTestData {

	@DataProvider(name = "TestData")
	public Object[][] datafetch() {
		ReadPropertyData readproperty = new ReadPropertyData();
		String inputdatapath = readproperty.readPropertyData().getProperty("inputdatapath");
		File f = new File(inputdatapath);
		ExcelOperations eo = new ExcelOperations();
		Object[][]  ob=eo.ExcelreadData(f);
				return ob;

	}

	
	

	
}
