package ebayApp;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class SearchAndPurchaseItem {
	
	ProductScreenObjects pscr;
	LoginPageObjects page;
	
	public void SearchItem(AndroidDriver<MobileElement> appDriver,String searchItem) throws IOException, InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		
		page = new LoginPageObjects();
		pscr = new ProductScreenObjects();
		CommonUtilities common = new CommonUtilities();
		
		appDriver.findElementById(page.getSearchTextBoxId()).click();	
		appDriver.findElementById(page.getSearchBoxId()).clear();
		appDriver.findElementById(page.getSearchBoxId()).sendKeys(searchItem);
		appDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		
		//Wait for Page loading
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(page.getSortId())));

		int dim[] = common.windowSize(appDriver);
		int x = dim[0];
		int y = dim[1];
		new TouchAction((MobileDriver) appDriver).press(PointOption.point(x, y/2));
		new TouchAction((MobileDriver) appDriver).press(PointOption.point(x, y))
		.moveTo(PointOption.point(x, 0)).release();
		
		appDriver.findElements(By.id(page.getItemId())).get(1).click();
		
		//common.waitForLoadingPage(appDriver);
		String name = appDriver.findElementById(pscr.getNameID()).getText();	
		String price = appDriver.findElementById(pscr.getPriceID()).getText();
		
		//System.out.println(name + ",," + price);
		pscr.setName(name);
		pscr.setPrice(price);

		PurchaseItem(appDriver);

	}
		
	public void PurchaseItem(AndroidDriver<MobileElement> appDriver) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		
		ReviewScreenObjects rscr = new ReviewScreenObjects();
		CommonUtilities common = new CommonUtilities();
		
		String name = pscr.getName();
		String price = pscr.getPrice();
		//System.out.println(name + "--" + price);
		
		//Add to cart
		common.waitForLoadingPage(appDriver);
		appDriver.findElementById(rscr.getBuyButtonId()).click();
			
		//getting info on checkout screen
		wait.until(ExpectedConditions.elementToBeClickable(By.id(rscr.getRevButtonId()))); 
			
		String revName = appDriver.findElementById(rscr.getNameID()).getText();		
		String revPrice = appDriver.findElementById(rscr.getPriceID()).getText();
			
		//validation of product details
		Assert.assertFalse(!name.equals(revName));
		Assert.assertFalse(!price.equals(revPrice));
		
		//click on review button
		common.waitForLoadingPage(appDriver);
		appDriver.findElementById(rscr.getRevButtonId()).click();
		
		common.waitForLoadingPage(appDriver);
		Thread.sleep(15000);
		
		if  (appDriver.findElementByAccessibilityId("Proceed to Pay").isDisplayed())
		{
			//System.out.println("Payment Details");
			//Code details for payment options here
			//closing payment page
			appDriver.findElementById(page.getOptionsImageId()).click();
			//clicking again on options 
			appDriver.findElementById(page.getOptionsImageId()).click();
		}
		else
		{
			Assert.assertFalse(false);
		}
		
	}
}
