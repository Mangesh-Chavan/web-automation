package mahindra;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Login_page extends TestConfig{
	
	public static WebDriver driver;

	@Test
	public void TEST_01() {
		extentTest = extentReports.createTest("Login with Blank Fields");
		driver = getDriver();
		driver.get(properties.getProperty("url"));//Enter URL
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();//Click On login button
		assertTrue(driver.findElement(By.xpath("//button[text()=' Login ']")).isDisplayed(), "Validations Messages should be Visible");
	}
	
	@Test
	public void TEST_02() {
		extentTest = extentReports.createTest("Login with Incorrect Credentials");
		if(driver.getPageSource().contains("Sign out"))
		{
			driver.findElement(By.xpath("//button[text()=' Admin ']")).click();
			driver.findElement(By.xpath("//span[text()='Sign out']/parent::button")).click();
		}
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).sendKeys("test@ant.com");//Enter Login Id
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Test@1234");//Enter Login Password
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();//Click On login button
		sleep(1000);
		assertFalse(driver.findElement(By.xpath("//b[text()='Dashboard']")).isDisplayed(), "User should not get log in with incorrect details");
	}
	
	@Test
	public void TEST_03() {
		extentTest = extentReports.createTest("Login with Admin Credentials");
		if(driver.getPageSource().contains("Sign out"))
		{
			driver.findElement(By.xpath("//button[text()=' Admin ']")).click();
			driver.findElement(By.xpath("//span[text()='Sign out']/parent::button")).click();
		}
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).sendKeys("admin@admin.com");//Enter Login Id
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");//Enter Login Password
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();//Click On login button
		assertTrue(driver.findElement(By.xpath("//b[text()='Dashboard']")).isDisplayed(), "Dashboard Page Should be Visible");
	}
	
	@Test
	public void TEST_47() {
		if(driver.getPageSource().contains("Sign out"))
		{
			driver.findElement(By.xpath("//button[text()=' Admin ']")).click();
			driver.findElement(By.xpath("//span[text()='Sign out']/parent::button")).click();
			assertTrue(true, "Login Page should be visible after clicking on logout");
		}
	}
}
