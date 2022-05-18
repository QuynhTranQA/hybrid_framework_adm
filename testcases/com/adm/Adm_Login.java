package com.adm;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.DashBoardPageObject;
import pageObjects.LoginPageObject;

public class Adm_Login extends BaseTest{
	private WebDriver driver;
	
	LoginPageObject loginPage;
	DashBoardPageObject dashBoardPage;
	
	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
		loginPage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Pre-Condition - Step 01: Input to Username textbox");
		
		log.info("Pre-Condition - Step 02: Input to Password textbox");
		
		log.info("Pre-Condition - Step 03: Click to Login button");
		
		log.info("Pre-Condition - Step 04: Open Dashboard site");
		
	}

	@Test
	public void TC_Login() {
		
		


	}

	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	
}
