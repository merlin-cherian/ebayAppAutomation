package ebayApp;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EbayNativeApp {
	
	AndroidDriver app;
	String deviceName = "emulator-5554";
	String platformName = "Android";
	String apkPath = "D:\\Appium_Req\\eBay.apk";
	String appPackage = "com.ebay.mobile";
	String appActivity = ".activities.eBay";
	String searchText = "65 inch TV";
	
	@BeforeClass
	public void Launchapp() throws MalformedURLException, InterruptedException{
		setCapabilities launch = new setCapabilities();
		app = launch.setSessionCapabilities(
				deviceName,platformName,apkPath,appPackage,appActivity);

	}
	
	@Test 
	public void Login(){ 
		LoginPageObjects page = new LoginPageObjects();
		app.findElement(By.id(page.getSignInImage()));
		app.findElementById(page.getSignInImage());
		app.findElementById(page.getSearchField()).click();
		app.findElementById(page.getSearchTextBox()).sendKeys(searchText);
		app.findElementById(page.getSearchButton()).click();
		
		//scroll down
		app.swipe(0, 30, 0, 60, 2000);
		
		//getting info on product search screen
		ProductScreenObjects pscr = new ProductScreenObjects();
		String name = app.findElementById(pscr.getNameID()).getText();
		pscr.setName(name);
		
		String price = app.findElementById(pscr.getPriceID()).getText();
		pscr.setName(price);
		
		String desc = app.findElementById(pscr.getDescID()).getText();
		pscr.setName(desc);
		
		//Add to cart
		
		//getting info on checkout screen
		
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		Thread.sleep(1000);
		app.quit();
	}
}
