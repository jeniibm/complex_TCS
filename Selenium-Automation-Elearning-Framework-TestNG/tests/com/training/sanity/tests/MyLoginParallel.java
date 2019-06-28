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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.MyLoginParallelPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MyLoginParallel {

		private static WebDriver driver;
		private static String baseUrl;
		private static MyLoginParallelPOM loginPOM;
		private static Properties properties;
		private static ScreenShot screenShot;
		ExtentReports extent;
		ExtentTest logger;
		
		@BeforeTest
		public void extentretport()
		{
			extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyLoginOutput.html",true);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			
		}
		
		@BeforeClass
		public static void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
			
		}

		
		@BeforeMethod
		@Parameters("mybrowser")
		public void setUp(String mybrowser) throws Exception {
		if (mybrowser.equalsIgnoreCase("chrome"))
		{
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			Thread.sleep(3000);
			
		}
		else if (mybrowser.equalsIgnoreCase("firefox"))
		{
			driver = DriverFactory.getDriver(DriverNames.FIREFOX);	
			Thread.sleep(3000);
		}
		
		loginPOM = new MyLoginParallelPOM(driver); 
		baseUrl = properties.getProperty("BaseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		Thread.sleep(3000);
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
		
		
		@Test()
		public void validLoginTest() throws InterruptedException, IOException {
		
			logger = extent.startTest("MyLogin");
			logger.log(LogStatus.PASS, "Browser opened");
			loginPOM.login("admin","admin@123");
			loginPOM.buttonClick();
			logger.log(LogStatus.PASS, "Application Opened");
			logger.log(LogStatus.PASS, "Login Successfully");
			screenShot.captureScreenShot("MyLogin");
		}

	}


