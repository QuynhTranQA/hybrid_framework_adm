package pageUIs;

public class DashBoardPageUI {
	
	public static final String SUCCESS_MESSAGE_TEXT ="xpath=//span[text()='%s']";  
	
	public static final String MENU_BUTTON	="xpath=//div[contains(@class,'MuiDrawer-docked')]//span[text()='%s']";
	
	public static final String SUB_MENU_BUTTON	="xpath=//div[contains(@class,'MuiDrawer-docked')]//parent::div/parent::li/following-sibling::div//a[contains(text(),'%s')]";

}
