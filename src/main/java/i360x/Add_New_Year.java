package i360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Add_New_Year extends TestConfig{
	
	public static WebDriver driver;
	
	@Test
	public void test_01AddYearPage() {
		extentTest = extentReports.createTest("Add Year Page");
		driver = getDriver();
		//driver.findElement(By.xpath("//a[@aria-label='Close']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' ADD YEAR ']")));
		driver.findElement(By.xpath("//button[text()=' ADD YEAR ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")));
		assertTrue(driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).isDisplayed(),"Accounting period Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).isDisplayed(),"Number of Employees Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).isDisplayed(),"Revenue Feild");
		
	}
	
	//@Test
	public void test_02ValidateAddYearPage() {
		extentTest = extentReports.createTest("Validate Add Year Page");
		assertEquals(driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).getAttribute("aria-required"), "true", "Validation Faild");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).getAttribute("aria-required"), "true", "Validation Faild");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).getAttribute("aria-required"), "true", "Validation Faild");
	}
	
	
	@Test
	public void test_03AddYearSuccessfullPage() {
		extentTest = extentReports.createTest("Add Year Successfull Page");
		driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).sendKeys("April, 2023");
		driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).sendKeys("200");
		driver.findElement(By.xpath("//button[text()=' Add ']")).click();
		assertFalse(driver.findElement(By.xpath("//button[text()=' Add ']")).isDisplayed(),"Data Not Saved");
	}

}
