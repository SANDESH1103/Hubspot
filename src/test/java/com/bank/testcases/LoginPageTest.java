package com.bank.testcases;





import org.testng.Assert;

import org.testng.annotations.Test;



import com.aventstack.extentreports.Status;
import com.bank.PageObjects.LoginPage;
import com.bank.Utils.Reporting;


public class LoginPageTest extends Base {
//	public ExtentTest logger;
//	public ITestResult testResult;
	
	@Test
	public void login()  {
		log.info("Excution starts");
		LoginPage page = new LoginPage(driver);
		log.info("Take username");
		page.setUserName(username);
		//Reporting.( "Enter  text"+password+"to text field" +new Exception().getStackTrace()[0].getMethodName());
//		logger.log(Status.PASS, "Enter  text"+username+"to text field"+testResult.getName());
//		logger.log(Status.FAIL, "Unable to enter text"+username+"Gives exception"+testResult.getThrowable());
		
		log.info("Take password");
		page.setPassword(password);
		
		//Reporting.logger.log(Status.PASS, "Enter  text"+password+"to text field" +new Exception().getStackTrace()[0].getMethodName());
//		logger.log(Status.FAIL, "Unable to enter text"+password+"Gives exception"+testResult.getThrowable());
		
		page.clickSubmit();
		
//		logger.log(Status.PASS, "click successfully "+testResult.getName());
//		logger.log(Status.FAIL, "Unable to click"+"Gives exception"+testResult.getThrowable());
		driver.switchTo().alert().accept();
		
		String title=driver.getTitle();
		//SoftAssert softAssert=new SoftAssert();
	    Assert.assertEquals(title, "HubSpot Home");
	    
	    
	   // captureScreen( driver,"login");
	// softAssert.assertAll(); //to accumulate all test result
	
	}
}
