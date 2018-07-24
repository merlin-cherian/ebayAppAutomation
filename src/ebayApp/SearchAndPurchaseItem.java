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
	SearchObjects search;
	
	public void SearchItem(AndroidDriver<MobileElement> appDriver,String searchItem) throws IOException, InterruptedException
	{
		page = new LoginPageObjects(appDriver);
		pscr = new ProductScreenObjects(appDriver);
		search = new SearchObjects(appDriver);
		
		//Search for item
		search.clickOnSearchText();
		search.clearSearchBox();	
		search.enterSearchItem(searchItem);
		
		//Wait for Page loading
		search.waitForSortId();
		search.swipeDown();

		//Click on the item
		search.clickOnItem();
		
		//common.waitForLoadingPage(appDriver);
		String name = pscr.getItemName();	
		String price = pscr.getItemPrice();
		
		//System.out.println(name + ",," + price);
		pscr.setName(name);
		pscr.setPrice(price);

		PurchaseItem(appDriver);

	}
		
	public void PurchaseItem(AndroidDriver<MobileElement> appDriver) throws InterruptedException
	{	
		ReviewScreenObjects rscr = new ReviewScreenObjects(appDriver);
		CommonUtilities common = new CommonUtilities();
		
		String name = pscr.getName();
		String price = pscr.getPrice();
		//System.out.println(name + "--" + price);
		
		//Add to cart
		common.waitForLoadingPage(appDriver);
		rscr.clickOnBuyButton();
			
		//getting info on checkout screen
		rscr.waitForReviewButton();
			
		String revName = rscr.getItemName();
		String revPrice = rscr.getItemPrice();
			
		//validation of product details
		Assert.assertFalse(!name.equals(revName));
		Assert.assertFalse(!price.equals(revPrice));
		
		//click on review button
		common.waitForLoadingPage(appDriver);
		rscr.clickOnReviewButton();
		
		common.waitForLoadingPage(appDriver);
		//Thread.sleep(15000);
		
		if  (rscr.ifPaymentPageDisplayed())
		{
			//System.out.println("Payment Details");
			//Code details for payment options here
			//closing payment page
			page.clickOnOptionsImage();
			//clicking again on options 
			page.clickOnOptionsImage();
		}
		else
		{
			Assert.assertFalse(false);
		}
		
	}
}
