package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DashBoardPageObject extends BasePage {
	
	WebDriver driver;
	
	public DashBoardPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
