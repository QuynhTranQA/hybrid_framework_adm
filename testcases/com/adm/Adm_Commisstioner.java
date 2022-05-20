package com.adm;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.CommissionerPO;
import pageObjects.DashBoardPO;
import pageObjects.LoginPO;

public class Adm_Commisstioner extends BaseTest{
	private WebDriver driver;
	
	LoginPO loginPage;
	DashBoardPO dashBoardPage;
	CommissionerPO commissionerPage;
 	String userName = "quynh.tran@thebank.com.vn";
	String passWord = "changeme";
	
	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
		loginPage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Pre-Condition - Step 01: Input to Username textbox");
		
		loginPage.enterToUsernameTextbox(driver, GlobalConstants.DB_TEST_USER);
	
		log.info("Pre-Condition - Step 02: Input to Password textbox");
		
		loginPage.enterToPasswordTextbox(driver, GlobalConstants.DB_TEST_PASS);
		
		log.info("Pre-Condition - Step 03: Click to Login button and Open Dashboard site");
		
		dashBoardPage = loginPage.clickToLoginButton(driver);
		
		log.info("Pre-Condition - Step 04: Verify displayed Dashboard screen");
		
		dashBoardPage.isSuccessMessageDisplayed ("Chào mừng đến với trang quản trị Momi");
	
	}

	@Test
	public void TC_01_Commissioner_Create_Success() {
		log.info("Commissioner_Create - Step 01: Click 'CV hệ thống' menu");
		
		dashBoardPage.clickToMenu(driver, "CV hệ thống");
		
		log.info("Commissioner_Create - Step 02: Click 'Danh sách CV' menu");
		
		dashBoardPage.clickToSubMenu(driver, "Danh sách CV");
		
		commissionerPage = PageGeneratorManager.getCommissionerPage(driver);
		
		log.info("Commissioner_Create - Step 03: Click 'Tạo mới' button");
		
		log.info("Commissioner_Create - Step 04: Input to 'Họ' textbox");
		
		log.info("Commissioner_Create - Step 05: Input to 'Tên' textbox");
		
		log.info("Commissioner_Create - Step 06: Input to 'Số điện thoại' textbox");
		
		log.info("Commissioner_Create - Step 07: Input to 'Email' textbox");
		
		log.info("Commissioner_Create - Step 08: Select to 'Giới tính' dropdowlist");
		
		log.info("Commissioner_Create - Step 09: Input to 'Ngày sinh' datepicker");
		
		log.info("Commissioner_Create - Step 10: Input to 'CMND' textbox");
		
		log.info("Commissioner_Create - Step 11: Input to 'Ngày cấp' datepicker");
		
		log.info("Commissioner_Create - Step 12: Select to 'Tỉnh/Thành phố' dropdowlist");
		
		log.info("Commissioner_Create - Step 13: Select to 'Quận/Huyện' dropdowlist");
		
		log.info("Commissioner_Create - Step 14: Click 'Lưu' button");
		
		log.info("Commissioner_Create - Step 15: Verify success message");
		//div[contains(@class,'MuiToolbar-root')]/parent::header/following-sibling::nav/following-sibling::main//div[contains(@class,'MuiSnackbar-root')]//div[text()='Cập nhật thành công']
		

	}

	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}

	
}
