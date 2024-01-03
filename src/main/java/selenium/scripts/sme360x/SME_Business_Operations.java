package selenium.scripts.sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_Business_Operations extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;
	
	@Test
	public void test_01BusinessOperationsPage() {
		extentTest = extentReports.createTest("Business Operations Page");
		driver = getDriver();
		email =  AggregatorToolAddandInviteSMEusers.email;
				//"SME_0007@yopmail.com";
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).isDisplayed(), "Resume my assessment button");
		driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).click();
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
			driver.findElement(By.xpath("//div[@aria-label='Dismiss']")).click();
		}catch(Exception e) {}
		driver.findElement(By.xpath("//h3[text()='1']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']")).isDisplayed(),"Input Feild Is Visible");
	}
	
	
	@Test
	public void test_02ValidateBusinessOperationsPage() {
		extentTest = extentReports.createTest("Validate Business Operations Page");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']")).getAttribute("aria-required"), "true","Eployee count Feild is Mandatory");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']")).getAttribute("type"), "number","Employee count Feild Should be Number");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).getAttribute("aria-required"), "true","Revenue Feild is Mandatory");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).getAttribute("type"), "number","Employee count Feild is Mandatory");
		assertEquals(driver.findElement(By.xpath("//mat-select[@placeholder='USD']")).getAttribute("placeholder"), "USD","Currency Feild is Mandatory and Dropdown");
	}
	
	
	@Test
	public void test_03BusinessOperationsPreviousPage() {
		extentTest = extentReports.createTest("Business Operations Previous Page");
		driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']/following::span[text()=' Back ']")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Assessment Period']")).isDisplayed(), "Assessment Period");
		assertTrue(driver.findElement(By.xpath("//input[@placeholder='Select Assessment Period']")).isDisplayed(), "Date Filled");
		
	}
	
	@Test
	public void test_04ValidateQuestionCounter() {
		extentTest = extentReports.createTest("Validate Question Counter");
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
			driver.findElement(By.xpath("//div[@aria-label='Dismiss']")).click();
		}catch(Exception e) {}
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Next question']")));
		assertTrue(driver.findElement(By.xpath("//h1[text()='Business Operations']")).isDisplayed(),"Business Operations Headline");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='1']")).getText(), "1","Number Count 1");
		driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']")).sendKeys("1000");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='employeeCount']/following::span[text()='Next question']")));
		sleep(2000L);
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='2']")).getText(), "2","Number Count 2");
		sleep(1000L);
		driver.findElement(By.xpath("//mat-select[@placeholder='USD']")).sendKeys("INR");
		sleep(1000);
		driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).sendKeys("150000000");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']/following::span[text()='Next question']")));
		sleep(1000L);
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		
	}
	
	@Test
	public void test_05ValidateProgressBar() {
		extentTest = extentReports.createTest("Validate Progress Bar");
		sleep(2000L);
		assertTrue(driver.findElement(By.xpath("//li[contains(@class,'completed')]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_06DashboardPage() {
		extentTest = extentReports.createTest("Dashboard Page");
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).isDisplayed(), "Resume my assessment button");
	}
	
	@Test
	public void test_07GHGAssesmentPage() {
		extentTest = extentReports.createTest("GHG Assesment Page");
		driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Greenhouse Gas Emissions']")).isDisplayed(),"Greenhouse Gas Emissions Headline");
	}

}
