package ebayApp;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class setCapabilities {
	
	public AndroidDriver setSessionCapabilities(String deviceName,String platformName, String app,String appPackage,String appActivity) throws MalformedURLException
	{
		AndroidDriver appDriver;
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//Platform in which the test is running [Windows, Ubuntu]
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		//Android version of the emulator 
		capabilities.setCapability(CapabilityType.VERSION, "5.5.1");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platformName);
	
		//Specify fully qualified path of apk file
		capabilities.setCapability("app", app);
		//Specify appPackage and appActivity of the app to be tested
		capabilities.setCapability("appPackage",appPackage );
		capabilities.setCapability("appActivity",appActivity );
		
		//Default time the device waits for new command
		capabilities.setCapability("newCommandTimeout", "60");
		appDriver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("after creating driver");
		return appDriver;
	}

}
