package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.CheckOutWithoutLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CheckOutWithoutLoginTest {

	private WebDriver driver;
	private String baseUrl;
	private CheckOutWithoutLoginPOM checkOutPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void extentretport()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/CheckOutOutput.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
	}

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		checkOutPOM = new CheckOutWithoutLoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		logger.log(LogStatus.PASS, "Browser Closed");
		extent.endTest(logger);
		extent.flush();
		extent.close();
	}
	@Test
	public void ShoppingCartTest() throws InterruptedException {
		logger = extent.startTest("ShoppingCart");
		logger.log(LogStatus.PASS, "Browser opened");
		checkOutPOM.EarringMenu();
		logger.log(LogStatus.PASS, "Product has been added to Cart");
		checkOutPOM.shoppingCart();
		logger.log(LogStatus.PASS, "Product is ready to CheckOut");
		screenShot.captureScreenShot("CheckOut");
		logger.log(LogStatus.PASS, "Product CheckOut needs User Login");
	}
}
