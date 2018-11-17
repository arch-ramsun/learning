package com.test.hackerRank;

import org.testng.annotations.Test;

import com.test.hackerRank.HRDashboard;

import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class POM_HR_Test extends DriverSetup {

	private WebDriver driver;
	public static String timeStmpFlder = new SimpleDateFormat("yyMMddHHmm'\\'").format(new Date());
	public static String fileWithPath = Constants.resultPath;
	public static Object[] excelReqmnts;
	HackerRankLogin loginPg;

	@Test
	public void login() throws AWTException, IOException {
		loginPg = new HackerRankLogin(driver);

		loginPg.clickOnLogin(Constants.userName, Constants.pwd);
		System.out.println("First step completed");
	}

	@Test(priority = 2)
	public void viewDashbard() throws IOException {
		HRDashboard dshBrdvw = new HRDashboard(driver);
		List<WebElement> practiceElements = dshBrdvw.skillOptions();
		System.out.println("Second step completed");
		for (WebElement el : practiceElements) {
			dshBrdvw.listOfQns(el);
		}
	}

	@BeforeTest
	public void setUp() {
		this.driver = setDriver();
		File timeStmpPath = new File(fileWithPath + timeStmpFlder);
		timeStmpPath.mkdir();
		ScreenshotClass.setScreenShotFldr(fileWithPath + timeStmpFlder);
	}

	@BeforeMethod
	public void waitForPgLoad() {
		waitForLoad(driver);
		//((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%';");
	}

	@AfterTest(enabled = true)
	public void afterTest() {
		ScreenshotClass.addScreenshotsToDoc(fileWithPath, timeStmpFlder);
		exitTest();
	}

}
