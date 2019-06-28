package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.RecoverPasswordPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RecoverPasswordTest {
	private WebDriver driver;
	private String baseUrl;
	private RecoverPasswordPOM recoverPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void extentretport()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/RecoverOutput.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
	}
	
	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		recoverPOM = new RecoverPasswordPOM(driver); 
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
	public void RecoverPasswordTest() throws InterruptedException {
		
		logger = extent.startTest("RecoverPassword");
		logger.log(LogStatus.PASS, "Browser opened");
		recoverPOM.LoginPage();
		logger.log(LogStatus.PASS, "Login Page opened");
		recoverPOM.sendUserName("jeniibm@gmail.com");
		recoverPOM.sendPassword("admin");
		recoverPOM.clickLoginBtn(); 
		logger.log(LogStatus.PASS, "Login failed due to wrong password");
		recoverPOM.RecoverPas("jeniibm@gmail.com");
		screenShot.captureScreenShot("Recover");
		logger.log(LogStatus.PASS, "Password Recovered");
		

	}


}
