package com.orange.automation.farmework.webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import com.google.common.base.Splitter;
import com.orange.factory.BasePageFactory;
import com.orange.factory.WebPageFactory;
import com.orange.pageobjects.AdminPage;
import com.orange.pageobjects.LoginPage;
import com.orange.pageobjects.PIMPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static String dataFilepath = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx";
	public WebDriver driver;
	public BasePageFactory pageFactory;
	public static Actions a;
	public static FileInputStream f;
	public static XSSFWorkbook w;
	public static XSSFSheet s;
	public static XSSFRow r;
	public static XSSFCell c;
	public static FileOutputStream Out;
	public LoginPage lp;
	public AdminPage ap;
	public PIMPage pp;
	public static Map<String, String> testDataMap = new HashMap();

	public BasePageFactory getPageFactory() {
		pageFactory = new WebPageFactory(getDriver());
		return pageFactory;
	}

	public static WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	@BeforeMethod
	public void openApp() throws InterruptedException, AWTException {
		setUP();
		lp=getPageFactory().getLoginPage();
		pp=getPageFactory().getPIMPage();
		ap=getPageFactory().getAdminPage();
	}

	public synchronized void setUP() throws InterruptedException, AWTException {
		String browserName = "chrome";
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			DriverManager.setWebDriver(driver);
		} else if (browserName.equals("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			DriverManager.setWebDriver(driver);
		} else if (browserName.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			DriverManager.setWebDriver(driver);
		}
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DriverManager.getDriver().get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		// DriverManager.getDriver().get("https://ui.cogmento.com/");
		Thread.sleep(1000);
		a = new Actions(getDriver());

		lp = getPageFactory().getLoginPage();
		ap = getPageFactory().getAdminPage();
		pp = getPageFactory().getPIMPage();
	}

	public void upLoadFile(String filePath) throws InterruptedException, AWTException {
		Robot r = new Robot();
		StringSelection s = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	public static void pressTab() {

		a.sendKeys(Keys.TAB).perform();
	}

	public static void pressEnter() {
		a.sendKeys(Keys.ENTER).perform();
	}

	public static void mouseHover(WebElement ele) {
		a.moveToElement(ele).perform();
	}

	public static String readTestData(String dataFilePath, String sheetName, int rowNum, int colNum) {
		try {
			f = new FileInputStream(dataFilePath);
			w = new XSSFWorkbook(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s = w.getSheet(sheetName);
		r = s.getRow(rowNum);
		c = r.getCell(colNum);

		return c.getStringCellValue();

	}

	public static int getRowNum(String tcName, String sheetName) {
		int tcStartRow = 0;
		while (!readTestData(dataFilepath, sheetName, tcStartRow, 0).equals(tcName)) {
			tcStartRow++;
		}

		return tcStartRow;
	}

	public static Object[][] getData(String tcName, String sheetName) {
		int tcStartRow = 0;
		while (!readTestData(dataFilepath, sheetName, tcStartRow, 0).equals(tcName)) {
			tcStartRow++;
		}

		System.out.println(tcStartRow);

		int cols = 0;
		int colStartRow = tcStartRow + 1;
		while (!readTestData(dataFilepath, sheetName, colStartRow, cols).equals("N")) {
			cols++;
		}
		System.out.println(cols);

		int rows = 0;
		int dataStartRow = tcStartRow + 2;
		while (!readTestData(dataFilepath, sheetName, dataStartRow + rows, 0).equals("N")) {
			rows++;
		}
		System.out.println(rows);
		Object[][] data = new Object[rows][1];
		Map<String, String> map;
		int index = 0;
		for (int i = dataStartRow; i < dataStartRow + rows; i++) {
			map = new HashMap();
			for (int j = 0; j < cols; j++) {
				// System.out.println(readTestData(dataFilepath, sheetName, i, j));
				String key = readTestData(dataFilepath, sheetName, colStartRow, j);
				// System.out.println(key);
				String value = readTestData(dataFilepath, sheetName, i, j);
				// data[index][j]=readTestData(dataFilepath, sheetName, i, j);
				map.put(key, value);
			}
			data[index][0] = map;
			index++;
		}

		return data;
	}

	public static void main(String[] args) {
		// readTestData(System.getProperty("user.dir")+"/src/test/resources/TestData/TestData.xlsx",
		// "Sheet1", 1, 0);
		getData("TC1_addEmployeeFromPIM", "Sheet1");
		// System.out.println(writeSpecificData("TC2_ProvideAdminRights","Sheet1","EmpoyeeName","Test
		// User"));
	}

	public static boolean writeSpecificData(String tcName, String sheetName, String colName, String data) {
		try {
			FileInputStream f = new FileInputStream(dataFilepath);
			w = new XSSFWorkbook(f);
			s = w.getSheet(sheetName);
			int colNum = 0;
			int tcStartRow = 0;
			while (!readTestData(dataFilepath, sheetName, tcStartRow, 0).equals(tcName)) {
				tcStartRow++;
			}
			r = s.getRow(tcStartRow + 1);
			for (int i = 0; i < r.getLastCellNum(); i++) {
				if (r.getCell(i).getStringCellValue().trim().equals(colName)) {
					colNum = i;
					System.out.println("coming");
					break;
				}
			}

			s.autoSizeColumn(colNum);
			r = s.getRow(tcStartRow + 2);
			if (r == null)
				r = s.createRow(tcStartRow + 2);

			c = r.getCell(colNum);
			c = r.createCell(colNum);
			c.setCellValue(data);
			Out = new FileOutputStream(dataFilepath);

			w.write(Out);
			Out.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void readTestDataFromExcel(String appType, String testDataType) {

		try {

			readSpecificTestData("", "", testDataType);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readSpecificTestData(String fileName, String sheetName, String testDataType) throws IOException {

		StringBuffer testDataBeforeSplit = new StringBuffer();
		File file = new File(fileName);

		try {
			f = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String extension = fileName.substring(fileName.indexOf("."));
		if (extension.equalsIgnoreCase(".xlsx")) {
			try {
				w = new XSSFWorkbook(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

		}

		s = w.getSheet(sheetName);
		int rowCount = s.getLastRowNum() - s.getFirstRowNum();
		main: for (int i = 1; i <= rowCount; i++) {

			Row row = s.getRow(i);

			if (row.getCell(0).toString().equalsIgnoreCase(testDataType)
					&& row.getCell(1).toString().equalsIgnoreCase("Yes")) {

				int noOfCells = row.getPhysicalNumberOfCells();
				for (int j = 2; j < row.getPhysicalNumberOfCells() - 1; j++) {
					if (j + 1 == row.getPhysicalNumberOfCells() - 1) {
						testDataBeforeSplit.append(row.getCell(j).toString());

					} else {
						testDataBeforeSplit.append(row.getCell(j).toString() + "\n");
					}

				}

				testDataMap = splitToMap(testDataBeforeSplit.toString());

				break main;
			}
		}

		f.close();

	}

	public static Map<String, String> splitToMap(String data) {

		try {
			testDataMap = Splitter.on("\n").withKeyValueSeparator("~").split(data);

		} catch (Exception exp) {

		}

		return testDataMap;

	}

}
