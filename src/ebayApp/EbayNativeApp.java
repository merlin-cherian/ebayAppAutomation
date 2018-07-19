package ebayApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EbayNativeApp {
	
	AndroidDriver<MobileElement> appDriver;
	String CapPrprtyPath = "src/capability.properties";
	LoginPageObjects page;

	@BeforeClass
	public void Launchapp() throws MalformedURLException, InterruptedException{
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		CommonUtilities common = new CommonUtilities();
		//Platform in which the test is running 
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		//Android version of the emulator 
		capabilities.setCapability(CapabilityType.VERSION, "5.5.1");
		capabilities.setCapability("deviceName", common.readProperties("deviceName",CapPrprtyPath));
		capabilities.setCapability("platformName", common.readProperties("platformName",CapPrprtyPath));
	
		//Specify fully qualified path of apk file
		capabilities.setCapability("app", common.readProperties("app",CapPrprtyPath));
		//Specify appPackage and appActivity of the app to be tested
		capabilities.setCapability("appPackage",common.readProperties("appPackage",CapPrprtyPath));
		capabilities.setCapability("appActivity",common.readProperties("appActivity",CapPrprtyPath));
		
		//System.out.println("before creating driver");
		appDriver = new AndroidDriver<MobileElement> (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		//System.out.println("after creating driver");
	}
	
	@DataProvider(name = "InputData")
	public static Object[] getFromFile() {

	  File tFile = new File("data/InputData.txt");
	  FileReader fileReader = null;
	  List<String> lines = new ArrayList<String>();
	  try {
	    String line = null;
	    fileReader = new FileReader( tFile );
	    BufferedReader bufferedReader = new BufferedReader( fileReader );
	    while ((line = bufferedReader.readLine()) != null ) { 
	      lines.add(line); 
	    }
	    bufferedReader.close();
	  } catch (FileNotFoundException e) {
	    e.printStackTrace();
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	  int testSize = lines.size();
	  
	  int i = 0;
	  Object[] data = new Object[testSize];
	  for ( String lin : lines ) {
	    data[i] = lin;
	    i++;
	  }
	  return data;
	}
	
	@Test(dataProvider = "InputData")
	public void Login(String searchItem) throws IOException, InterruptedException{ 
		
		System.out.println(searchItem);
		WebDriverWait wait = new WebDriverWait(appDriver,20);
		CommonUtilities common = new CommonUtilities();
		
		common.waitForLoadingPage(appDriver);
		
		//Logging in the application
		LoginApp login= new LoginApp(appDriver);
		
		//Search and Purchase Items
		SearchAndPurchaseItem search = new SearchAndPurchaseItem();
		search.SearchItem(appDriver,searchItem);
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException{
		//close the app
		appDriver.quit();
	}
}
