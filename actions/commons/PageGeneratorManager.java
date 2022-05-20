package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.AgencyPO;

import pageObjects.CommissionerCreatePO;
import pageObjects.CommissionerPO;
import pageObjects.DashBoardPO;
import pageObjects.LoginPO;


public class PageGeneratorManager {
	
	public static LoginPO getUserHomePage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static DashBoardPO getDashBoardPage(WebDriver driver) {
		return new DashBoardPO(driver);
	}
	
	public static AgencyPO getAgencyPage(WebDriver driver) {
		return new AgencyPO(driver);
	}

	public static CommissionerPO getCommissionerPage(WebDriver driver) {
		return new CommissionerPO(driver);
	}

	public static CommissionerCreatePO getCommissionerCreatePage(WebDriver driver) {
		return new CommissionerCreatePO(driver);
	}
	
}