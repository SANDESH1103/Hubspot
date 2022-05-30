package com.bank.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.PageObjects.AddCustomerPage;

import com.bank.PageObjects.LoginPage;
import com.bank.Utils.Xlutils;

public class TC_AddCustomerTest_003 extends Base {

	@Test (dataProvider = "customer data")
	public void addNewCustomer(String cuname,String dob,String month,String year,String add,String city,String state,String Pin,String cphone,String pass) throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		log.info("User name is provided");
		lp.setPassword(password);
		log.info("Passsword is provided");
		lp.clickSubmit();

		Thread.sleep(3000);

		AddCustomerPage addcust = new AddCustomerPage(driver);

		addcust.clickAddNewCustomer();

		log.info("providing customer details....");

		addcust.custName(cuname);
		addcust.custgender("male");
		addcust.custdob(dob, month, year);
		Thread.sleep(5000);
		addcust.custaddress(add);
		addcust.custcity(city);
		addcust.custstate(state);
		addcust.custpinno(Pin);
		addcust.custtelephoneno(cphone);

		String email = randomString() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword(pass);
		addcust.custsubmit();
		captureScreen(driver, "CustomerD");

		Thread.sleep(3000);

		log.info("validation started....");

		boolean res = driver.getPageSource()
				.contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
			log.info("test case passed....");

		} else {
			log.info("test case failed....");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}

	}
	
	
	@DataProvider(name="customer data")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/bank/Testdata/fb.xlsx";
		
		int rownum=Xlutils.getRowCount(path, "Sheet2");
		int colcount=Xlutils.getCellCount(path,"Sheet2",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=Xlutils.getData(path,"Sheet2", i,j);//1 0
			}
				
		}
	return logindata;
	}

}