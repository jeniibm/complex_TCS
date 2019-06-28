package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RecoverPasswordPOM {
	private WebDriver driver; 
	
	public RecoverPasswordPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='http://retail.upskills.in/account/account']")
	private WebElement account; 
	
	@FindBy(id="input-email")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(css="input.btn.btn-primary")
	private WebElement loginBtn; 
	
	@FindBy(css="div.alert.alert-danger")
	private WebElement danger; 
	
	
	@FindBy(linkText="Forgotten Password")
	private WebElement forgotten;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(css="input.btn.btn-primary")
	private WebElement recover;
	

	
	public void LoginPage() throws InterruptedException
	{
		this.account.click();
		Thread.sleep(3000);
	}
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}

	public void RecoverPas(String email) throws InterruptedException
	{
		String Actual = "Warning: No match for E-Mail Address and/or Password.";
		String Expected = this.danger.getText();
		Assert.assertEquals(Actual, Expected);
		this.forgotten.click();
		Thread.sleep(3000);
		this.email.sendKeys(email);
		this.recover.click();
		
	}
	
}
