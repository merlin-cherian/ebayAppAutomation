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
	
	public void clickOnBuyButton()
	{
		appDriver.findElementById(getBuyButtonId()).click();
	}
	
	public void waitForReviewButton()
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(getRevButtonId()))); 
	}
	
	public String getItemName()
	{
		String itemName = appDriver.findElementById(getNameID()).getText();
		return itemName;
	}
	
	public String getItemPrice()
	{
		String itemPrice = appDriver.findElementById(getPriceID()).getText();
		return itemPrice;
	}
	
	public void clickOnReviewButton()
	{
		appDriver.findElementById(getRevButtonId()).click();
	}
	
	public boolean ifPaymentPageDisplayed()
	{
		if (appDriver.findElementByAccessibilityId("Proceed to Pay").isDisplayed())
			return true;
		else
			return false;
	}
}
