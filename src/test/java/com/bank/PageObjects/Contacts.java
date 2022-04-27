package com.bank.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contacts {
	WebDriver driver;
	@FindBy(xpath = "(//a[@id='nav-primary-contacts-branch'])[1]")
	public WebElement contactElement;
	@FindBy(xpath = "(//div[@style='color:inherit'])[1]")
	WebElement clickContact;
	@FindBy(xpath = "(//span[contains(.,'Create contact')])[2]")
	WebElement clickCreatContactElement;
	@FindBy(id = "UIFormControl-1")
	WebElement emailElement;
	@FindBy(id = "UIFormControl-3")
	WebElement firstNamElement;
	@FindBy(id = "UIFormControl-7")
	WebElement lastNamElement;
	@FindBy(id = "UIFormControl-15")
	WebElement jobTitlElement;
	@FindBy(id = "UIFormControl-17")
	WebElement phoneNumberElement;
	@FindBy(id = "UIFormControl-19")
	WebElement lifeCycleDropdownElement;
	@FindBy(xpath = "//button[@title='Customer']")
	WebElement selectOneLifeCycle;
	@FindBy(xpath = "(//span[@class='private-typeahead-result private-typeahead-result--selectable'])[3]")
	WebElement leadStatusElement;
	@FindBy(xpath = "(//span[contains(.,'Connected')])[5]")
	WebElement selectOneLeadElement;
	@FindBy(xpath = "(//span[@class='private-loading-button__content private-button--internal-spacing'])[2]")
	WebElement createAndaddAnotherBtnElement;
	
	public Contacts(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    public void moveTOcontact() {
    	Actions actions=new Actions(driver);
    	actions.moveToElement(contactElement).build().perform();
	}
	public void clickContact() {
		clickContact.click();
	}
	public void clickCreateContact() {
		clickCreatContactElement.click();
	}
	public void email(String em) {
		emailElement.sendKeys(em);
	}
	public void firstName(String fn) {
		firstNamElement.sendKeys(fn);
	}
	public void lastName(String ln) {
		lastNamElement.sendKeys(ln);
	}
	public void jobTitle(String jt) {
		jobTitlElement.sendKeys(jt);
	}
	public void phoneNumber(String pn) {
		phoneNumberElement.sendKeys(pn);
	}
	public void lifeCycleDropdown() {
		lifeCycleDropdownElement.click();
	}
	public void selectOneLife() {
		selectOneLifeCycle.click();;
	}
	public void leadStatus() {
		leadStatusElement.click();;
	}
	public void selectOneLead() {
		selectOneLeadElement.click();;
	}
	public void createAndAddBtn() {
		createAndaddAnotherBtnElement.click();
	}
}
