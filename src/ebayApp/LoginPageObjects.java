package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginPageObjects {
	
	AndroidDriver<MobileElement> appDriver;
	String optionsImageId = "com.ebay.mobile:id/home";
	String signInImageId ="com.ebay.mobile:id/logo";
	
	String userNameId = "com.ebay.mobile:id/edit_text_username";
	String passwordId = "com.ebay.mobile:id/edit_text_password";
	String signInButtonId = "com.ebay.mobile:id/button_sign_in";
	String denyButtonId= "com.ebay.mobile:id/button_google_deny";
	String homeButtonId= "com.ebay.mobile:id/design_menu_item_text";
	
	public LoginPageObjects(AndroidDriver<MobileElement> driver)
	{
		this.appDriver = driver;
	}
	
	public String getDenyButtonId() {
		return denyButtonId;
	}
	public void setDenyButtonId(String denyButtonId) {
		this.denyButtonId = denyButtonId;
	}
	public String getOptionsImageId() {
		return optionsImageId;
	}
	public void setOptionsImageId(String optionsImageId) {
		this.optionsImageId = optionsImageId;
	}
	public String getSignInImageId() {
		return signInImageId;
	}
	public void setSignInImageId(String signInImageId) {
		this.signInImageId = signInImageId;
	}
	public String getUserNameId() {
		return userNameId;
	}
	public void setUserNameId(String userNameId) {
		this.userNameId = userNameId;
	}
	public String getPasswordId() {
		return passwordId;
	}
	public void setPasswordId(String passwordId) {
		this.passwordId = passwordId;
	}
	public String getSignInButtonId() {
		return signInButtonId;
	}
	public void setSignInButtonId(String signInButtonId) {
		this.signInButtonId = signInButtonId;
	}

	public String getHomeButtonId() {
		return homeButtonId;
	}
	public void setHomeButtonId(String homeButtonId) {
		this.homeButtonId = homeButtonId;
	}
	
	public void clickOnOptionsImage()
	{
		MobileElement options = (MobileElement) appDriver.findElementById(getOptionsImageId());
		options.click();
	}
	
	public void clickOnSignInImage()
	{
		MobileElement signIn = (MobileElement) appDriver.findElementById(getSignInImageId());
		signIn.click();
	}
	
	public void enterUsername(String username)
	{
		MobileElement userName = (MobileElement) appDriver.findElementById(getUserNameId());
		userName.sendKeys(username);
	}
	
	public void enterPassword(String passwd)
	{
		MobileElement password = (MobileElement) appDriver.findElementById(getPasswordId());
		password.sendKeys(passwd);
	}
	
	public void clickOnSignInButton()
	{
		MobileElement signInButton = (MobileElement) appDriver.findElementById(getSignInButtonId());
		signInButton.click();
	}
	
	public void clickOnDenyButton()
	{
		MobileElement denyButtonId = (MobileElement) appDriver.findElementById(getDenyButtonId());
		denyButtonId.click();
	}
	
	public void clickOnHomeButton()
	{
		appDriver.findElementById(getHomeButtonId()).click();
	}

}
