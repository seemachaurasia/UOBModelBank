package StepDefinitionFiles;

import org.openqa.selenium.JavascriptExecutor;

import com.cucumber.listener.Reporter;

import BaseFile.UIBase;
import Pages.UOB_Login;
import UtillFiles.Log;
import UtillFiles.ProcessTestRequest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition_UOB_Login extends UIBase {
	UOB_Login ub = new UOB_Login(driver);
	ProcessTestRequest  ptr =new ProcessTestRequest(driver);
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	@Given("^Navigate to UOB application$")
	public void navigate_to_UOB_application() throws Throwable {
	    try {
	    	driver.get("https://modelbank.bankonline.com/rwd-web/logon");
	    	ub.pagesync(5);
	    	if(ub.Login_Header.isDisplayed())
	    	{
	    	Reporter.addStepLog("User able to login to UOB application");
	    	
	    	}
	    }
	    catch(Exception e)
	    {
	    	
	    	
	    }
	}

	@When("^User enter company id \"([^\"]*)\"$")
	public void user_enter_company_id(String companyId) throws Throwable {
		try{
			if(ub.CompanyId.isEnabled())
	    	{  ub.pagesync(2);
				Reporter.addStepLog("User able to see company id");
				ptr.type(ub.CompanyId, companyId, "user enter company id");
//				ptr.highlightElement(driver, ub.CompanyId);
//	    		ub.CompanyId.sendKeys(companyId);
	    	}
			else
			{
				Reporter.addStepLog("User not able to see company id");
			}
		}
	    catch(Exception e)
		{
	    	
		}
	}

	@When("^User enter user id \"([^\"]*)\"$")
	public void user_enter_user_id(String userId) throws Throwable {
		try{
			if(ub.UserId.isEnabled())
	    	{  ub.pagesync(2);
				Reporter.addStepLog("User able to see user id");
				//ptr.highlightElement(driver, ub.UserId);
				ptr.type(ub.UserId, userId, "user enter user id");
	    		//ub.UserId.sendKeys(userId);
	    	}
			else
			{
				Reporter.addStepLog("User not able to see user id");
			}
		}
	    catch(Exception e)
		{
	    	
		}
	}

	@When("^User enter password \"([^\"]*)\"$")
	public void user_enter_password(String password) throws Throwable {
		try{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    	Thread.sleep(2000);
			if(ub.Password.isEnabled())
	    	{  ub.pagesync(2);
				Reporter.addStepLog("User able to see password");
				//ptr.highlightElement(driver, ub.Password);
				ptr.type(ub.Password, password, "user enter Password");
	    		//ub.Password.sendKeys(password);
	    	}
			else
			{
				Reporter.addStepLog("User not able to see password");
			}
		}
	    catch(Exception e)
		{
	    	
		}
	}

	@When("^User select language \"([^\"]*)\"$")
	public void user_select_language(String language) throws Throwable {
		try{
			if(ub.Language.isEnabled())
	    	{  ub.pagesync(2);
				Reporter.addStepLog("User able to see language");
				//ptr.highlightElement(driver, ub.Language);
				ptr.Select_VisibleText(ub.Language, language, "user select value");
	    		//ptr.Select_Value(ub.Language, language);
	    	}
			else
			{
				Reporter.addStepLog("User not able to see language");
			}
		}
	    catch(Exception e)
		{
	    	
		}
	}

	@When("^User click on Access account button$")
	public void user_click_on_Access_account_button() throws Throwable {
		try{
			if(ub.AccessAccounts.isEnabled())
	    	{  ub.pagesync(2);
				Reporter.addStepLog("User able to see Access Accounts button");
				//ptr.click(ub.AccessAccounts, "User click on Access Accounts button");
	    		ub.AccessAccounts.click();
	    	}
			else
			{
				Reporter.addStepLog("User not able to see Access Accounts button");
			}
		}
	    catch(Exception e)
		{
	    	
		}
	}

	@Then("^Verify that user is able to see UOB home page$")
	public void verify_that_user_is_able_to_see_UOB_home_page() throws Throwable {
		try{
			ub.pagesync(4);
			if(ub.DigitalBanker_Img.isDisplayed())
			{
				Reporter.addStepLog("user able to see UOB home page");
			}
			
		}
	    catch(Exception e)
		{
	    	
		}
	}



}
