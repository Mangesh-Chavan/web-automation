package selenium.scripts.sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_New_User_Landing_Page extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;

	String parentWindow = "";

	
	@Test
	public void test_01PlatformLandingPage() {
		extentTest = extentReports.createTest("Platform Landing Page");
		driver = getDriver();
		email = AggregatorToolAddandInviteSMEusers.email;
				//"SME_0011@yopmail.com";
		driver.get("https://yopmail.com/");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(email + Keys.ENTER);
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
			driver.findElement(By.id("rc-anchor-container")).click();
			sleep(2000L);
		} catch (Exception e) {
		}
		driver.switchTo().defaultContent();
		parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("refresh")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifinbox")));
		driver.findElement(By.xpath("//div[contains(text(),'Welcome')]/parent::button")).click();
		driver.switchTo().defaultContent();

		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Set')]/parent::a")).isDisplayed());
		driver.findElement(By.xpath("//span[contains(text(),'Set')]/parent::a")).click();
		for (String s : driver.getWindowHandles()) {
			if (!s.equals(parentWindow))
				driver.switchTo().window(s);
		}
		String generatedURL = driver.getCurrentUrl();
		String partialURL = (generatedURL.split(".org"))[1];
		String URL = "http://localhost:4200"+partialURL;
		System.out.println(URL);
		driver.get(URL);
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys("Testing@123"+Keys.TAB);
		sleep(1000L);
		driver.findElement(By.tagName("mat-checkbox")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Create my account']")));
		driver.findElement(By.xpath("//div[@class='company-location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter a location']")).sendKeys(new CharSequence[] {"ANTLLP"} );
		sleep(2000L);
		driver.findElement(By.xpath("//input[@placeholder='Enter a location']")).sendKeys(new CharSequence[] { Keys.ARROW_DOWN , Keys.ENTER} );
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		WebElement element = driver.findElement(By.xpath("//button[@id='lba-arrow-right']"));
		js.executeScript("window.scrollTo(0, 600);");
		js.executeScript("arguments[0].click();", element);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='sector']")).sendKeys("IT");
		sleep(2000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='subSector']")).sendKeys("Computer");
		sleep(2000L);
		element = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		js.executeScript("arguments[0].click();",element);
		String status =driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		sleep(1000);
		assertEquals(status, "Success!");
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Welcome')]")).isDisplayed(),"Welcome Message");
	}
	
	@Test
	public void test_02AssessmentInformationPage() {
		extentTest = extentReports.createTest("Assessment Information Page");
		assertTrue(driver.findElement(By.xpath("//b[contains(text(),'assessment')]")).isDisplayed(),"Enviroment Assessment");
		assertTrue(driver.findElement(By.xpath("//b[contains(text(),'information')]")).isDisplayed(),"Information on assesment");
		assertTrue(driver.findElement(By.className("welcome-ul")).isDisplayed(),"Assessment List");
		assertTrue(driver.findElement(By.xpath("//span[text()='Begin environmental assessment']")).isDisplayed(),"Begin assessment button");
	}
	
	@Test
	public void test_03StartofAssessmentPage2() {
		extentTest = extentReports.createTest("Start of Assessment Page 2");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Begin environmental assessment']")));
		assertEquals(driver.findElement(By.xpath("//h1[text()='Assessment Period']")).isDisplayed(), "Assessment Period");
	}
	
	@Test
	public void test_04SMEDadhboardPage() {
		extentTest = extentReports.createTest("SME Dadhboard Page");
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).isDisplayed(), "Coutinue assessment button");
	}
	
	@Test
	public void test_05StartofAssessmentPage2() {
		extentTest = extentReports.createTest("Start of Assessment Page 2");
		driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).click();
		assertEquals(driver.findElement(By.xpath("//h1[text()='Assessment Period']")).isDisplayed(), "Assessment Period");
		assertEquals(driver.findElement(By.xpath("//input[@placeholder='Select Assessment Period']")), "Date Filled");
		
	}
	
	
}
