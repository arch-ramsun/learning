package com.test.hackerRank;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HackerRankLogin extends DriverSetup {

	WebDriver driver;

	@FindBy(id = "menu-item-907")
	WebElement loginLnk;

	@FindBy(xpath = "//*[@id=\"post-175\"]/div/div/div[1]/div/div/div[2]/div[1]/div/div[4]/div/div/a")
	WebElement codeLoginBtn;

	@FindBy(xpath = "//input[@id=\"login\"]")
	WebElement userName;

	@FindBy(xpath = "//*[@id=\"password\" and @data-attr2=\"Login\"]")
	WebElement password;

	@FindBy(xpath = "//*[@id=\"legacy-login\"]/div[1]/p/button")
	WebElement signInLoginBtn;
	
	@FindBy(xpath = "//*[@id=\"social-signup\"]/div[1]/a")
	WebElement facebkBtn;
	
	@FindBy(xpath="//*[@id=\"social-signup\"]/div[2]/a")
	WebElement googlePlusBtn;
	
	@FindBy(xpath="//*[@id=\"social-signup\"]/div[3]/a")
	WebElement linkedInBtn;
	
	@FindBy(xpath="//*[@id=\"social-signup\"]/div[4]/a")
	WebElement gitHubBtn;
	
	public HackerRankLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnLogin(String email, String pwd) throws AWTException, IOException {

		loginLnk.click();
		codeLoginBtn.click();

		// login to HR
		userName.sendKeys(email);
		password.sendKeys(pwd);
		//userName.sendKeys(email + Keys.TAB + pwd);
		System.out.println("username and password applied");
		ScreenshotClass.takeSnapshot(driver, "SignIn Complete");
		assertFunc();

		signInLoginBtn.click();
	}

	private void assertFunc() {

	}

}
