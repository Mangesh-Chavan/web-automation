package selenium.scripts.i360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Business_Details extends TestConfig {

	public static WebDriver driver;
	
	@Test
	public void test_001LoginPage() {
		extentTest = extentReports.createTest("Login Page");
		driver = getDriver();
		driver.get("https://i360x.projectnimbus.co.in/");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='email']")).isDisplayed(),"Email input feild");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='password']")).isDisplayed(),"Password input feild");
		assertTrue(driver.findElement(By.xpath("//button[text()=' LOG IN ']")).isDisplayed(),"Login button");
	}
	
	@Test
	public void test_01ValidateLoginPage() {
		extentTest = extentReports.createTest("Validate Login Page");
		String expectedString = "A Revolutionary Product from GIST! I360XTM (Beta)is "
				+ "a digital DIY online platform that combines advanced Sustainability Analytics with cutting edge technology, "
				+ "global data bases, algorithms developed with 40+ person years' research and cognitive search engines. I360XTM (Beta). "
				+ "makes Sustainability Impact Assessment easy, fast and affordable for every type of organization.";
		String actualString = driver.findElement(By.xpath("//p[@class='mt-2']")).getText();
		assertEquals(actualString, expectedString);
		expectedString = "Copyright © 2021 I360XTM (Beta). All Rights Reserved. Version: 1.0.1 | Privacy policy";
		actualString = driver.findElement(By.xpath("//p[@class='text-center mb-2']")).getText();
		assertEquals(actualString, expectedString);
		actualString = "A GIST Product";
		expectedString = driver.findElement(By.tagName("h1")).getText();
		assertEquals(actualString, expectedString);
	}
	
	@Test
	public void test_02BusinessDetailsPage() {
		extentTest = extentReports.createTest("Business Details Page");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("admin@gmail.com");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[text()=' LOG IN ']")).click();
		assertTrue(driver.findElement(By.xpath("//h3[@class='report-tab-subtitle']")).isDisplayed(), "Business Details");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='designation']")).isDisplayed(),"Designation Feild");
		assertTrue(driver.findElement(By.xpath("//select[@placeholder='Enter Country']")).isDisplayed(),"Enter Country Feild");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='sector']")).isDisplayed(),"Sector Feild");
		assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(),"Save and Next Button");
	}
	
	@Test
	public void test_03ValidateBusinessDetailsPage() {
		extentTest = extentReports.createTest("Validate Business Details Page");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='designation']/following-sibling::mat-error")).isDisplayed(),"Error 1");
		assertTrue(driver.findElement(By.xpath("//select[@placeholder='Enter Country']/following-sibling::mat-error")).isDisplayed(),"Error 2");
		assertTrue(driver.findElement(By.xpath("//select[@formcontrolname='sector']/following-sibling::mat-error")).isDisplayed(),"Error 3");
		
	}
	
	
	@Test
	public void test_04EmptyStateDashboardPage() {
		extentTest = extentReports.createTest("Empty State Dashboard Page");
		driver.findElement(By.xpath("//input[@formcontrolname='designation']")).sendKeys("Testing");
		driver.findElement(By.xpath("//select[@placeholder='Enter Country']")).sendKeys("India");
		driver.findElement(By.xpath("//select[@formcontrolname='sector']")).sendKeys("Small");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		assertTrue(driver.findElement(By.xpath("//button[text()=' CREATE NEW UNIT']")).isDisplayed(), "Empty state dashboard page should be visible");
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
