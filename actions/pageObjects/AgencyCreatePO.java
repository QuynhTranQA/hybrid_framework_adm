package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AgencyCreatePageUI;

public class AgencyCreatePO extends BasePage {
	
	WebDriver driver;
	
	public AgencyCreatePO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void nameAgencyTextbox(WebDriver driver, String dbNameAgency) {
		waitForElementVisible(driver, AgencyCreatePageUI.NAMEAGENCY_TEXTBOX);
		sendkeyToElement(driver, AgencyCreatePageUI.NAMEAGENCY_TEXTBOX, dbNameAgency);
		
	}
}
