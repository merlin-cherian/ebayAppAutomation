package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class EbayNativeApp {
	
	AndroidDriver<MobileElement> appDriver;
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
		//Platform in which the test is running 
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
		capabilities.setCapability("newCommandTimeout", 60);
		System.out.println("before creating driver");
		appDriver = new AndroidDriver<MobileElement> (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("after creating driver");
	}
	
	@Test 
	public void Login() throws IOException, InterruptedException{ 
		
		// Import excel sheet.
		File src=new File(inputFilePath);
		FileInputStream finput = new FileInputStream(src);
		CommonUtilities common = new CommonUtilities();
		LoginPageObjects page = new LoginPageObjects();
		
		System.out.println("Logging in!");
		//click on Options
		MobileElement options = (MobileElement) appDriver.findElementById(page.getOptionsImageId());
		options.click();
		MobileElement signIn = (MobileElement) appDriver.findElementById(page.getSignInImageId());
		signIn.click();
		
		//Enter the credentials and Sign in
		MobileElement userName = (MobileElement) appDriver.findElementById(page.getUserNameId());
		userName.sendKeys(common.readProperties("username"));
		MobileElement password = (MobileElement) appDriver.findElementById(page.getPasswordId());
		password.sendKeys(common.readProperties("password"));

		MobileElement signInButton = (MobileElement) appDriver.findElementById(page.getSignInButtonId());
		signInButton.click();
			
		//Wait for page to load
		WebDriverWait wait = new WebDriverWait(appDriver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(page.getSignInImageId())));
		
		// Load the workbook and data sheet
		 workbook = new XSSFWorkbook(finput); 
		 sheet= workbook.getSheetAt(0);
		 
		 for(int i=0; i<sheet.getLastRowNum(); i++)
		 {
			// Import data for Search Text
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			System.out.println("data : " + cell.getStringCellValue());
			
			appDriver.findElementById(page.getSearchTextBoxId()).click();	
			appDriver.findElementById(page.getSearchBoxId()).sendKeys(cell.getStringCellValue());
			appDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			
			//Wait for Page loading
			wait(40);
			
			//scroll down
			JavascriptExecutor js = (JavascriptExecutor) appDriver;
			HashMap<String,String> scrollObject = new HashMap<String,String>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile:scroll", scrollObject);
			
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
