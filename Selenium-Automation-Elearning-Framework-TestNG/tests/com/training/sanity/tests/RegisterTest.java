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
//import com.training.pom.LoginPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterTest {
	private WebDriver driver;
	private String baseUrl;
	private RegisterPOM registerPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void extentretport()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/RegisterOutput.html",true);
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
		registerPOM = new RegisterPOM(driver); 
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
	public void RegisterTest() throws InterruptedException {
		logger = extent.startTest("Register");
		logger.log(LogStatus.PASS, "Browser opened");
		registerPOM.Register();
		registerPOM.PersonalDetails("Arokiya", "Jenifer", "jeniibm@gmail.com", "8884109992");
		logger.log(LogStatus.PASS, "Personal Details Provided Successfully");
		registerPOM.Address("IBM India", "Bangalore", "560045", "1489");
		logger.log(LogStatus.PASS, "Address Provided Successfully");
		registerPOM.SetPassword("admin123", "admin123");
		registerPOM.confirm();
		logger.log(LogStatus.PASS, "Id created Successfully/ Registration Successful");
		screenShot.captureScreenShot("Register");
	}
	
	}

