package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginApp {

	public LoginApp(AndroidDriver<MobileElement> appDriver)
	{
		String loginPrprtyPath = "src/login.properties";
		LoginPageObjects page = new LoginPageObjects();
		CommonUtilities common = new CommonUtilities();
		
		try
		{
			//click on Options
			MobileElement options = (MobileElement) appDriver.findElementById(page.getOptionsImageId());
			options.click();
			
			MobileElement signIn = (MobileElement) appDriver.findElementById(page.getSignInImageId());
			signIn.click();
			
			//Enter the credentials and Sign in on first time
			MobileElement userName = (MobileElement) appDriver.findElementById(page.getUserNameId());
			userName.sendKeys(common.readProperties("username",loginPrprtyPath));
			MobileElement password = (MobileElement) appDriver.findElementById(page.getPasswordId());
			password.sendKeys(common.readProperties("password",loginPrprtyPath));
		
			MobileElement signInButton = (MobileElement) appDriver.findElementById(page.getSignInButtonId());
			signInButton.click();
			common.waitForLoadingPage(appDriver);
			MobileElement denyButtonId = (MobileElement) appDriver.findElementById(page.getDenyButtonId());
			denyButtonId.click();
		}
		catch(Exception e)
		{
			//click on home button
			appDriver.findElementById(page.getHomeButtonId()).click();
		}
	}
	
}
