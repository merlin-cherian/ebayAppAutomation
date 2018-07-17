package ebayApp;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	String inputFilePath = "data/InputData.txt";
	LoginPageObjects page;
	
	WebDriverWait wait;

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
		
		System.out.println("before creating driver");
		appDriver = new AndroidDriver<MobileElement> (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("after creating driver");
	}
	
	@Test 
	public void Login() throws IOException, InterruptedException{ 
		
		File src=new File(inputFilePath);
		FileReader filereader = new FileReader(src);
		BufferedReader bufferedReader = new BufferedReader(filereader);
		
		page = new LoginPageObjects();
		ProductScreenObjects pscr = new ProductScreenObjects();
		ReviewScreenObjects rscr = new ReviewScreenObjects();
		CommonUtilities common = new CommonUtilities();
		WebDriverWait wait = new WebDriverWait(appDriver,20);
	
		String line = "";
		common.waitForLoadingPage(appDriver);
		
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
		common.waitForLoadingPage(appDriver);
		MobileElement denyButtonId = (MobileElement) appDriver.findElementById(page.getDenyButtonId());
		denyButtonId.click();
		
		//Wait for page to load
		wait.until(ExpectedConditions.elementToBeClickable(By.id(page.getSearchTextBoxId())));
		appDriver.findElementById(page.getSearchTextBoxId()).click();
		
		while ((line = bufferedReader.readLine()) != null)
		{
			System.out.println("data : " + line);
			appDriver.findElementById(page.getSearchBoxId()).clear();
			appDriver.findElementById(page.getSearchBoxId()).sendKeys(line);
			appDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			
			//Wait for Page loading
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(page.getSortId())));

			//Scrolling down
			new TouchAction((MobileDriver) appDriver).press(PointOption.point(100, 100));
			new TouchAction((MobileDriver) appDriver).press(PointOption.point(100, 100))
			.moveTo(PointOption.point(100, 0)).release().perform();
			
			appDriver.findElements(By.id(page.getItemId())).get(1).click();

			//getting info on product search screen
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pscr.getBuyButtonId())));
				
				String name = appDriver.findElementById(pscr.getNameID()).getText();	
				String price = appDriver.findElementById(pscr.getPriceID()).getText();
			
			//Add to cart
				appDriver.findElementById(pscr.getBuyButtonId()).click();
				
			//getting info on checkout screen
				wait.until(ExpectedConditions.elementToBeClickable(By.id(rscr.getRevButtonId()))); 
				String revName = appDriver.findElementById(rscr.getNameID()).getText();		
				String revPrice = appDriver.findElementById(rscr.getPriceID()).getText();
				
				//validation of product details
				Assert.assertFalse(!name.equals(revName));
				Assert.assertFalse(!price.equals(revPrice));
				
				common.waitForLoadingPage(appDriver);
				appDriver.findElementById(rscr.getRevButtonId()).click();
				
				//closing the page after loading
				//System.out.println("before closing");
				common.waitForLoadingPage(appDriver);
				appDriver.findElementById(page.getOptionsImageId()).click();
				
				appDriver.findElementById(page.getSearchButtonId()).click();
				
				line = "";
				
		 }
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		appDriver.quit();
	}
}
