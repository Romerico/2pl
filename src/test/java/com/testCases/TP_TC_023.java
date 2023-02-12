package com.testCases;

//Testcase Description: Testing the functionality by entering "Invalid Email and Valid Password"
//Acceptance Criteria: The Application should display the Error message

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseClass;
import com.pageObjects.LoginPage;
import com.utilities.XLUtils;

public class TP_TC_023 extends BaseClass {

	//To Verify invalid Email and valid Password Sign in.
	
	@Test(dataProvider = "LoginData", priority=2301)
	public void invalidEmailLogin(String user, String pwd) throws IOException, InterruptedException {
		
		logger.info("Started TP_TC_023");
		
		LoginPage loginpage = new LoginPage(driver);
		SoftAssert softAssert = new SoftAssert();

		loginpage.clickloginlandingbtn();
		logger.info("Clicking on Login button to enter user name and password");

		loginpage.setUsername(user);
		logger.info("providing invalid username");

		loginpage.setPassword(pwd);
		logger.info("providing password");

		loginpage.clickloginbtn1(); 
		logger.info("Clicking on login button to access TwoPlugs main home page");

		softAssert.assertTrue(loginpage.displaystxtErrorMessage(), "Test Failed");

		// Validating the error message invalid email/password
						
		//Go back to Landing page
		driver.get(baseURL);
		
		softAssert.assertAll();
		logger.info("Test passed! login failed due to invalid email!");
		logger.info("Completed TP_TC_023");
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException, InterruptedException {

		// Data Provider will always return String type data Two dimensional String type
		// Array
		// Declaring Excel String path
		String path = System.getProperty("user.dir") + "/src/test/java/com/testData/TestcasesData.xlsx";

		// To Read data from Excel File
		// Reading number of rows
		int rownum = XLUtils.getRowCount(path, "InvalidLogin1");

		// Reading number of columns
		int colnum = XLUtils.getCellCount(path, "InvalidLogin1", 0);

		// rownum and colcount give the exact no of values in the Excel sheet
		// which is passed in logindata[][]
		// now the data size and array size both will be equal
		String logindata[][] = new String[rownum][colnum];

		// Reading data and storing in a 2 dimensional array
		// Starting from 1 since index 0 is the header part
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) { // Since the colunm values start from index 0

				// Extract data from XL
				// Since the data starts from index 1 for rows and 0 for col in XL sheet
				// We need to store the same value in the array
				// so the value index value for row will be i-1 since the array will
				// store the data from and it will not be taking the header values of the XL
				// sheet
				// for col its same as, the col reads from index 0 and saves it in the array in
				// index 0
				logindata[i - 1][j] = XLUtils.getCellData(path, "invalidLogin1", i, j);
			}
		}
		return logindata; // returning 2 dim arrary
	}

}
