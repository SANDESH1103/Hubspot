package com.bank.testcases;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.bank.PageObjects.LoginPage;
import com.bank.Utils.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	ReadConfig read = new ReadConfig();
	
	public static WebDriver driver;
    public  String url= read.getApplicationUrl();
	public  String username= read.getUsername();
	public  String password= read.getPassword();
	public static Logger log;
	
	
	@Parameters ("browser")
	@BeforeMethod
	public void initilization(String browser) {
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty(("user.dir")+ ""))

		//String browser = "chrome";
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().deleteAllCookies();
		driver.get(url);
		log=Logger.getLogger(Base.class);
		PropertyConfigurator.configure("Log4j.properties");
	  
	
	}
	public void captureScreen(WebDriver driver,String tname)   {
		TakesScreenshot shoot=(TakesScreenshot)driver;
		File src = shoot.getScreenshotAs(OutputType.FILE);
		File dec =new File(System.getProperty("user.dir")+"/Screenshoot/"+ tname +".png");
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshoot taken");
		
	}
	public static String  randomString () {
	 String random=RandomStringUtils.randomAlphabetic(6);
	 return random;
	}
	public static String randomNumber() {
		 String random=RandomStringUtils.randomNumeric(10);
		 return random;
		}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
