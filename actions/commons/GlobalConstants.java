package commons;

import java.io.File;

public class GlobalConstants {
	public static final String USER_URL = "https://adm.xoay.io/#/login";
	public static final String ADMIN_URL = "";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	
	public static final String DRAP_DROP_HTML5 = PROJECT_PATH + File.separator + "drapAndDrop";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	
	public static final String DB_DEV_URL = "192.168.1.15:9860";
	public static final String DB_DEV_USER = "nhi.hoang@thebank.com.vn";
	public static final String DB_DEV_PASS = "000000";
	
	public static final String DB_TEST_URL = "192.168.1.23:9860";
	public static final String DB_TEST_USER = "quynh.tran@thebank.com.vn";
	public static final String DB_TEST_PASS = "changeme";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;
}
