package ebayApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	String inputFilePath = "data/InputData.txt";
	
	
	public void SearchItem(AndroidDriver<MobileElement> appDriver) throws IOException
	{
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		File src=new File(inputFilePath);
		FileReader filereader = new FileReader(src);
		BufferedReader bufferedReader = new BufferedReader(filereader);
		String line = "";
		
		LoginPageObjects page = new LoginPageObjects();
		ProductScreenObjects pscr = new ProductScreenObjects();
		CommonUtilities common = new CommonUtilities();
		
		appDriver.findElementById(page.getSearchTextBoxId()).click();
		
		while ((line = bufferedReader.readLine()) != null)
		{
			System.out.println("data : " + line);
			appDriver.findElementById(page.getSearchBoxId()).clear();
			appDriver.findElementById(page.getSearchBoxId()).sendKeys(line);
			appDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			
			//Wait for Page loading
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(page.getSortId())));

			//Scrolling down
			new TouchAction((MobileDriver) appDriver).press(PointOption.point(100, 100));
			new TouchAction((MobileDriver) appDriver).press(PointOption.point(100, 100))
			.moveTo(PointOption.point(100, 0)).release().perform();
			
			appDriver.findElements(By.id(page.getItemId())).get(1).click();

			//getting info on product search screen
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pscr.getBuyButtonId())));


				common.waitForLoadingPage(appDriver);
				appDriver.findElementById(page.getOptionsImageId()).click();
				
				appDriver.findElementById(page.getSearchButtonId()).click();
				line = "";
		 }
	}
		
		public void PurchaseItem(AndroidDriver<MobileElement> appDriver)
		{
			WebDriverWait wait = new WebDriverWait(appDriver,20);
			
			ProductScreenObjects pscr = new ProductScreenObjects();
			ReviewScreenObjects rscr = new ReviewScreenObjects();
			CommonUtilities common = new CommonUtilities();
			
			String name = appDriver.findElementById(pscr.getNameID()).getText();	
			String price = appDriver.findElementById(pscr.getPriceID()).getText();
			
			//Add to cart
				appDriver.findElementById(pscr.getBuyButtonId()).click();
				
			//getting info on checkout screen
				wait.until(ExpectedConditions.elementToBeClickable(By.id(rscr.getRevButtonId()))); 
				String revName = appDriver.findElementById(rscr.getNameID()).getText();		
				String revPrice = appDriver.findElementById(rscr.getPriceID()).getText();
				
				//validation of product details
				Assert.assertFalse(!name.equals(revName));
				Assert.assertFalse(!price.equals(revPrice));
				
				common.waitForLoadingPage(appDriver);
				appDriver.findElementById(rscr.getRevButtonId()).click();
				
				//Code details for payment options here
				
		}

}
