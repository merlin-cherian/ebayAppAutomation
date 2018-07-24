package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginApp {

	public LoginApp(AndroidDriver<MobileElement> appDriver)
	{
		String loginPrprtyPath = "src/login.properties";
		LoginPageObjects page = new LoginPageObjects(appDriver);
		CommonUtilities common = new CommonUtilities();
		
		try
		{
			//click on Options
			page.clickOnOptionsImage();
			
			//click on sign in
			page.clickOnSignInImage();
			
			//Enter the credentials and Sign in on first time
			String username = common.readProperties("username",loginPrprtyPath);
			String password = common.readProperties("password",loginPrprtyPath);
			
			page.enterUsername(username);
			page.enterPassword(password);
		
			//click on sign in button
			page.clickOnSignInButton();
			common.waitForLoadingPage(appDriver);
			//Click on no thanks button
			page.clickOnDenyButton();

		}
		catch(Exception e)
		{
			//click on home button
			page.clickOnHomeButton();
		}
	}
	
}
