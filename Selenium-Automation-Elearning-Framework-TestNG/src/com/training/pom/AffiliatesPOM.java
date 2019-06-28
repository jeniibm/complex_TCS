package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AffiliatesPOM {
	
	private WebDriver driver; 
	
	public AffiliatesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="input-username")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(css="button.btn.btn-primary")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//a[@class='parent']/i[@class='fa fa-share-alt fw']")
	private WebElement marketing;
	
	@FindBy(linkText="Affiliates")
	private WebElement affiliates;
	
	@FindBy(css="a.btn.btn-primary")
	private WebElement addNew;
	
	@FindBy(id="input-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-address-1")
	private WebElement address;
	
	@FindBy(id="input-city")
	private WebElement city;
	
	@FindBy(id="input-postcode")
	private WebElement postal;
	
	@FindBy(id="input-zone")
	private WebElement zone;
	
	@FindBy(id="input-password")
	private WebElement password1;
	
	@FindBy(id="input-confirm")
	private WebElement confirm_password;
	
	
	@FindBy(id="input-country")
	private WebElement country;
	
	@FindBy(linkText="Payment Details")
	private WebElement paymentTab;
	
	@FindBy(id="input-cheque")
	private WebElement payeeName;
	
	@FindBy(css="button.btn.btn-primary")
	private WebElement save;
	
	@FindBy(css="div.alert.alert-success")
	private WebElement success;
	
	
	public void LoginPage(String userName,String password) {
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear(); 
		this.password.sendKeys(password); 
		this.loginBtn.click();
	}
	
	public void AffliatesIcon() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(this.marketing).build().perform();
		Thread.sleep(3000);
		act.sendKeys(this.affiliates, Keys.ENTER).build().perform();
		Thread.sleep(3000);
		this.addNew.click();	
	}
	
	
	public void PersonalDetails(String firstname,String lastname, String email, String telephone)
	{
		this.firstname.clear();
		this.firstname.sendKeys(firstname);
		this.lastname.clear();
		this.lastname.sendKeys(lastname);
		this.email.sendKeys(email);
		this.telephone.sendKeys(telephone);
		
	}
	
	public void SetPassword(String password, String confirm)
	{
		this.password1.sendKeys(password);
		this.confirm_password.sendKeys(confirm);
		
	}
	
	public void Address(String address, String city, String postal, String country, String zone)
	{
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		this.postal.sendKeys(postal);
		Select se = new Select(this.country);
		se.selectByValue(country);
		Select se2 = new Select(this.zone);
		se2.selectByValue(zone);
	}
	
	public void saveAffiliates(String payeeName)
	{
		this.paymentTab.click();
		this.payeeName.sendKeys(payeeName);
		this.save.click();
		String Actual_str,Actual;
		Actual_str = this.success.getText();
		Actual = Actual_str.replace("\n", "");
		Actual = Actual.replace("×","");
		String Expected = "Success: You have modified affiliates!";
		Assert.assertEquals(Actual,Expected);
	}

}
