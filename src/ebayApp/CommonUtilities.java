package ebayApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CommonUtilities {

	public String readProperties(String property)
	{
		Properties prop = new Properties();
		String propertyValue= "";
		InputStream input = null;
		
		try{
			input = new FileInputStream("C:\\Users\\Core i3\\git\\ebayAutomation\\src\\ebayApp\\login.properties");
			prop.load(input);
			propertyValue = prop.getProperty(property);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
		return propertyValue;
	}
	
	public void waitForLoadingPage(AndroidDriver<MobileElement> appDriver)
	{
		appDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
		
}
