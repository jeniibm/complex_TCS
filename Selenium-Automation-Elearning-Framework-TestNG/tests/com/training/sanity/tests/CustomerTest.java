package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.CustomerPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CustomerTest {
	
	private WebDriver driver;
	private String baseUrl;
	private CustomerPOM customerPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void extentretport()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/CustomerOutput.html",true);
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
		customerPOM = new CustomerPOM(driver); 
		baseUrl = properties.getProperty("BaseURL");
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
	public void Cust_Search_Test() throws InterruptedException {
		logger = extent.startTest("CustomerSearch");
		logger.log(LogStatus.PASS, "Browser opened");
		customerPOM.login("admin", "admin@123");
		customerPOM.buttonClick();
		logger.log(LogStatus.PASS, "Application Opened");
		customerPOM.CustomerMenu();
		logger.log(LogStatus.PASS, "Customer Menu clicked");
		logger.log(LogStatus.PASS, "Customer Name check");
		customerPOM.CustomerNameSearch("Arokiya Jenifer");
		logger.log(LogStatus.PASS, "Customer Name check Successful");
		screenShot.captureScreenShot("CustomerName");
		customerPOM.CustomerEmailSearch("jeniibm@gmail.com");
		screenShot.captureScreenShot("CustomerSearch");
		logger.log(LogStatus.PASS, "Customer Email Check Successful");
		
	}

}
