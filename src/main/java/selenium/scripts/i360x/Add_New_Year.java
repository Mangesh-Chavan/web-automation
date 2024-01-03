package selenium.scripts.i360x;

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
	
	public String revenueAmount="";
	
	@Test	
	public void test_001LoginPage() {
		driver = getDriver();
		driver.get("https://i360x.projectnimbus.co.in/");
		extentTest = extentReports.createTest("Login Page");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='email']")).isDisplayed(),"Email input feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='password']")).isDisplayed(),"Password input feild");
		assertTrue(driver.findElement(By.xpath("//button[text()=' LOG IN ']")).isDisplayed(),"Login button");
	}
	
	
	@Test
	public void test_01AllUnitPage() {
		extentTest = extentReports.createTest("All Unit Page");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("admin@gmail.com");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[text()=' LOG IN ']")).click();
		driver.findElement(By.xpath("//a[contains(@href,'units')]")).click();
		assertTrue(driver.findElement(By.xpath("//h4[text()='ALL Units']")).isDisplayed(),"All Units Label On top Left");
		assertFalse(driver.findElement(By.xpath("//button[text()=' ADD YEAR ']")).isDisplayed(),"Add new Year, Button Is Not Visible");
		
	}
	
	
	@Test
	public void test_02AddYearPage() {
		extentTest = extentReports.createTest("Add Year Page");
		driver = getDriver();
		driver.findElement(By.xpath("//a[@aria-label='Close']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' ADD YEAR ']")));
		driver.findElement(By.xpath("//button[text()=' ADD YEAR ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")));
		assertTrue(driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).isDisplayed(),"Accounting period Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).isDisplayed(),"Number of Employees Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).isDisplayed(),"Revenue Feild");
		
	}
	
	//@Test
	public void test_03ValidateAddYearPage() {
		extentTest = extentReports.createTest("Validate Add Year Page");
		assertEquals(driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).getAttribute("aria-required"), "true", "Validation Faild");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).getAttribute("aria-required"), "true", "Validation Faild");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).getAttribute("aria-required"), "true", "Validation Faild");
	}
	
	
	@Test
	public void test_04AddYearSuccessfullPage() {
		extentTest = extentReports.createTest("Add Year Successfull Page");
		driver.findElement(By.xpath("//button[text()=' ADD YEAR ']/following::input[@formcontrolname='accountingPeriod']")).sendKeys("01-04-2022");
		driver.findElement(By.xpath("//input[@formcontrolname='empcount']")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@formcontrolname='totalrevenue']")).sendKeys("200");
		driver.findElement(By.xpath("//button[text()=' Add ']")).click();
		String actualText = driver.findElement(By.xpath("//h5[contains(@class,'oper-year')][1]")).getText();
		assertEquals(actualText, "Year 2022","New Added Year Should Be Visible");
		actualText = driver.findElement(By.xpath("//a[contains(text(),'$')]")).getText();
		assertEquals(actualText, "$200 million","Revenue Should be visible with $ symbol and monetization");
		assertFalse(driver.findElement(By.xpath("//button[text()=' Add ']")).isDisplayed(),"Data Not Saved");
	}
	
	@Test
	public void test_111LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		driver.findElement(By.xpath("//img[contains(@src,'logo')]")).click();
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='email']")).isDisplayed(),"Email input feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='password']")).isDisplayed(),"Password input feild");
		assertTrue(driver.findElement(By.xpath("//button[text()=' LOG IN ']")).isDisplayed(),"Login button");
	}

}
