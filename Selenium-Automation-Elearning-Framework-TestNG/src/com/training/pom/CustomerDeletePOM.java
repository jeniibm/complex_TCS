package com.training.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CustomerDeletePOM {
private WebDriver driver; 
	
	public CustomerDeletePOM(WebDriver driver) {
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
	
	@FindBy(xpath="//tbody/tr[3]/td[1]")
	private WebElement name_checkbox;
	
	@FindBy(xpath="//tbody/tr[3]/td[2]")
	private WebElement cust_name;
	
	@FindBy(css="button.btn.btn-danger")
	private WebElement delete;	
	
	@FindBy(xpath="//*[contains(text(),' Success: You have modified customers!      ')]")
	private WebElement success;
	
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
	
	public void CustomerDelete() throws AWTException, InterruptedException
	{
		String Actual_str,Actual;
		this.name_checkbox.click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_UP);
		this.delete.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		Actual_str = this.success.getText();
		Actual = Actual_str.replace("\n", "");
		Actual = Actual.replace("×","");
		System.out.println(Actual);
		String Expected = "Success: You have modified customers!";
		System.out.println("Both are same");
		Assert.assertEquals(Actual,Expected);
	}

}
