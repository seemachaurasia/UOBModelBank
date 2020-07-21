package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtillFiles.ProcessTestRequest;

public class UOB_Login {

	WebElement ele;
	WebDriver driver;
	ProcessTestRequest ptr ;
	public UOB_Login(WebDriver driver) {
		this.driver = driver;
		ptr = new ProcessTestRequest(driver);
		PageFactory.initElements(driver, this);
		}
	
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='page-header-title']")
	public WebElement Login_Header;
	
	@CacheLookup
	@FindBy(xpath = "//input[@placeholder='Enter Company ID']")
	public WebElement CompanyId;
	
	@CacheLookup
	@FindBy(xpath = "//input[@placeholder='Enter User ID']")
	public WebElement UserId;
	
	@CacheLookup
	@FindBy(xpath = "//input[@placeholder='Enter Password']")
	public WebElement Password;
	
	@CacheLookup
	@FindBy(xpath = "//select[@placeholder='Select a Language']")
	public WebElement Language;
	
	@CacheLookup
	@FindBy(xpath = "//button[@id='btnLogin']")
	public WebElement AccessAccounts;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='page-header-title']")
	public WebElement DigitalBanker_Img;
	
	public void pagesync(int val) throws InterruptedException {

		Thread.sleep(1000 * val);
	}


}
