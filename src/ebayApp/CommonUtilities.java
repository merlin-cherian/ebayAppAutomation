package ebayApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CommonUtilities {

	/*Function to read from Properties file*/
	public String readProperties(String property,String src)
	{
		Properties prop = new Properties();
		String propertyValue= "";
		InputStream input = null;
		
		try{
			input = new FileInputStream(src);
			prop.load(input);
			propertyValue = prop.getProperty(property);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
		return propertyValue;
	}
	
	/*Function to wait for page to load*/
	public void waitForLoadingPage(AndroidDriver<MobileElement> appDriver)
	{
		appDriver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	}
	
	/*Function to get the window size*/
	public int[] windowSize(AndroidDriver<MobileElement> appDriver)
	{
		//Getting dimensions of window
		int wSize[] =new int[2];
		Dimension size = appDriver.manage().window().getSize();
		int x = size.width / 2;
		int y = size.height;
		wSize[0]= x;
		wSize[1] = y;
		return wSize;
	} 
	
	/*Functions to wrap the driver functions*/
	public MobileElement findElementId(AndroidDriver<MobileElement> appDriver,String name)
	{
		MobileElement element = (MobileElement)appDriver.findElementById(name);
		return element;
		
	}
	
	public MobileElement findElementAccess(AndroidDriver<MobileElement> appDriver,String name)
	{
		MobileElement element = (MobileElement)appDriver.findElementByAccessibilityId(name);
		return element;
		
	}
	
}
