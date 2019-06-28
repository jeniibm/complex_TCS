package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.training.pom.AffiliatesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AffiliatesTest {
	private WebDriver driver;
	private String baseUrl;
	private AffiliatesPOM affiliatePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void extentretport()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/AffiliatesOutput.html",true);
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
		affiliatePOM = new AffiliatesPOM(driver); 
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
	@Test(groups={"Login"})
	public void LoginPage()
	{
		logger = extent.startTest("Affiliates");
		logger.log(LogStatus.PASS, "Browser opened");
		affiliatePOM.LoginPage("admin","admin@123");
		logger.log(LogStatus.PASS, "Login Successful");
	}
	@Test
	public void AddAffiliatesTest() throws InterruptedException, IOException {
		
		affiliatePOM.AffliatesIcon();
		logger.log(LogStatus.PASS, "Adding New Affiliates");
		affiliatePOM.PersonalDetails("Arokiya", "Jenifer", "jeniibm@gmail.com", "8098170700");
		logger.log(LogStatus.PASS, "PersonalDetails Entered");
		affiliatePOM.SetPassword("admin123", "admin123");
		affiliatePOM.Address("IBM India Ltd", "Bangalore", "560045","99","1489");
		logger.log(LogStatus.PASS, "Address Entered");
		affiliatePOM.saveAffiliates("Jenifer");
		logger.log(LogStatus.PASS, "Payee Name Provided");
		logger.log(LogStatus.PASS, "New Affiliate Created");
		screenShot.captureScreenShot("Affiliates");
	}
	

}
