package com.bank.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bank.PageObjects.LoginPage;
import com.bank.Utils.Xlutils;

public class TC_001_DDTLogin extends Base {
    
	
	@Test (dataProvider = "login")
	public void loginData(String user,String pass) throws InterruptedException {
		LoginPage lg=new LoginPage(driver);
		log.info("username provided");
		lg.getuser(user);
		log.info("password provided");
		lg.getpass(pass);
		log.info("click on signin button");
		lg.clickSignIn();
		Thread.sleep(5000);
		String title=driver.getTitle();
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(title, "HubSpot Home");
	 captureScreen( driver,"loginData");
	 softAssert.assertAll(); //to accumulate all test result
		
	
}
    @DataProvider(name = "login")
    public String[][] getData() throws IOException {
		String pathString=System.getProperty("user.dir")+"/src/test/java/com/bank/Testdata/fb.xlsx";
	    int row=Xlutils.getRowCount(pathString, "Sheet1");
	    int cell=Xlutils.getCellCount(pathString, "Sheet1",1 );
        String [] [] loginData=new String [row][cell];
        for (int i = 1; i <=row; i++) {
        	for (int j = 0; j < cell; j++) {
				loginData[i-1][j]= Xlutils.getData(pathString, "Sheet1", i, j);
			}
			
		}
        return loginData;
    }
}
;