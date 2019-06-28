package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyLoginParallelPOM {
private WebDriver driver; 
	
	public MyLoginParallelPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="input-username")
	private WebElement username;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(css="button.btn.btn-primary")
	private WebElement login;
	
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

}
