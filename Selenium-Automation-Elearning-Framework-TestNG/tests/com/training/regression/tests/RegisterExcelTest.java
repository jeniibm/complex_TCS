package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.RegisterDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterExcelTest {
	
	private WebDriver driver;
	private String baseUrl;
	private RegisterPOM registerPOM;
	private static Properties properties;
	private ScreenShot screenShot;

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
	}
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = RegisterDataProviders.class)
	public void RegisterTest(String firstName, String lastName, String email, String phone) throws InterruptedException {
		registerPOM.Register();
		registerPOM.PersonalDetails(firstName, lastName, email, phone);
		registerPOM.Address("IBM India", "Bangalore", "560045", "1489");
		registerPOM.SetPassword("admin123", "admin123");
		registerPOM.confirm();
		screenShot.captureScreenShot("Register_DB");
	}

}
