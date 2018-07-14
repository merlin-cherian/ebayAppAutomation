package ebayApp;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class EbayNativeApp {
	
	AndroidDriver appDriver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
	String deviceName = "emulator-5554";
	String platformName = "Android";
	String apkPath = "D:\\Appium_Req\\eBay.apk";
	String appPackage = "com.ebay.mobile";
	String appActivity = ".activities.eBay";
	String inputFilePath = "D:\\workspace\\ebayApp\\data\\InputData.xlsx";
	
	@BeforeClass
	public void Launchapp() throws MalformedURLException, InterruptedException{
		setCapabilities launch = new setCapabilities();
		appDriver = launch.setSessionCapabilities(
				deviceName,platformName,apkPath,appPackage,appActivity);

	}
	
	@Test 
	public void Login() throws IOException{ 
		
		// Import excel sheet.
		File src=new File(inputFilePath);
		FileInputStream finput = new FileInputStream(src);
		LoginPageObjects page = new LoginPageObjects();
		appDriver.findElement(By.id(page.getSignInImage()));
		appDriver.findElementById(page.getSignInImage());
		appDriver.findElementById(page.getSearchField()).click();
			
		 // Load the workbook and data sheet
		 workbook = new XSSFWorkbook(finput); 
		 sheet= workbook.getSheetAt(0);
		 
		 for(int i=1; i<=sheet.getLastRowNum(); i++)
		 {
			 // Import data for Search Text
			 cell = sheet.getRow(i).getCell(1);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 System.out.println("data : " + cell.getStringCellValue());
			 appDriver.findElementById(page.getSearchTextBox()).sendKeys(cell.getStringCellValue());		   		
	      }
		
		appDriver.findElementById(page.getSearchButton()).click();
		
		//scroll down
		appDriver.swipe(0, 30, 0, 60, 2000);
		
		//getting info on product search screen
		ProductScreenObjects pscr = new ProductScreenObjects();
		String name = appDriver.findElementById(pscr.getNameID()).getText();
		pscr.setName(name);
		
		String price = appDriver.findElementById(pscr.getPriceID()).getText();
		pscr.setName(price);
		
		String desc = appDriver.findElementById(pscr.getDescID()).getText();
		pscr.setName(desc);
		
		//Add to cart
		
		//getting info on checkout screen
		
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		Thread.sleep(1000);
		appDriver.quit();
	}
}
