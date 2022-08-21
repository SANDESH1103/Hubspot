package com.bank.testcases;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bank.PageObjects.LoginPage;
import com.bank.Utils.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	ReadConfig read = new ReadConfig();
	 // Method method=new Method();
	public static WebDriver driver;
    public  String url= read.getApplicationUrl();
	public  String username= read.getUsername();
	public  String password= read.getPassword();
    public Logger log;
	
	
	
	@Parameters ("browser")
	@BeforeMethod
	public void initilization(String browser) {
		
	
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",read.getChromePath());
			driver = new ChromeDriver();
			System.out.println("Browser name :"+ browser);
			System.out.println(Thread.currentThread().getId());
		}
		else if (browser.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver",read.getFirefoxPath());
			driver = new FirefoxDriver();
			System.out.println("Browser name :"+ browser);
			System.out.println(Thread.currentThread().getId());
		}
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Browser name :"+ browser);
			System.out.println(Thread.currentThread().getId());
		}
	
	
		driver.manage().window().maximize();
		//driver.manage().timeouts().impli
		driver.manage().deleteAllCookies();
		driver.get(url);
		log=Logger.getLogger(Base.class);
		PropertyConfigurator.configure("log4j.properties");
	  
	
	}
	
	
	
	public  static  String captureScreen ( WebDriver driver,String tname)   {
		TakesScreenshot shoot=(TakesScreenshot)driver;
		File src = shoot.getScreenshotAs(OutputType.FILE);
		File dec =new File(System.getProperty("user.dir")+"/Screenshoot/"+ tname +".png");
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshoot taken");
		
		return dec.getAbsolutePath();
	}
	public static String captureScreen(WebDriver driver)   {
		TakesScreenshot shoot=(TakesScreenshot)driver;
		String base64Code = shoot.getScreenshotAs(OutputType.BASE64);
		
		System.out.println("Screenshoot taken");
		
		return base64Code;
	}
	public static String  randomString () {
	 String random=RandomStringUtils.randomAlphabetic(6);
	 return random;
	}
	public static String randomNumber() {
		 String random=RandomStringUtils.randomNumeric(10);
		 return random;
		}
	
//	public static void sendKeys_Custom(WebElement element,String methodName,String valueTobeSend ) {
//		element.sendKeys(valueTobeSend);
//		
//		
//	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
