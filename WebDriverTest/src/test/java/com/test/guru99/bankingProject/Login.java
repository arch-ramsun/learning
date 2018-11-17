package com.test.guru99.bankingProject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	public WebDriver driver;

	@FindBy(name = "uid")
	WebElement userID;

	@FindBy(name = "password")
	WebElement passwrd;

	@FindBy(name = "btnLogin")
	WebElement loginBtn;

	@FindBy(name = "btnReset")
	WebElement resetBtn;

	@FindBy(xpath = "//table/tbody/tr/td/table/tbody/tr[3]/td")
	WebElement mngrID;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// userID = mngr153309
	// pwd = mabArUn
	public Boolean guru99BankLogin(List<String> loginCred) {

		// enter username and password
		userID.sendKeys(loginCred.get(1));
		passwrd.sendKeys(loginCred.get(2));
		// clickOn Login
		loginBtn.click();

		// if login is unsuccessful, alert is displayed
		try {
			Alert alrt = driver.switchTo().alert();
			String boxTitle = alrt.getText();
			alrt.accept();
			if (boxTitle.equals(loginCred.get(3))) {
				return true;
			} else
				return false;

		} catch (NoAlertPresentException e) {
			if (driver.getTitle().equalsIgnoreCase(Util.logInExpTitle) && mngrID.getText().contains(loginCred.get(1))) {
				return true;
			} else
				return false;

		}
	}

	public Boolean dpLogin(String userName, String pwd, String result) {

		// enter username and password
		userID.sendKeys(userName);
		passwrd.sendKeys(pwd);
		// clickOn Login
		loginBtn.click();

		// if login is unsuccessful, alert is displayed
		try {
			Alert alrt = driver.switchTo().alert();
			String boxTitle = alrt.getText();
			alrt.accept();
			if (boxTitle.equals(result)) {
				return true;
			} else
				return false;

		} catch (NoAlertPresentException e) {
			if (driver.getTitle().equalsIgnoreCase(Util.logInExpTitle) && mngrID.getText().contains(userName)) {
				return true;
			} else
				return false;

		}
	}
}
