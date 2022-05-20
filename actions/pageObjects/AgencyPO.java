package pageObjects;

	import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AgencyPageUI;

	public class AgencyPO extends BasePage {
		
		WebDriver driver;
		
		public AgencyPO(WebDriver driver) {
			this.driver = driver;
		}
		
		public void enterNameagencyTextbox(WebDriver driver, String dbNameAgency) {
			waitForElementVisible(driver, AgencyPageUI.AGENCY_MENU);
			sendkeyToElement(driver, AgencyPageUI.AGENCY_MENU, dbNameAgency);
		}	
}