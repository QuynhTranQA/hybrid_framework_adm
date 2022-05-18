package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPageObject;
import pageObjects.LoginPageObject;


public class PageGeneratorManager {
	
	public static LoginPageObject getUserHomePage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DashBoardPageObject getDashBoardPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	
}