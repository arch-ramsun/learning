package com.test.hackerRank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sun.jna.platform.FileUtils;

public class ScreenshotClass extends DriverSetup {

	/**
	 * This function will take screenshot and place it in the specific folder
	 * 
	 * @param WebDriver
	 * @param filePath
	 * @param functionName
	 * @throws Exception
	 */

	WebDriver driver;

	public static String fileWithPath = "";
	public static String extnsn = ".png";

	public ScreenshotClass(WebDriver driver) {
		driver = this.driver;
	}

	public static void takeSnapshot(WebDriver webdriver, String functionality) throws IOException {

		TakesScreenshot tkScrnshot = ((TakesScreenshot) webdriver);

		File srcFile = tkScrnshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(fileWithPath + functionality + extnsn);

		Files.copy(srcFile.toPath(), destFile.toPath());
	}

	public static void setScreenShotFldr(String fileWithPath2) {
		fileWithPath = fileWithPath2;
	}

	public static void addScreenshotsToDoc(String fileSavePath, String timeStmpFlder) {

		List<String> imgFiles = new ArrayList<>();
		File fileDirectory = new File(fileWithPath);
		File[] fileLists = fileDirectory.listFiles();
		
		try {
			XWPFDocument docx = new XWPFDocument();
			XWPFRun run = docx.createParagraph().createRun();
			File snapShotDoc = new File(
					fileWithPath + "\\" + timeStmpFlder.substring(0, timeStmpFlder.length() - 1) + ".docx");
			snapShotDoc.createNewFile();
			FileOutputStream out = new FileOutputStream(snapShotDoc);

			run.setText(timeStmpFlder);
			for (int i = 0; i < fileLists.length; i++) {
				System.out.println("File Save Path: " + fileWithPath);
				System.out.println("File Name: " + fileLists[i].getName());
				System.out.println("Full Path: " + fileWithPath + fileLists[i].getName());
				
				InputStream pic = new FileInputStream(fileWithPath + fileLists[i].getName());
				run.addBreak();
				run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, fileLists[i].getName(), Units.toEMU(500),
						Units.toEMU(300));
				pic.close();
				run.setText(fileLists[i].getName());
				run.addCarriageReturn();
				TimeUnit.SECONDS.sleep(2);

			}
			docx.write(out);
			out.flush();
			out.close();
			docx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
