package com.bank.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	 
	@FindBy(xpath = "//input[@id='username']")
	 WebElement loginElement;
	@FindBy(xpath = "//input[@id='password']")
	 WebElement passElement;
	@FindBy(xpath = "//button[@id='loginBtn']")
    WebElement signinElement;

	
	
	 

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void getuser(String user) {
		while (true) {

			try {
				loginElement.sendKeys(user);
				break;
			} catch (Exception e) {
				System.out.println("some problem");
				break;
			}
		}

	}

	public  void  getpass(String pass) {
		passElement.sendKeys(pass);
	}

	public  void clickSignIn() {
		signinElement.click();
	}

}