package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ReportAggregatesListener;
import com.cucumber.listener.Reporter;

import UtillFiles.ProcessTestRequest;

public class Commonfunctions {
	WebElement ele;
	WebDriver driver;
	ProcessTestRequest ptr ;
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	public Commonfunctions(WebDriver driver) {
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
	@FindBy(xpath = "//div[@id='banking-logo']//img")
	public WebElement DigitalBanker_Img;
	
	public void pagesync(int val) throws InterruptedException {

		Thread.sleep(1000 * val);
	}
	
	public void Login(String companyId ,String userid , String password ,String language )
	{
		try {
			if(CompanyId.isEnabled())
	    	{  pagesync(2);
				Reporter.addStepLog("User able to see company id");
				ptr.type(CompanyId, companyId, "user enter company id");

	    	}
			if(UserId.isEnabled())
			{
				Reporter.addStepLog("User able to see User id");
				ptr.type(UserId, userid, "user enter user id");
			}
			
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    	Thread.sleep(2000);
			if(Password.isEnabled())
	    	{  
				Reporter.addStepLog("User able to see password");
				
				ptr.type(Password, password, "user enter Password");
	    	
	    	}
			if(Language.isEnabled())
	    	{  
				Reporter.addStepLog("User able to see language");
			
				ptr.Select_VisibleText(Language, language, "user select value");
	    		
	    	}
			if(AccessAccounts.isEnabled())
	    	{  
				Reporter.addStepLog("User able to see Access Accounts button");
			
	    		AccessAccounts.click();
	    	}
			pagesync(4);
			if(DigitalBanker_Img.isDisplayed())
			{
				Reporter.addStepLog("user able to see UOB home page");
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

}
