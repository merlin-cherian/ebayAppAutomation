package ebayApp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ReviewScreenObjects {
	
	AndroidDriver<MobileElement> appDriver;
	String name;
	String price;
	String nameID = "com.ebay.mobile:id/item_title";
	String priceID= "com.ebay.mobile:id/textview_item_price";
	String revButtonId = "com.ebay.mobile:id/take_action";
	String buyButtonId = "com.ebay.mobile:id/button_bin";
	String payPage = "Proceed to Pay";
	CommonUtilities common = new CommonUtilities();
	
	public ReviewScreenObjects(AndroidDriver<MobileElement> driver)
	{
		this.appDriver = driver;
	}
	
	public String getRevButtonId() {
		return revButtonId;
	}
	public void setRevButtonId(String revButtonId) {
		this.revButtonId = revButtonId;
	}
	public String getBuyButtonId() {
		return buyButtonId;
	}
	public void setBuyButtonId(String buyButtonId) {
		this.buyButtonId = buyButtonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNameID() {
		return nameID;
	}
	public void setNameID(String nameID) {
		this.nameID = nameID;
	}
	public String getPriceID() {
		return priceID;
	}
	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}
	
	/*Function to click Buy Button*/
	public void clickOnBuyButton()
	{
		common.findElementId(appDriver,getBuyButtonId()).click();
	}
	
	/*Function to wait for review button*/
	public void waitForReviewButton()
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(getRevButtonId()))); 
	}
	
	/*Function to get item name*/
	public String getItemName()
	{
		String itemName = common.findElementId(appDriver,getNameID()).getText();
		return itemName;
	}
	
	/*Function to get item price*/
	public String getItemPrice()
	{
		String itemPrice = common.findElementId(appDriver,getPriceID()).getText();
		return itemPrice;
	}
	
	/*Function to click Review Button*/
	public void clickOnReviewButton()
	{
		common.findElementId(appDriver,getRevButtonId()).click();
	}
	
	/*Function to check if payment page is displayed*/
	public boolean ifPaymentPageDisplayed()
	{
		if (common.findElementAccess(appDriver,payPage).isDisplayed())
			return true;
		else
			return false;
	}
}
