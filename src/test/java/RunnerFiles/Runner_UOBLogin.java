package RunnerFiles;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.ExtentProperties;

import BaseFile.UIBase;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = ".\\src\\test\\java\\Feature_Files\\UOB_Login.feature", glue = { "StepDefinitionFiles" }, tags = { "@test" },plugin = {
		"pretty",
		"com.cucumber.listener.ExtentCucumberFormatter:" },
monochrome = true
)
public class Runner_UOBLogin extends UIBase {
	private TestNGCucumberRunner testNGCucumberRunner;
	UIBase testbase = new UIBase();
	

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
	testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	ExtentProperties extentProperties = ExtentProperties.INSTANCE;
    extentProperties.setReportPath("target/cucumber-reports/UOBreport.html");
	// testbase.initialization();
	}
	@Test(groups = "Cucumber_Reports", description = "Login to UOB", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
	testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
	return testNGCucumberRunner.provideFeatures();
	}

	

	

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
	testbase.closeBrowser();
	testNGCucumberRunner.finish();
	}
}
