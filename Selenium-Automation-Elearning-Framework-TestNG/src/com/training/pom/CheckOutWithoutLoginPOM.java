package com.training.pom;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckOutWithoutLoginPOM {
	
private WebDriver driver; 
	
	public CheckOutWithoutLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
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
	
	@FindBy(id="input-email")
	private WebElement email;
	
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
		this.addCart.click();
		Thread.sleep(5000);	
	}
	
	public void shoppingCart() throws InterruptedException
	{
		String Actual,Expected,Act_email,Exp_email;
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		act.moveToElement(this.mouseCart).build().perform();
		Thread.sleep(3000);
		Actual = "Shopping Cart";
		Expected = this.shoppingMsg.getText();
		System.out.println("Expected " + Expected);
		Assert.assertEquals(Actual, Expected);
		this.viewCart.click();
		this.checkOut.click();
		Act_email = "jeniibm@gmail.com";
		this.email.sendKeys("jeniibm@gmail.com");
		Exp_email = this.email.getAttribute("value");
		System.out.println("Email value is "+Exp_email);
		Assert.assertEquals(Act_email, Exp_email);
	}

}
