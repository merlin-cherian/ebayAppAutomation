package ebayApp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class SearchObjects {

	AndroidDriver<MobileElement> appDriver;
	String searchTextBoxId = "com.ebay.mobile:id/search_box";
	String searchBoxId = "com.ebay.mobile:id/search_src_text";
	String sortId = "com.ebay.mobile:id/button_sort";
	String itemId = "com.ebay.mobile:id/image";
	String searchButtonId = "com.ebay.mobile:id/menu_search";
	
	CommonUtilities common = new CommonUtilities();
	
	public SearchObjects(AndroidDriver<MobileElement> driver)
	{
		this.appDriver = driver;
	}
	
	public String getSearchButtonId() {
		return searchButtonId;
	}

	public String getSortId() {
		return sortId;
	}
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	public void setSearchButtonId(String searchButtonId) {
		this.searchButtonId = searchButtonId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSearchTextBoxId() {
		return searchTextBoxId;
	}
	public void setSearchTextBoxId(String searchTextBoxId) {
		this.searchTextBoxId = searchTextBoxId;
	}
	public String getSearchBoxId() {
		return searchBoxId;
	}
	public void setSearchBoxId(String searchBoxId) {
		this.searchBoxId = searchBoxId;
	}
	
	/*Function to click search text*/
	public void clickOnSearchText()
	{
		common.findElementId(appDriver,getSearchTextBoxId()).click();
	}
	
	/*Function to clear search box*/
	public void clearSearchBox()
	{
		common.findElementId(appDriver,getSearchBoxId()).clear();
	}
	
	/*Function to search item*/
	public void enterSearchItem(String searchItem)
	{		
		common.findElementId(appDriver,getSearchBoxId()).sendKeys(searchItem);
		appDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
	}
	
	/*Function to wait for sort id*/
	public void waitForSortId()
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getSortId())));
	}
	
	/*Function to swipe down*/
	public void swipeDown()
	{
		int dim[] = common.windowSize(appDriver);
		int x = dim[0];
		int y = dim[1];
		new TouchAction((MobileDriver) appDriver).press(PointOption.point(x, y/2));
		new TouchAction((MobileDriver) appDriver).press(PointOption.point(x, y))
		.moveTo(PointOption.point(x, 0)).release();
	}
	
	/*Function to click search item*/
	public void clickOnItem()
	{
		appDriver.findElements(By.id(getItemId())).get(1).click();
	}
}

