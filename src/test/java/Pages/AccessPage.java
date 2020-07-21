package Pages;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import UtillFiles.ProcessTestRequest;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class AccessPage  {
	WebElement ele;
	WebDriver driver;
	ProcessTestRequest ptr ;
	public AccessPage(WebDriver driver) {
		this.driver = driver;
		ptr = new ProcessTestRequest(driver);
		PageFactory.initElements(driver, this);
		}

	@CacheLookup
	@FindBy(xpath = "//li[contains(text(),'Disclaimer Content')]")
	public WebElement Disclaimer;
	
	
	public By AcceptButton() {

		return By.xpath("//button[@id='submitDisclaimerAccept']");

		}

	@CacheLookup
	@FindBy(xpath = "//*[starts-with(@id,'SelfService')]/a")
	public WebElement OfficialRecords;
	
	@CacheLookup
	@FindBy(xpath = "//a[@class='ss-action ss-action-form ss-utility-box ss-action-page-search ui-link']//h1[contains(text(),'Document Type Web Search')]")
	public WebElement DocTypeSearch;
	
	@CacheLookup
	@FindBy(name="field_RecDateID_DOT_StartDate")
	public WebElement startDate;
	
	@CacheLookup
	@FindBy(name="field_RecDateID_DOT_EndDate")
	public WebElement endDate;
	
	@CacheLookup
	@FindBy(xpath="//input[@id='field_selfservice_documentTypes']")
	public WebElement DocType_TextField;
	
	@CacheLookup
	@FindBy(xpath="//ul[@class='cblist-input-list']")
	public WebElement DocTypeList;
	
	
	@CacheLookup
	@FindBy(xpath="//span[text()='View']")
	public WebElement ViewLink;
	
	
	
	public By SearchButton() {

		return By.xpath("//a[@id='searchButton']");
		

		}
	
	
	

	public void pagesync(int val) throws InterruptedException {

		Thread.sleep(1000 * val);
	}

}
