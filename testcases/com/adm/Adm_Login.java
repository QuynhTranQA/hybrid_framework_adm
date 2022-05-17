package com.adm;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Adm_Login extends BaseTest{
	private WebDriver driver;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
		
	}

	@Test
	public void TC_Login() {
		
		


	}

	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	
}
