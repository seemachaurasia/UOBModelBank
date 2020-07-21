package StepDefinitionFiles;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;

import BaseFile.UIBase;
import Pages.AccessPage;
import UtillFiles.ProcessTestRequest;
import UtillFiles.WebElementExtender;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sourceforge.tess4j.Tesseract;

public class Access_Steps extends UIBase {
	
	AccessPage ap=new AccessPage(driver);
	ProcessTestRequest ptr = new ProcessTestRequest(driver);
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	@Given("^Initialize the browser with chrome$")
	public void initialize_the_browser_with_chrome() throws Throwable {
	    Reporter.addStepLog("browser launched successfully");
	}

	@Given("^Navigate to \"([^\"]*)\" Site$")
	public void navigate_to_Site(String arg1) throws Throwable {
		Reporter.addStepLog("site launched successfully");
		System.out.println("*************************************!!!!!!!!!!!!!!!!!!!!");
	}

	@Given("^Click on ACCEPT link$")
	public void click_on_ACCEPT_link() throws Throwable {
	    try
	    {  
	    	
	    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    	Thread.sleep(5000);
	    	if(ap.Disclaimer.isDisplayed())
	    	{   System.out.println("system is displaying disclamer page succeessfully");
	    		Reporter.addStepLog("system is displaying disclamer page succeessfully");
	    	}
	    	
	    	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@id='submitDisclaimerAccept']")));
//	    	ptr.clickForStaleElement(ap.AcceptButton());
//	    	driver.findElement(By.xpath("//button[@id='submitDisclaimerAccept']")).click();
	    	
	    }
	    catch(Exception e)
	    {
	    	
	    }
	}

	@Given("^click on official Records for web search$")
	public void click_on_official_Records_for_web_search() throws Throwable 
	{
		try {
			ap.pagesync(3);
			ap.OfficialRecords.click();
			
		}
		catch(Exception e)
	    {
			System.out.println("Exception ");
	    }
	    
	}

	@Given("^click on Docuemnt Type Web Search$")
	public void click_on_Docuemnt_Type_Web_Search() throws Throwable
	{  
		ap.pagesync(2);
		try {
			if(ap.DocTypeSearch.isDisplayed())
			{
				System.out.println("system is displaying Doc Type Serach page succeessfully");
				System.out.println("path of directly");
				System.out.println(System.getProperty("user.dir"));
	    		Reporter.addStepLog("system is displaying Doc Type Serach page succeessfully");
			}
			ap.DocTypeSearch.click();
			
		}
		catch(Exception e)
	    {
	    	
	    }
	    
	}
	@Given("^enter start date \"([^\"]*)\" and end date \"([^\"]*)\"$")
	public void enter_start_date_and_end_date(String sdate, String edate) throws Throwable {
		ap.pagesync(2);
		
		try
		{
		
		Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println(dateOnly.format(cal.getTime()));
		   
			if(ap.startDate.isDisplayed())
			{ System.out.println("system is displaying start date");
				ap.startDate.sendKeys(sdate);
			}
			if(ap.endDate.isDisplayed())
			{
				System.out.println("system is displaying end date");
				System.out.println("edate"+edate);
				if(edate.equals("current date"))
				{
				ap.endDate.sendKeys(dateOnly.format(cal.getTime()));
			}
				else
				{
					ap.endDate.sendKeys(edate);
				}
			}
			
		}
		catch(Exception e)
	    {
	    	
	    }
	    
	}

	@When("^User enters search \"([^\"]*)\" and click on searh button$")
	public void user_enters_search_and_click_on_searh_button( List<String> value) throws Throwable 
	{   
		
		//driver.findElement(By.xpath("//ul[@class='cblist-input-list']")).click(); 
		ap.DocTypeList.click();
		ap.pagesync(3);
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow']/li"));
		//System.out.println("List of  values"+options.size());
		 for(int i=0;i<value.size();i++)
		 {
			 
		  
			 System.out.println("List of given values"+value.get(i).toString().toUpperCase());
			 System.out.println("click on element");
				//driver.findElement(By.xpath("//input[@id='field_selfservice_documentTypes']")).sendKeys(value.get(i).toString().toUpperCase());
			    ap.DocType_TextField.sendKeys(value.get(i).toString().toUpperCase());
				ap.pagesync(2);
				 driver.findElement(By.xpath("//li[contains(text(),'"+value.get(i).toString().toUpperCase()+"')]")).click();
				 //driver.findElement(By.xpath("//input[@id='field_selfservice_documentTypes']")).clear();
				 ap.DocType_TextField.clear();
				
		
		
		 }

	    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// ptr.clickForStaleElement(ap.SearchButton());
		 js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@id='searchButton']")));
	    
		
	}

	@When("^view the docuemnts and download file in local$")
	public void view_the_docuemnts_and_download_file_in_local() throws Throwable {
		ap.pagesync(3);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("(//ul[@class='selfServiceSearchResultList ui-listview ui-listview-inset ui-corner-all ui-shadow']//li)[1]")).click();
		ap.pagesync(3);
		//driver.findElement(By.xpath("//span[text()='View']")).click();
		ap.ViewLink.click();
	   ap.pagesync(3);
	  // int size = driver.findElements(By.tagName("iframe")).size();
	   
	   driver.switchTo().frame(0);
	   ap.pagesync(6);
	   
	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/div[1]/div[2]/button[4]")).click();
	}

	
	@Then("^Verify that user is able to see all the fields$")
	public void verify_that_user_is_able_to_see_all_the_fields() throws Throwable {
	    ap.pagesync(6);
	    ptr.readTextImage();
		
		System.out.println("testing for git");
		System.out.println("testing for git by kapil");
		System.out.println("testing for git by kapil");
	}

	@Then("^close browsers$")
	public void close_browsers() throws Throwable {
	    
	}

}
