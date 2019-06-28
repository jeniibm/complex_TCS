package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CustomerPOM {
private WebDriver driver; 
	
	public CustomerPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="input-username")
	private WebElement username;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(css="button.btn.btn-primary")
	private WebElement login;
	
	@FindBy(id="menu-customer")
	private WebElement menu;
	
	@FindBy(linkText="Customers")
	private WebElement cust;
	
	@FindBy(id="input-name")
	private WebElement cust_name;	
	
	@FindBy(id="button-filter")
	private WebElement filter;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement name_check;
	
	@FindBy(id="input-email")
	private WebElement cust_email;	
	
	@FindBy(xpath="//tbody/tr/td[3]")
	private WebElement email_check;
	
	public void login(String username,String password)
	{
		this.username.clear();
		this.username.sendKeys(username);
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void buttonClick()
	{
		this.login.click();
	}
	
	public void CustomerMenu() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(this.menu).build().perform();
		act.sendKeys(this.cust, Keys.ENTER).build().perform();
		Thread.sleep(3000);
	}
	
	public void CustomerNameSearch(String cust_name) throws InterruptedException
	{
		String name_check;
		Thread.sleep(3000);
		this.cust_name.clear();
		this.cust_name.sendKeys(cust_name);
		this.filter.click();
		name_check = this.name_check.getText();
		Assert.assertEquals(cust_name, name_check);
		this.cust_name.clear();
	}
	
	public void CustomerEmailSearch(String cust_email) throws InterruptedException
	{
		String email_check;
		Thread.sleep(3000);
		this.cust_email.clear();
		this.cust_email.sendKeys(cust_email);
		this.filter.click();
		email_check = this.email_check.getText();
		Assert.assertEquals(cust_email, email_check);
	}


}
