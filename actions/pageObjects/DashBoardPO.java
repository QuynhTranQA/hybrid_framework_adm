package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.DashBoardPageUI;

public class DashBoardPO extends BasePage {
	
	WebDriver driver;
	
	public DashBoardPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessMessageDisplayed(String successMessage) {
		waitForElementVisible(driver,DashBoardPageUI.SUCCESS_MESSAGE_TEXT , successMessage);
		return isElementDisplayed(driver,DashBoardPageUI.SUCCESS_MESSAGE_TEXT , successMessage);
	}

}
