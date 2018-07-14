package ebayApp;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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
	String appActivity = ".activities.MainActivity";
	String inputFilePath = "D:\\workspace\\ebayApp\\data\\InputData.xlsx";
	
	@BeforeClass
	public void Launchapp() throws MalformedURLException, InterruptedException{
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//Platform in which the test is running [Windows, Ubuntu]
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		//Android version of the emulator 
		capabilities.setCapability(CapabilityType.VERSION, "5.5.1");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platformName);
	
		//Specify fully qualified path of apk file
		capabilities.setCapability("app", apkPath);
		//Specify appPackage and appActivity of the app to be tested
		capabilities.setCapability("appPackage",appPackage);
		capabilities.setCapability("appActivity",appActivity);
		
		//Default time the device waits for new command
		capabilities.setCapability("newCommandTimeout", "60");
		System.out.println("before creating driver");
		appDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("after creating driver");
	}
	
	@Test 
	public void Login() throws IOException{ 
		
		// Import excel sheet.
		File src=new File(inputFilePath);
		FileInputStream finput = new FileInputStream(src);
		LoginPageObjects page = new LoginPageObjects();
		
		System.out.println("nnnnn");
		//appDriver.findElement(By.id(page.getSignInImage()));
		//appDriver.findElementById(page.getSignInImage());
		
		//appDriver.findElementById(page.getSearchTextBox()).click();
			
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
	      
			appDriver.findElementById(page.getSearchButton()).click();
			
			//scroll down
//			appDriver.swipe(0, 30, 0, 60, 2000);
			
			//getting info on product search screen
//			ProductScreenObjects pscr = new ProductScreenObjects();
//			String name = appDriver.findElementById(pscr.getNameID()).getText();
//			pscr.setName(name);
//			
//			String price = appDriver.findElementById(pscr.getPriceID()).getText();
//			pscr.setName(price);
//			
//			String desc = appDriver.findElementById(pscr.getDescID()).getText();
//			pscr.setName(desc);
			
			//Add to cart
			
			//getting info on checkout screen
		 }
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		appDriver.quit();
	}
}
