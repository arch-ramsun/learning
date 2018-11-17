package com.test.guru99.bankingProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

public class Util {

	// protected static String User_ID = "mngr153309";
	// protected static String pwd = "mabArUn";
	public static String baseUrl = "http://www.demo.guru99.com/V4/";
	public static String geckoDriverPath = "F:\\Archana\\geckodriver.exe";
	public static String chromeDriverPath = "F:\\Archana\\chromedriver.exe";
	public static String ffProfilePath = "C:\\Users\\Home\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\lt7z9snl.SeleniumUserProfile";
	public static String logInExpTitle = "Guru99 Bank Manager HomePage";
	public static int timeOutSec = 15;
	public static String xlPath = "C:\\Users\\Home\\workspace\\WebDriverTest\\src\\test\\java\\com\\test\\guru99\\bankingProject\\";
	public static String xlFileName = "TCSheet.xlsx";

	public List<List<String>> readFromExcel() throws IOException {

		List<List<String>> excelVal = new ArrayList<>();
		FileInputStream ipStream = new FileInputStream(xlPath + xlFileName);
		Workbook dataWrkbk = null;

		if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xlsx")) {
			dataWrkbk = new XSSFWorkbook(ipStream);
		} else if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xls")) {
			dataWrkbk = new HSSFWorkbook(ipStream);
		} else {
			throw new FileFormatException(xlFileName);
		}

		Sheet wrkSheet = dataWrkbk.getSheetAt(0);
		int rowCnt = wrkSheet.getLastRowNum() - wrkSheet.getFirstRowNum();

		for (int i = 1; i < rowCnt; i++) {
			Row row = wrkSheet.getRow(i);
			List<String> rowVal = new ArrayList<>();
			String cellVal = "";
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					CellType cellType = cell.getCellTypeEnum();
					if (CellType.NUMERIC.equals(cellType))
						cellVal = Double.toString(cell.getNumericCellValue());
					else
						cellVal = cell.getStringCellValue();
				}
				rowVal.add(cellVal);
			}
			excelVal.add(rowVal);
		}

		return excelVal;
	}

	public static String[][] readStringFromExcel(String sheetName) throws IOException {

		FileInputStream ipStream = new FileInputStream(xlPath + xlFileName);
		Workbook dataWrkbk = null;
		String[][] tabArray = null;

		if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xlsx")) {
			dataWrkbk = new XSSFWorkbook(ipStream);

		} else if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xls")) {
			dataWrkbk = new HSSFWorkbook(ipStream);
		} else {
			throw new FileFormatException(xlFileName);
		}

		Sheet wrkSheet = dataWrkbk.getSheet(sheetName);
		int rowCnt = wrkSheet.getLastRowNum() - wrkSheet.getFirstRowNum();
		int colCnt = wrkSheet.getRow(0).getLastCellNum() - wrkSheet.getRow(0).getFirstCellNum();
		tabArray = new String[rowCnt][colCnt];
		
		for (int i = 1; i < rowCnt; i++) {
			Row row = wrkSheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					CellType cellType = cell.getCellTypeEnum();
					if (CellType.NUMERIC.equals(cellType))
						tabArray[i][j] = Long.toString(Math.round((cell.getNumericCellValue())));
					else
						tabArray[i][j] = cell.getStringCellValue();
				}
			}
		}
		return tabArray;
	}
	
	public static LoginInformation[] readDataFromExcel(String sheetName) throws IOException {

		FileInputStream ipStream = new FileInputStream(xlPath + xlFileName);
		Workbook dataWrkbk = null;
		LoginInformation[] tabArray = null;

		if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xlsx")) {
			dataWrkbk = new XSSFWorkbook(ipStream);

		} else if (xlFileName.substring(xlFileName.indexOf('.') + 1, xlFileName.length()).equals("xls")) {
			dataWrkbk = new HSSFWorkbook(ipStream);
		} else {
			throw new FileFormatException(xlFileName);
		}

		Sheet wrkSheet = dataWrkbk.getSheet(sheetName);
		int rowCnt = wrkSheet.getLastRowNum() - wrkSheet.getFirstRowNum();
		int colCnt = wrkSheet.getRow(0).getLastCellNum() - wrkSheet.getRow(0).getFirstCellNum();
		tabArray = new LoginInformation[rowCnt-1];
		
		for (int i = 1; i < rowCnt; i++) {
			Row row = wrkSheet.getRow(i);
			double caseNum = row.getCell(0).getNumericCellValue();
			String userName = row.getCell(1).getStringCellValue();
			String password = row.getCell(2).getStringCellValue();
			String result = row.getCell(3).getStringCellValue();
			LoginInformation obj = new LoginInformation(caseNum,userName,password,result);
			tabArray[i-1] = obj;
		}
		return tabArray;
	}


}