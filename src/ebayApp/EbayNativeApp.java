package ebayApp;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EbayNativeApp {
	
	AndroidDriver app;
	
	@BeforeClass
	public void Launchapp() throws MalformedURLException, InterruptedException{
		
	DesiredCapabilities capabilities=new DesiredCapabilities();
	//Platform in which the test is running [Windows, Ubuntu]
	capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
	//Android version of the emulator 
	capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
	capabilities.setCapability("deviceName", "Android Emulator");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("platformName", "Android");

	//Specify fully qualified path of apk file
	capabilities.setCapability("app", "D:\\apk\\");
	//Specify appPackage and appActivity of the app to be tested
	capabilities.setCapability("appPackage", "com.ebay.mobile");
	capabilities.setCapability("appActivity", ".activities.eBay");
	//Default time the device waits for new command
	capabilities.setCapability("newCommandTimeout", "60");
	app =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
}
	
	@Test 
	public void Login(){ 
		app.findElement(By.id("com.ebay.mobile:id/home_sign_in_image"));
		app.findElementById("com.ebay.mobile:id/home_sign_in_image");
		app.findElementById("com.ebay.mobile:id/home_search_text").click();
		app.findElementById("android:id/search_src_text").sendKeys("65 inch TV");
		app.findElementById("android:id/search_go_btn").click();	
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		Thread.sleep(1000);
		app.quit();
	}
}
