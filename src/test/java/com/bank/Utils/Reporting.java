package com.bank.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bank.testcases.Base;


public class Reporting extends TestListenerAdapter{

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@Override	
	public void onStart(ITestContext testContext)
	{
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		//String repName="Test-Report-"+timeStamp+".html";

		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Test-output/Extent Reports/report.html");//specify location of the report
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/Extent-config.xml");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		extent=new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","sandesh");

		htmlReporter.config().setDocumentTitle("Hubspot Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	@Override
	public void onTestStart(ITestResult tr) {
		extent.createTest(tr.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,"Test Case Pass"+tr.getName());
		logger.log(Status.PASS,"Test Case Pass "+tr.getThrowable());
		
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	@Override
	public void onTestFailure(ITestResult tr) 
	{
		
			String pathString=Base.captureScreen(Base.driver,tr.getName());
			String base64String=Base.captureScreen(Base.driver);
		
		extent.createTest(tr.getName()).info("Test report at test level").fail(tr.getName()).addScreenCaptureFromPath("."+pathString);
		extent.createTest(tr.getName()).fail(tr.getName()).addScreenCaptureFromBase64String(base64String);
		extent.createTest(tr.getName()).fail(tr.getName()).addScreenCaptureFromBase64String(base64String, tr.getName());
		
		extent.createTest(tr.getName()).fail(tr.getName(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
		
		extent.createTest(tr.getName()).fail(tr.getName(), MediaEntityBuilder.createScreenCaptureFromPath("."+pathString).build());
		
		extent.createTest(tr.getName()).fail(tr.getName(), MediaEntityBuilder.createScreenCaptureFromPath("."+pathString,tr.getName()).build());
		//Throwable t=new Throwable("this is custom exception");
		//extent.createTest(tr.getName()).fail(t);
		if(tr.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL,"Test case failed=> "+tr.getName());
			logger.log(Status.FAIL,"Test case failed=> "+tr.getThrowable());
			logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		}
		
			
			
		
		
		 // send the fail information to the report with RED color highlighted

//		String screenshotPath=System.getProperty("user.dir")+"\\Screenshot\\"+tr.getName()+".png";
//
//		File f = new File(screenshotPath); 
//
//		if(f.exists())
//		{
//			try {
//				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
//			} 
//			catch (IOException e) 
//			{
//				e.printStackTrace();
//			}
//		}

	}
	@Override
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,"Test case Skipped=> "+tr.getName());
		logger.log(Status.SKIP,"Test case Skipped=> "+tr.getThrowable());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	@Override
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}




