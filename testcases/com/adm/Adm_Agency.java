package com.adm;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.DashBoardPO;
import pageObjects.LoginPO;

public class Adm_Agency extends BaseTest{
		private WebDriver driver;
		
		LoginPO loginPage;
		DashBoardPO dashBoardPage;
		String userName = "nhi.hoang@thebank.com.vn";
		String passWord = "000000";
		
		@Parameters({"browser", "urlAdmin"})
		@BeforeClass
		public void beforeClass(String browserName, String url) {
			driver = getBrowserDriver(browserName, url);
			
			loginPage = PageGeneratorManager.getUserHomePage(driver);
			
			log.info("Pre-Condition - Step 01: Input to Username textbox");
			
			loginPage.enterToUsernameTextbox(driver, GlobalConstants.DB_DEV_USER);
		
			log.info("Pre-Condition - Step 02: Input to Password textbox");
			
			loginPage.enterToPasswordTextbox(driver, GlobalConstants.DB_DEV_PASS);
			
			log.info("Pre-Condition - Step 03: Click to Login button and Open Dashboard site");
			
			dashBoardPage = loginPage.clickToLoginButton(driver);
			
			log.info("Pre-Condition - Step 04: Verify displayed Dashboard screen");
			
			dashBoardPage.isSuccessMessageDisplayed ("Chào mừng đến với trang quản trị Momi");
		
		}
		@Test
		public void TC_01_Agency_Create_Success() {
			
		}
	

	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}

	
}
