package i360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Create_A_New_Unit extends TestConfig{
	
	public static WebDriver driver;
	
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
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='CREATE NEW UNIT']")));
		assertFalse(driver.findElement(By.xpath("//button[text()='CREATE NEW UNIT']")).isDisplayed(),"Create New Unit Is Not Visible");
		
	}
	
	@Test
	public void test_02AddUnitDetailsPage() {
		extentTest = extentReports.createTest("Add Unit Details Page");
//		driver.findElement(By.xpath("//h4[text()='ALL Units']")).click();
//		driver.findElement(By.xpath("//button[text()='CREATE NEW UNIT']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='unitName']")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='unitName']")).isDisplayed(),"Unit Name Feild");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='sectoralClassification']")).isDisplayed(),"Sectorial Classification Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='accountingPeriod']")).isDisplayed(),"Accounting period Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmployees']")).isDisplayed(),"Number of Employees Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalUSD']")).isDisplayed(),"Revenue Feild");
		assertTrue(driver.findElement(By.xpath("//button[text()='Next']")).isDisplayed(),"Next Button");
	}
	
	
	//@Test
	public void test_03ValidateAddUnitDetailsPage() {
		extentTest = extentReports.createTest("Validate Add Unit Details Page");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='unitName']")).getAttribute("aria-required"), "true", "Validation Faild");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='sectoralClassification']/following-sibling::mat-error")).isDisplayed(),"Error 2");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='accountingPeriod']/following-sibling::mat-error")).isDisplayed(),"Error 3");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmployees']/following-sibling::mat-error")).isDisplayed(),"Error 4");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalUSD']/following-sibling::mat-error")).isDisplayed(),"Error 5");
		
	}
	
	@Test
	public void test_04AddAddressPage() {
		extentTest = extentReports.createTest("Add Address Page");
		driver.findElement(By.xpath("//input[@formcontrolname='unitName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//select[@formcontrolname='sectoralClassification']")).sendKeys("One");
		driver.findElement(By.xpath("//input[@formcontrolname='accountingPeriod']")).sendKeys("April, 2023");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmployees']")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@formcontrolname='totalUSD']")).sendKeys("200");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='unitAddress']")).isDisplayed(),"Unit Address Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='city']")).isDisplayed(),"City Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='zipCode']")).isDisplayed(),"zipcode Feild");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='country']")).isDisplayed(),"Country Feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='lattitudeLongtitude']")).isDisplayed(),"lattitudeLongtitude Feild");
		assertTrue(driver.findElement(By.xpath("//div[@role='alert' and contains(text(),'Note')]")).isDisplayed(),"Note in Red Color");
	}
	
	//@Test
	public void test_05ValidateAddAddressPage() {
		extentTest = extentReports.createTest("Validate Add Address Page");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='unitAddress']")).getAttribute("aria-required"), "true", "Validation Faild");
		//assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='unitAddress']/following-sibling::mat-error")).isDisplayed(),"Error 1");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='city']/following-sibling::mat-error")).isDisplayed(),"Error 2");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='zipCode']/following-sibling::mat-error")).isDisplayed(),"Error 3");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='country']/following-sibling::mat-error")).isDisplayed(),"Error 4");
		//assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='lattitudeLongtitude']/following-sibling::mat-error")).isDisplayed(),"Error 5");
		
	}
	
	
	@Test
	public void test_06SelectCaptitalsPage() {
		extentTest = extentReports.createTest("Select Captitals Page");
		driver.findElement(By.xpath("//input[@formcontrolname='unitAddress']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='zipCode']")).sendKeys("400710");
		driver.findElement(By.xpath("//select[@formcontrolname='country']")).sendKeys("India");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='lattitudeLongtitude']/following::button[text()='Next']")));
		assertTrue(driver.findElement(By.xpath("//h5[text()='NCX']")).isDisplayed(),"NCX Text");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='naturalCapital']")).isDisplayed(),"NCX Checkbox");
		assertTrue(driver.findElement(By.xpath("//h5[text()='HCX']")).isDisplayed(),"HCX Text");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='humanCapital']")).isDisplayed(),"HCX Checkbox");
		assertTrue(driver.findElement(By.xpath("//h5[text()='SCX']")).isDisplayed(),"SCX Text");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='socialCapital']")).isDisplayed(),"SCX Checkbox");
		assertTrue(driver.findElement(By.xpath("//h5[text()='FCX']")).isDisplayed(),"FCX Text");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='financialCapital']")).isDisplayed(),"FCX Checkbox");
	}
	
	//@Test
	public void test_07ValidateSelectCapitalPage() {
		extentTest = extentReports.createTest("Validate Select Capital Page");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='naturalCapital']")).getAttribute("aria-required"), "true", "Validation Faild");	
		}
	
	@Test
	public void test_08UnitAddedSuccessfullPage() {
		extentTest = extentReports.createTest("Unit Added Successfull Page");
		driver.findElement(By.xpath("//input[@formcontrolname='naturalCapital']")).click();
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		assertTrue(driver.findElement(By.xpath("//span[contains(@class,'badge')]")).isDisplayed(),"Data Not Saved");
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
