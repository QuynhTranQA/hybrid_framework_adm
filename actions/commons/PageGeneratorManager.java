package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPO;
import pageObjects.LoginPO;


public class PageGeneratorManager {
	
	public static LoginPO getUserHomePage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static DashBoardPO getDashBoardPage(WebDriver driver) {
		return new DashBoardPO(driver);
	}
	
}