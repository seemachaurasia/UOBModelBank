package BaseFile;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.cucumber.listener.Reporter;

import UtillFiles.Buffer;
import UtillFiles.Constants;
import UtillFiles.ProcessTestRequest;

public class UIBase {
	public static JavascriptExecutor JSExec;
	//public static ProcessTestRequest ptr;
	public static WebDriver driver;
	
	public static String getRandom() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);

		return String.valueOf(minutes) + String.valueOf(seconds);
	}
     
	@BeforeMethod
	public void initialization() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_PATH);
		// String downloadFilepath = "/Testing/src/test/resources/DownloadedFiles";

		// options.addArguments("--headless");

		//String downloadpath = System.getProperty("user.dir") + "/" + "DownloadedFiles";
		//File downloadPathFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\DownloadedFiles");
		String downloadFilepath = System.getProperty("user.dir") + "\\src\\test\\resources\\DownloadedFiles";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.directory_upgrade", true);
		chromePrefs.put("browser.set_download_behavior", "allow");
		chromePrefs.put("download.prompt_for_download", false);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(options);

//    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//    chromePrefs.put("profile.default_content_settings.popups", 0);
//    chromePrefs.put("download.default_directory", "D:\\");
//    ChromeOptions options = new ChromeOptions();
//    options.setExperimentalOption("prefs", chromePrefs);
//	  
//	  
//    driver = new ChromeDriver(options);

//    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//    ChromeOptions options = new ChromeOptions();
//   
//    capabilities.setCapability("chrome.binary", downloadFilepath);
//    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//    //options.merge(capabilities);
//    driver = new ChromeDriver(capabilities);

//  DesiredCapabilities caps =DesiredCapabilities.chrome();
//  caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//  caps.setCapability(ChromeOptions.CAPABILITY, options);

		/*
		 * Map<String, Object> prefs = new HashMap<String, Object>();
		 * prefs.put("profile.default_content_settings.popups", 0);
		 * prefs.put("download.default_directory",
		 * "C:\\Users\\seema.chaurasia\\Desktop\\Workplace\\Testing\\src\\test\\resources\\DownloadedFiles"
		 * ); ChromeOptions options = new ChromeOptions();
		 * options.setExperimentalOption("prefs", prefs);
		 */

		// driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		//driver.navigate().to("https://clerk.clevelandcountyok.com/web/user/disclaimer");
	}

	@AfterMethod
	public String getMethodName(ITestResult result) {
		// driver.quit();
		return result.getMethod().getMethodName();
	}

	@AfterSuite
	public void closeBrowser() throws IOException {
		Reporter.loadXMLConfig(Constants.EXTENT_CONFIG_PATH);
		Reporter.setSystemInfo("Author", " QA AUTOMATION TEAM");
		Reporter.setSystemInfo("Project", " Analytics");
//	File htmlFile = new
//			 File("\\target\\cucumber-reports\\Automation_Report.html");
//			 Desktop.getDesktop().browse(htmlFile.toURI());

	}

}
