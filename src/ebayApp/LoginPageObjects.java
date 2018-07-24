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
	
	CommonUtilities common = new CommonUtilities();
	
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
	
	/*Function to click Options Image*/
	public void clickOnOptionsImage()
	{
		MobileElement options = common.findElementId(appDriver,getOptionsImageId());
		options.click();
	}
	
	/*Function to click SignIn Image*/
	public void clickOnSignInImage()
	{
		MobileElement signIn = common.findElementId(appDriver,getSignInImageId());
		signIn.click();
	}
	
	/*Function to enter username*/
	public void enterUsername(String username)
	{
		MobileElement userName = common.findElementId(appDriver,getUserNameId());
		userName.sendKeys(username);
	}
	
	/*Function to enter password*/
	public void enterPassword(String passwd)
	{
		MobileElement password = common.findElementId(appDriver,getPasswordId());
		password.sendKeys(passwd);
	}
	
	/*Function to click Sign In Button*/
	public void clickOnSignInButton()
	{
		MobileElement signInButton = common.findElementId(appDriver,getSignInButtonId());
		signInButton.click();
	}
	
	/*Function to click No Thanks Image*/
	public void clickOnDenyButton()
	{
		MobileElement denyButtonId = common.findElementId(appDriver,getDenyButtonId());
		denyButtonId.click();
	}
	
	/*Function to click Home Button*/
	public void clickOnHomeButton()
	{
		common.findElementId(appDriver,getHomeButtonId()).click();
	}

}
