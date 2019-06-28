package com.training.pom;

	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyLoginPOM {
	
		private WebDriver driver; 
		
		public MyLoginPOM(WebDriver driver) {
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
		
		@FindBy(xpath="//i[@class='fa fa-user-o']")
		private WebElement act;
		
		@FindBy(xpath="//*[@class='fa fa-sign-out']")
		private WebElement logoutBtn;
		
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
		
		public void clickLogOut() throws InterruptedException
		{
			Thread.sleep(3000);
			Actions act = new Actions(driver);
			act.moveToElement(this.act).build().perform();
			Thread.sleep(3000);
			this.logoutBtn.click();
		}
	}



