package com.test.hackerRank;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.hackerRank.ScreenshotClass;

public class HRDashboard extends DriverSetup {

	public WebDriver driver;

	@FindBy(xpath = "//div[@class='my-tracks']//descendant::h3")
	List<WebElement> yourSkills;
	
	@FindBy(xpath = "//button[contains(@class,'ui-btn ui-btn-normal ui-btn-large')]")
									//  ui-btn ui-btn-normal ui-btn-large ui-btn-line-primary	
	List<WebElement> continuePractice;
	
	String dashBoardTitle = "https://www.hackerrank.com/dashboard";

	public HRDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		((JavascriptExecutor)driver).executeScript("document.body.style.zoom='80%';");
	}

	public List<WebElement> skillOptions() throws IOException {

		WebDriverWait exWait = new WebDriverWait(driver, 25);
		exWait.until(ExpectedConditions.urlToBe(dashBoardTitle));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		ScreenshotClass.takeSnapshot(driver, "dashboard view");
		js.executeScript("window.scrollBy(0,400)");
		ScreenshotClass.takeSnapshot(driver, "dashBoard view2");
		js.executeScript("window.scrollBy(0,500)");
		ScreenshotClass.takeSnapshot(driver, "dashBoard view3");
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		ScreenshotClass.takeSnapshot(driver, "dashBoard view4");
		//System.out.println("number of skills:" + baseCardTitle.size());
		
						
		if(yourSkills.size()>0)
			return yourSkills;
		else
			throw new NoSuchElementException();
		
		
		/*for (WebElement opt : baseCardTitle) {
			System.out.println(opt.getText());
			System.out.println();
			if(opt.getText().equalsIgnoreCase("Problem Solving")){
				WebElement practiceButton = driver.findElement(By.xpath("//button[contains(@class,'ui-btn ui-btn-normal')]"));
				practiceButton.click();
				
			}
		}
		
		for(WebElement practiceBtn: continuePractice){
			System.out.println(practiceBtn.getText());
		}*/
	}

	public void listOfQns(WebElement el) {
		// TODO Auto-generated method stub
		
		
	}
}
