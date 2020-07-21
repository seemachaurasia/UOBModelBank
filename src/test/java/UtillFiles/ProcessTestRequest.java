package UtillFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import UtillFiles.Status.LogStatus;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class ProcessTestRequest {

	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 15;
	public static Properties prop;
	public static Actions action;
	WebDriver driver;
	WebDriverWait wait;

	public ProcessTestRequest(WebDriver driver) {
	this.driver = driver;
	}

	public ProcessTestRequest() {

	}
	// public void switchToFrame() {
	// driver.switchTo().frame("mainpanel");
	// }

	public String getLocator(WebElement element) {
	try {
	Object proxyOrigin = FieldUtils.readField(element, "h", true);
	Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
	Object findBy = FieldUtils.readField(locator, "by", true);
	if (findBy != null) {
	return findBy.toString();
	}
	} catch (IllegalAccessException ignored) {
	}
	return "[unknown]";
	}

	public void openTab(String url) {
	((JavascriptExecutor) driver).executeScript("window.open()");
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	driver.get(url);
	}

	public void javaScriptClick(WebElement ele) throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", ele);
	// highlightElement(driver, ele);
	}

	public void pageDown() {
	action = new Actions(driver);
	action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	// act.sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
	action.keyUp(Keys.CONTROL).pause(1000).build().perform();

	}

	public void pageUp() {
	action = new Actions(driver);
	action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
	// action.sendKeys(Keys.ESCAPE).pause(1000).build().perform();
	action.keyUp(Keys.CONTROL).pause(1000).build().perform();
	}

	public void switchToParentTab() {
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(0));
	}

	public void switchToTab(int num) {
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(num));
	}

	public void waitForElement(WebElement ele) {
	wait = new WebDriverWait(driver, 60, 5);
	String val = null;
	try {
	if (getLocator(ele).contains("xpath")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(val)));
	} else if (getLocator(ele).contains("css")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(val)));
	} else if (getLocator(ele).contains("id")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(val)));
	} else if (getLocator(ele).contains("name")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(val)));

	}
	} catch (Exception e) {
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	}

	public void waitUntilElementInvisible(WebElement ele) {
	wait = new WebDriverWait(driver, 60, 5);
	String val = null;
	if (getLocator(ele).contains("xpath")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(val)));
	} else if (getLocator(ele).contains("css")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(val)));
	} else if (getLocator(ele).contains("id")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(val)));
	} else if (getLocator(ele).contains("name")) {
	val = getLocator(ele).substring(getLocator(ele).indexOf(" ") + 1, getLocator(ele).length());
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(val)));
	}
	}

	public void waitUntilDisappearByLocator(By locator, int time) {
	wait = new WebDriverWait(driver, time, 5);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static String getCurrentTime() {
	String CTime = java.time.LocalTime.now().toString();
	return CTime.replace(":", "").replace(".", "").substring(4, 8);
	}

	public static String takeScreenshotAtEndOfTest(WebDriver driver, String screenshotName) throws IOException {
	// String dateName = new SimpleDateFormat("MMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	// after execution, you could see a folder "FailedTestsScreenshots"
	// under src folder
	String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
	}

	public void propMathod() {
	try {
	prop = new Properties();
	FileInputStream config = new FileInputStream(
	System.getProperty("D:\\WorkSpace\\CucumberBDD" + "\\Configuration\\Config.properties"));
	prop.load(config);
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	// Set<String> windows = driver.getWindowHandles();
	// Iterator<String> itr = windows.iterator();
	// {
	// while (itr.hasNext()) {
	// System.out.println(itr.next());
	// }
	// }

	// select the dropdown using "select by VisibleText", so pass VisibleText as
	// String
	public void clickOnCanvas(WebElement element) throws InterruptedException {
	int width = element.getSize().getWidth();
	int xOffset = (int) (width - (width / 3));
	System.out.println(xOffset);
	System.out.println(element.getSize().getWidth());
	System.out.println(element.getLocation().getX());
	System.out.println(element.getLocation().getY());
	// highlightElement(driver, element);
	action = new Actions(driver);
	// action.moveToElement(element,xOffset,0).click().build().perform();
	// action.click(element).moveByOffset(1120, 310).click().build().perform();
	// action.moveToElement(element).moveByOffset(xOffset,
	// 0).click().build().perform();
	action.moveByOffset(0, 0).build().perform();
	Thread.sleep(2000);
	action.moveByOffset(1200, 310).pause(Duration.ofSeconds(2)).click().build().perform();
	}

	// public void dragAnddropSikuli(String srcpath, String destpath) throws InterruptedException, FindFailed {
	// Screen screen = new Screen();
	// Pattern pat = null;
	// screen.dragDrop(srcpath, destpath);
	// Thread.sleep(2000);
	// }
	//
	// public void dragAnddropSikuliUp(String srcpath, String destpath) throws InterruptedException, FindFailed {
	// Screen scr = new Screen();
	// Region lRegion = scr.find(destpath);
	// Location lLocation = lRegion.getCenter().setLocation(150, 350);
	// scr.drag(srcpath);
	// Thread.sleep(2000);
	// scr.dropAt(lLocation);
	// }

	// public void clickUp(String imgPath) throws FindFailed, InterruptedException {
	// Screen scr = new Screen();
	// Region lRegion = scr.find(imgPath);
	// Location lLocation = lRegion.getCenter().setLocation(200, 350);
	// // scr.highlight();
	// scr.doubleClick(lLocation);
	// // scr.resetScreens();
	// }
	//
	// public void clickDown(String imgPath) throws FindFailed, InterruptedException {
	// Screen scr = new Screen();
	// Region lRegion = scr.find(imgPath);
	// Location lLocation = lRegion.getCenter().setLocation(200, 600);
	// // scr.highlight();
	// scr.doubleClick(lLocation);
	// // scr.resetScreens();
	// }

	// public void dragAnddropSikuliRight(String srcpath, String destpath) throws InterruptedException, FindFailed {
	// Screen scr = new Screen();
	// Region lRegion = scr.find(destpath);
	// // good for center
	// // Location lLocation = lRegion.getCenter().setLocation(500,420);
	// Location lLocation = lRegion.getCenter().setLocation(800, 420);
	// scr.drag(srcpath);
	// Thread.sleep(2000);
	// scr.dropAt(lLocation);
	// }
	//
	// public void dragAnddropSikuliDown(String srcpath, String destpath) throws InterruptedException, FindFailed {
	// Screen scr = new Screen();
	// Region lRegion = scr.find(destpath);
	// Location lLocation = lRegion.getCenter().setLocation(150, 600);
	// scr.drag(srcpath);
	// Thread.sleep(2000);
	// scr.dropAt(lLocation);
	// }

	// public void doubleClickSikuli(String srcpath) throws InterruptedException, FindFailed {
	// Screen screen = new Screen();
	// Pattern pat = new Pattern(srcpath);
	// Thread.sleep(2000);
	// screen.doubleClick(pat);
	// Thread.sleep(2000);
	// }
	//
	// public void clickSikuli(String srcpath) throws InterruptedException, FindFailed {
	// Screen screen = new Screen();
	// Pattern pat = new Pattern(srcpath);
	// Thread.sleep(2000);
	// screen.click(pat);
	// Thread.sleep(2000);
	// }

	public void dragAnddrop(WebElement source, WebElement target) {
	action = new Actions(driver);
	action.dragAndDrop(source, target);
	}

	public void Select_VisibleText(WebElement element, String VisibleText, String stepDesc) {
	Select selObj = new Select(element);
	highlightElement(driver, element);
	selObj.selectByVisibleText(VisibleText);
	System.out.println("..." + stepDesc);
	}

	public void Select_FirstOption(WebElement element) {
	Select selObj = new Select(element);
	highlightElement(driver, element);
	selObj.selectByIndex(0);
	}

	// select the dropdown using "select by index", so pass IndexValue as '2'
	public void Select_IndexValue(WebElement element, int IndexValue) {
	Select selObj = new Select(element);
	highlightElement(driver, element);
	selObj.selectByIndex(IndexValue);
	}

	// select the dropdown using "select by value"
	public void Select_Value(WebElement element, String Value) {
	Select selObj = new Select(element);
	highlightElement(driver, element);
	selObj.selectByValue(Value);
	}

	public static void scriptSync() throws InterruptedException {
	Thread.sleep(2000);
	}

	public void sendEnter() {
	action = new Actions(this.driver);
	action.sendKeys(Keys.ENTER).build().perform();
	}

	public void clickAt(WebElement ele, String stepDesc) {
	action = new Actions(driver);
	action.moveToElement(ele).click().build().perform();
	System.out.println("..." + stepDesc);
	}

	static String fileName = "BufferStorage.properties";

	@SuppressWarnings("unused")
	public static void checkFilePresent(String fileName) throws IOException {
	try {
	@SuppressWarnings("resource")
	FileReader file = new FileReader(fileName);
	} catch (FileNotFoundException e) {
	FileOutputStream fos;
	fos = new FileOutputStream(fileName);
	fos.close();
	}
	}

	public static void setBuffer(String key, String value) throws IOException {
	checkFilePresent(fileName);
	Properties prop = new Properties();
	prop.load(new FileInputStream(fileName));
	prop.put(key, value);
	FileOutputStream fileOut = new FileOutputStream(fileName);
	prop.store(fileOut, "");
	fileOut.close();
	}

	public static String getBuffer(String key) throws IOException {
	checkFilePresent(fileName);
	FileReader file = new FileReader(fileName);
	Properties prop = new Properties();
	prop.load(file);
	if (prop.getProperty(key) == null) {
	System.out.println("Key is not Present");
	return null;
	}
	return prop.getProperty(key);
	}

	public void click(WebElement ele, String stepDesc) {
	try {
	highlightElement(driver, ele);

	ele.click();
	System.out.println("..." + stepDesc);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	// e.printStackTrace();
	}
	}
	public void jClick(WebElement ele, String stepDesc) {
	try {
	highlightElement(driver, ele);
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	executor.executeScript("arguments[0].click();", ele);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}

	public void type(WebElement ele, String val, String stepDesc) {
	try {
	highlightElement(driver, ele);
	ele.clear();
	ele.sendKeys(val);
	System.out.println("..." + stepDesc);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}

	public void typeByAction(WebElement ele, String val, String stepDesc) {
	try {
	highlightElement(driver, ele);
	action = new Actions(driver);
	ele.click();
	Thread.sleep(1000);
	ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	Thread.sleep(1000);
	action.sendKeys(Keys.BACK_SPACE).pause(1000).sendKeys(Keys.BACK_SPACE).build().perform();
	// action.sendKeys(Keys.chord(Keys.CONTROL,
	// "a")).pause(1000).sendKeys(Keys.BACK_SPACE).pause(1000).build().perform();
	action.sendKeys(val).pause(1000).sendKeys(Keys.TAB).build().perform();
	// action.keyUp(Keys.CONTROL).pause(1000).build().perform();
	System.out.println("..." + stepDesc);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}

	public void highlightElement(WebDriver driver, WebElement element) {
	// Creating JavaScriptExecuter Interface
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].style.border='6px groove yellow'", element);
	try {
	Thread.sleep(100);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	js.executeScript("arguments[0].style.border=''", element);
	}

	public static String getRandom() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	// int hours = calendar.get(Calendar.HOUR_OF_DAY);
	int minutes = calendar.get(Calendar.MINUTE);
	int seconds = calendar.get(Calendar.SECOND);
	// System.out.println(String.valueOf(minutes) + String.valueOf(seconds));
	return String.valueOf(minutes) + String.valueOf(seconds);
	}

	
	@SuppressWarnings("deprecation")
	public static String readTextFile(String filename) throws IOException {
	String everything;
	FileInputStream inputStream = new FileInputStream(".\\BODY\\" + filename + ".txt");
	try {
	everything = IOUtils.toString(inputStream);
	} finally {
	inputStream.close();
	}

	return everything;

	}
	// public static void csvRead() throws IOException, InterruptedException {
	// {
	// // Build reader instance
	// UtilityClass.scriptSync();
	// CSVReader reader = new CSVReader(new
	// FileReader(PipeLineValidate_Backend.finalfileName), ',', '"', 1);
	// // Read all rows at once
	// List<String[]> allRows = reader.readAll();
	// // Read CSV line by line and use the string array as you want
	// for (String[] row : allRows) {
	// System.out.println(Arrays.toString(row));
	// }
	// }
	// }

	public String getTextStaleElement(By locator) throws InterruptedException, IOException, ClassNotFoundException {

	@SuppressWarnings("unused")
	boolean result = false;
	int attempts = 0;
	String val = null;
	while (attempts < 2) {
	try {

	val = driver.findElement(locator).getText();

	result = true;
	break;
	} catch (StaleElementReferenceException e) {
	}
	;
	attempts++;
	}
	return val;
	}

	public void clickForStaleElement(By locator) throws Throwable {

	@SuppressWarnings("unused")
	boolean result = false;
	int attempts = 0;
	// String val = null;
	while (attempts < 2) {
	try {
	//highlightElement(driver);
	highlightElement(driver, driver.findElement(locator));
	driver.findElement(locator).click();

	result = true;
	break;
	} catch (StaleElementReferenceException e) {
	}
	;
	attempts++;
	}
	}

	public void clickForStaleElements(WebElement webElement) throws Throwable {

	@SuppressWarnings("unused")
	boolean result = false;
	int attempts = 0;
	// String val = null;
	while (attempts < 2) {
	try {
	//highlightElement(driver);
	highlightElement(driver, driver.findElement((By) webElement));
	driver.findElement((By) webElement).click();

	result = true;
	break;
	} catch (StaleElementReferenceException e) {
	}
	;
	attempts++;
	}

	}
	public void typeForStaleElement(By locator,String value) throws Throwable {

	@SuppressWarnings("unused")
	boolean result = false;
	int attempts = 0;
	// String val = null;
	while (attempts < 2) {
	try {
	//highlightElement(driver);
	highlightElement(driver, driver.findElement(locator));
	driver.findElement(locator).clear();
	driver.findElement(locator).sendKeys(value);

	result = true;
	break;
	} catch (StaleElementReferenceException e) {
	}
	;
	attempts++;
	}

	}
	public void typeMultiple(WebElement[] ele, String[] values, int startingWebElement, String stepDesc) {

	int i = startingWebElement;
	for (String m : values) {
	try {
	highlightElement(driver, ele[i]);
	action = new Actions(driver);
	ele[i].click();
	Thread.sleep(1000);
	action.sendKeys(Keys.chord(Keys.CONTROL, "a")).pause(1000).sendKeys(Keys.BACK_SPACE).pause(1000)
	.sendKeys(Keys.BACK_SPACE).build().perform();
	action.sendKeys(m).pause(1000).sendKeys(Keys.TAB).build().perform();
	System.out.println("..." + stepDesc);
	} catch (Exception e) {
	e.printStackTrace();
	}

	i++;

	}

	}

	public boolean isDisplayedForStaleElement(By locator)
	throws InterruptedException, IOException, ClassNotFoundException {

	boolean result = false;
	int attempts = 0;
	// String val = null;
	while (attempts < 2) {
	try {
	highlightElement(driver, driver.findElement(locator));
	driver.findElement(locator).isDisplayed();

	result = true;
	break;
	} catch (StaleElementReferenceException e) {
	}
	;
	attempts++;
	}
	return result;

	}
	public List<String> getTextStaleElements(By locator)
	            throws InterruptedException, IOException, ClassNotFoundException {

	     @SuppressWarnings("unused")
	boolean result = false;
	     int attempts = 0;
	     List<WebElement> ele = null;
	     List<String> data = new ArrayList<String>();
	     while (attempts < 2) {
	            try {
	                  // highlightElement(driver, driver.findElements(locator));
	                  ele = driver.findElements(locator);
	                  for (int i = 1; i < ele.size()-1; i++) {
	                         ele.get(i).getText();
	                         //System.out.println(ele.get(i).getText());
	                         data.add(ele.get(i).getText());
	                  }

	                  result = true;
	                  break;
	            } catch (StaleElementReferenceException e) {
	            }
	            ;
	            attempts++;
	     }
	     return data;

	}

	public void log(LogStatus value, String stepName)
	throws ClassNotFoundException, IOException {

	if (value.equals(LogStatus.valueOf("START"))) {
	Log.startTestCase(stepName);
	} else if (value.equals(LogStatus.valueOf("END"))) {
	Log.endTestCase(stepName);
	}
	else if (value.equals(LogStatus.valueOf("FAIL"))) {
	Log.FailedTestCase(stepName);
	}else if (value.equals(LogStatus.valueOf("INFO"))) {
	Log.info(stepName);
	}

	}
	//public static String getRandom() {
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(new Date());
	// int hours = calendar.get(Calendar.HOUR_OF_DAY);
	// int minutes = calendar.get(Calendar.MINUTE);
	// int seconds = calendar.get(Calendar.SECOND);
	// System.out.println(String.valueOf(minutes) + String.valueOf(seconds));
	// return String.valueOf(minutes) + String.valueOf(seconds);
	// }


	public void readTextImage() throws IOException, TesseractException, ParseException {
        //File srcImage = imageVerify.getScreenshotAs(OutputType.FILE);
        //String ToPath = System.getProperty("user.dir") +"/ImageVerify/image.png";
        //FileHandler.copy(srcImage, new File(ToPath));
		System.out.println("file reader");
        String ToPath = "C:\\Users\\seema.chaurasia\\Downloads\\T2020-187-1.pdf";
        ITesseract image = new Tesseract();
        image.setDatapath(".\\tessdata\\");
        image.setLanguage("eng");
                     
        try {
               String imageText = image.doOCR(new File(ToPath));
            System.out.println(imageText);
                         
         } catch (TesseractException e) {
             System.err.println(e.getMessage());
         }
 }

}
