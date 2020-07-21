package StepDefinitionFiles;



import com.cucumber.listener.Reporter;

import BaseFile.UIBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest extends UIBase{
	@Given("^login to the google app$")
	public void login_to_the_google_app() throws Throwable {
		Reporter.addStepLog("Login to application");
		System.out.println("hello");

	}

	@When("^Google home page open$")
	public void google_home_page_open() throws Throwable {
	    
	}

	@Then("^verify google icon on the home page$")
	public void verify_google_icon_on_the_home_page() throws Throwable {
	    
	}

}
