package com.test.guru99.bankingProject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSetup {

	public WebDriver driver;

	public WebDriver setDriver(String testDriver) {

		if (testDriver.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", Util.geckoDriverPath);
			File profileFile = new File(Util.ffProfilePath);
			FirefoxProfile ffProfile = new FirefoxProfile(profileFile);
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setCapability(FirefoxDriver.PROFILE, ffProfile);
			WebDriver driver = new FirefoxDriver(ffOptions);
			this.driver = driver;
		} else if (testDriver.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", Util.chromeDriverPath);
			WebDriver driver = new ChromeDriver();
			this.driver = driver;
		}
		driver.navigate().to(Util.baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Util.timeOutSec, TimeUnit.SECONDS);

		return driver;

	}

	public void exitCaseTest() {
		driver.close();
		// driver.quit();
	}
	
	public void exitTest(){
		driver.quit();
	}
}
