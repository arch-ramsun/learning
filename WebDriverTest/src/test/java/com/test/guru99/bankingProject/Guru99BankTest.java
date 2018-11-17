package com.test.guru99.bankingProject;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Guru99BankTest extends DriverSetup {

	public WebDriver driver;

	@Test(enabled = false)
	@Parameters("testDriver")
	public void login(@Optional("firefox") String testDriver) throws IOException {

		Util xlUtil = new Util();
		List<List<String>> loginCred = new ArrayList<>();
		loginCred = xlUtil.readFromExcel();

		for (int i = 0; i < loginCred.size(); i++) {
			this.driver = setDriver(testDriver);
			Login loginObj = new Login(driver);

			if (loginObj.guru99BankLogin(loginCred.get(i)))
				System.out.println("Test case " + Integer.valueOf(i + 1) + " : Passed");
			else
				System.out.println("Test case " + Integer.valueOf(i + 1) + " : Failed");

			exitCaseTest();
		}
	}

	/*@DataProvider(name = "guruTest")
	public String[][] testData() throws Exception {
		return Util.readStringFromExcel("Sheet1");
	}*/

	/*
	 * @Test(enabled=false)
	 * 
	 * @Parameters("testDriver") public void setUpDriver(@Optional("firefox")
	 * String testDriver) { this.driver = setDriver(testDriver);
	 * 
	 * }
	 */

	@Test(dataProvider = "guruTest", enabled = false) // , dependsOnMethods = { "setUpDriver" })
	public void loginExcel(String caseNum, String userName, String password, String result) throws IOException {
		this.driver = setDriver("firefox");
		Login loginObj = new Login(driver);
		try {
			if (userName != null) {
				if (loginObj.dpLogin(userName, password, result))
					System.out.println("Test case " + Integer.valueOf(caseNum) + " : Passed");
				else
					System.out.println("Test case " + Integer.valueOf(caseNum) + " : Failed");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			exitCaseTest();
		}
	}
	
	@DataProvider(name = "classDataTest")
	public Object[][] testData2() throws Exception {
		LoginInformation[] loginData = Util.readDataFromExcel("Sheet1");
		Object[][] data = new Object[loginData.length][];
		for (int i = 0; i < loginData.length; i++) {
			data[i] = new Object[] { loginData[i] };
		}
		return data;
	}
	
	@Test(dataProvider = "classDataTest") // , dependsOnMethods = { "setUpDriver" })
	public void loginExcel(LoginInformation info) throws IOException {
		this.driver = setDriver("firefox");
		Login loginObj = new Login(driver);
		try {
			if (info.getUserName() != null) {
				if (loginObj.dpLogin(info.getUserName(),info.getPassword(),info.getResult()))
					System.out.println("Test case " + info.getCaseNum() + " : Passed");
				else
					System.out.println("Test case " + info.getCaseNum() + " : Failed");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			exitCaseTest();
		}

	}
	
	@AfterTest
	public void exitTesting(){
		exitTest();
	}
}
