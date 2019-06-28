package com.training.pom;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckOutWithLoginPOM {
	
   private WebDriver driver; 
	
	public CheckOutWithLoginPOM(WebDriver driver) {
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
	
	@FindBy(xpath="//a[@href='javascript:;']")
	private WebElement home;
	
	@FindBy(xpath="//a[@href='http://retail.upskills.in/earrings']")
	private WebElement earring;
	
	@FindBy(xpath="//a[@href='http://retail.upskills.in/earrings/integer-vitae-iaculis-massaa']")
	private WebElement product;
	
	@FindBy(id="button-cart")
	private WebElement addCart;
	
	@FindBy(xpath="//*[@id='cart']")
	private WebElement mouseCart;
	
	@FindBy(xpath="//*[@class='content']/h3")
	private WebElement shoppingMsg;
	
	@FindBy(xpath="//*[@class='checkout buttons']/a[1]")
	private WebElement viewCart;
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	private WebElement checkOut;
	
	@FindBy(id="button-payment-address")
	private WebElement continueAddress;
	
	@FindBy(id="button-shipping-address")
	private WebElement continueShip;
	
	@FindBy(xpath="//*[@type='radio' and @name='shipping_method']")
	private WebElement radioCheck;
	
	@FindBy(name="comment")
	private WebElement comment;
	
	@FindBy(id="button-shipping-method")
	private WebElement continueShiping;
	
	@FindBy(id="button-payment-method")
	private WebElement conitinuePayment;
	
	@FindBy(xpath="//*[@type='checkbox' and @name='agree']")
	private WebElement checkBox;
	
	@FindBy(id="button-confirm")
	private WebElement confirmOrder;
	
	@FindBy(xpath="//*[contains(text(),'Your order has been successfully processed!')]")
	private WebElement confirmMsg;
	
	public void LoginPage(String userName,String password) throws InterruptedException
	{
		this.account.click();
		Thread.sleep(3000);
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear(); 
		this.password.sendKeys(password);
		this.loginBtn.click(); 
	}
	
	public void EarringMenu() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(this.home).build().perform();
		Thread.sleep(3000);
		this.earring.click();
		this.product.sendKeys(Keys.chord(Keys.CONTROL,Keys.RETURN));
		ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
		Thread.sleep(2000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(3000);
		this.addCart.click();
		Thread.sleep(5000);	
	}
	
	public void shoppingCart() throws InterruptedException
	{
		//String Actual,Expected,Act_str,Exp_str;
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		act.moveToElement(this.mouseCart).build().perform();
		Thread.sleep(3000);
		/*Actual = "Shopping Cart";
		Expected = this.shoppingMsg.getText();
		Assert.assertEquals(Actual, Expected);*/
		this.viewCart.click();
		this.checkOut.click();
		Thread.sleep(3000);
		this.continueAddress.click();
		Thread.sleep(5000);
		this.continueShip.click();
		Thread.sleep(3000);
		if(this.radioCheck.isSelected())
		{
			this.comment.sendKeys("Product is nice");
			this.continueShiping.click();
			Thread.sleep(3000);
			this.checkBox.click();
			this.conitinuePayment.click();
		}
		Thread.sleep(3000);
		this.confirmOrder.click();
		/*Act_str = this.confirmMsg.getText();
		Exp_str="Your order has been successfully processed!";
		Assert.assertEquals(Act_str, Exp_str);*/
	}
	
	

}
