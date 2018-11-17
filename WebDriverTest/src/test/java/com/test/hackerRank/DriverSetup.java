package com.test.hackerRank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetup {

	public static String baseUrl = "https://www.hackerrank.com/";
	public WebDriver driver;

	public WebDriver setDriver() {
		
	//	System.setProperty("webdriver.gecko.driver", "F:\\Archana\\geckodriver.exe");
	//	driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
		
		//ChromeOptions optns = new ChromeOptions();
		//optns.addArguments("window-size=800,480");
		
		//DesiredCapabilities capabilites = DesiredCapabilities.chrome();
		//capabilites.setCapability(ChromeOptions.CAPABILITY,optns);
		
		driver = new ChromeDriver(/*optns*/);
		
		//Create an object of event firing webdriver and pass the driver instance
		//EventFiringWebDriver evntWd = new EventFiringWebDriver(driver);
		
		//create an object of class webdriver listener and pass the driver instance
		//WebDriverEventListener evntListnr = new WebDriverEventListener(driver);
		//evntWd.register(evntListnr);
		//Actions act = new Actions(driver);
	
		driver.navigate().to(DriverSetup.baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public void exitTest() {
		driver.quit();
		System.out.println("The testing has successfully completed");
	}
	
	public void waitForLoad(WebDriver driver) {

		// waits for the page to load completely
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (js).executeScript("return document.readyState").equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(pageLoadCondition);
	}

}
