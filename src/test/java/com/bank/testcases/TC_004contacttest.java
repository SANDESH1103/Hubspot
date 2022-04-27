package com.bank.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.PageObjects.Contacts;
import com.bank.Utils.Xlutils;

public class TC_004contacttest {
	public class TC_004_contactPage extends Base {
		Contacts contacts = new Contacts(driver);
		@Test(dataProvider = "contact")
		public void getData(String fn, String lnString,String jb) {
			contacts.moveTOcontact();
			contacts.clickContact();
			contacts.clickCreateContact();
			String mail = randonString()+ "gmail.com";
			contacts.email(mail);
			contacts.firstName(fn);
			contacts.lastName(lnString);
			contacts.jobTitle(jb);
		  	String number = randomNumber();
			contacts.phoneNumber(number);
			contacts.lifeCycleDropdown();
			contacts.selectOneLife();
			contacts.leadStatus();
			contacts.selectOneLead();
			contacts.createAndAddBtn();

		}
		@DataProvider(name = "contact")
		public String[][] contactData() throws IOException {
			String pathString = "./src/test/java/com/bank/Testdata/fb.xlsx";

			int rowCount = Xlutils.getRowCount(pathString, "Sheet2");
			int cellCount = Xlutils.getCellCount(pathString, "Sheet2", 1);
			String getData[][] = new String[rowCount][cellCount];
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < cellCount; j++) {
					getData[i - 1][j] = Xlutils.getData(pathString, "Sheet2", i, j);
				}
			}
			return getData;

		}
	}
}
