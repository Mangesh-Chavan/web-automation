package sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_Tool_Assessment_Period extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;
	
	@Test
	public void test_01DashboardPage() {
		extentTest = extentReports.createTest("Dashboard Page");
		driver = getDriver();
		driver.get("http://localhost:4200/login");
		email =  AggregatorToolAddandInviteSMEusers.email;
				//"SME_0007@yopmail.com";
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).isDisplayed(), " Complete your assessment ");
		
	}
	
	
	@Test
	public void test_02AssessmentPeriodPage() {
		extentTest = extentReports.createTest("Assessment Period Page");
		driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Assessment Period']")).isDisplayed(), "Assessment Period");
		assertTrue(driver.findElement(By.xpath("//input[@placeholder='Select Assessment Period']")).isDisplayed(), "Date Filled");
		assertTrue(driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).isDisplayed(),"Calender Icon");
	}
	
	
	@Test
	public void test_03ValidationsChecks() {
		extentTest = extentReports.createTest("Validations Checks");
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
		while(driver.findElement(By.xpath("//button[@aria-label='Previous month']")).isEnabled())
		{
			driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();
			sleep(100L);
		}
		String actualString = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']/span/span")).getText();
		assertEquals(actualString,"FEBRUARY 2021");
		while(driver.findElement(By.xpath("//button[@aria-label='Next month']")).isEnabled())
		{
			driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
			sleep(100L);
		}
		actualString = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']/span/span")).getText();
		assertEquals(actualString,"JANUARY 2022");
		driver.findElement(By.xpath("//span[text()=' 1 ']")).click();
//		if(driver.findElement(By.xpath("//span[text()='JANUARY 2022']")).isDisplayed())
//		{
//			assertFalse(driver.findElement(By.xpath("//button[@aria-label='Next month']")).isEnabled(),"Validation for calender Feb 2022");
//			for(int i=1;i<12;i++);
//			{
//				driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();
//				sleep(500L);
//			}
//			assertFalse(driver.findElement(By.xpath("//button[@aria-label='Previous month']")).isEnabled(),"Validation for calender Jan 2021");
//		}
		sleep(2000L);
		String valueString = driver.findElement(By.xpath("//input[@formcontrolname='date']")).getAttribute("value");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//		TemporalAccessor temporalAccessor = formatter.parse(valueString);
//		LocalDateTime date = LocalDateTime.parse(valueString,formatter);
//		String expectedResult = date.plusMonths(12).toString();
//		valueString = driver.findElement(By.xpath("//input[@formcontrolname='date']")).getAttribute("value");
//		System.out.println(expectedResult);
//		System.out.println(valueString);
//		assertEquals(valueString, expectedResult);
		valueString = driver.findElement(By.xpath("//input[@formcontrolname='date']")).getAttribute("readonly");
		assertEquals(valueString, "true");
	}
	
	
	@Test
	public void test_04NextButtonClickable() {
		extentTest = extentReports.createTest("Next Button Clickable");
		assertTrue(driver.findElement(By.xpath("//span[text()='Next question']")).isEnabled(),"Next Button");
	}
	
	@Test
	public void test_05BusinessOperationsPage() {
		extentTest = extentReports.createTest("Business Operations Page");
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Next question']")));
		assertTrue(driver.findElement(By.xpath("//h1[text()='Business Operations']")).isDisplayed(),"Business Operations Headline");
	}
	
	@Test
	public void test_06DashboardPage2() {
		extentTest = extentReports.createTest("Dashboard Page 2");
		driver.get("http:localhost:4200/login");
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
	public void test_07BusinessAssesmentPage() {
		extentTest = extentReports.createTest("Business Assesment Page");
		driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Business Operations']")).isDisplayed(),"Business Operations Headline");
	}

	
	//@Test
	public void test_08LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		driver.findElement(By.xpath("//span[contains(text(),'Testing')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
	}
}
