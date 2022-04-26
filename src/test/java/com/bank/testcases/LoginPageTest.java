package com.bank.testcases;




import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bank.PageObjects.LoginPage;


public class LoginPageTest extends Base {
	@Test
	public void login() throws InterruptedException {
		log.info("Excution starts");
		LoginPage page = new LoginPage(driver);
		log.info("Take username");
		page.getuser(username);
		log.info("Take password");
		page.getpass(password);
		page.clickSignIn();
		Thread.sleep(5000);
		String title=driver.getTitle();
		SoftAssert softAssert=new SoftAssert();
	    softAssert.assertEquals(title, "HubSpot Home");
	    captureScreen( driver,"login");
	 softAssert.assertAll(); //to accumulate all test result
	
	}
}
