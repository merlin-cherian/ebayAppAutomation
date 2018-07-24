package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ProductScreenObjects {

	AndroidDriver<MobileElement> appDriver;
	String name;
	String price;
	
	String nameID = "com.ebay.mobile:id/textview_item_name";
	String priceID= "com.ebay.mobile:id/textview_item_price";
	
	public ProductScreenObjects(AndroidDriver<MobileElement> driver)
	{
		this.appDriver = driver;
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
}
