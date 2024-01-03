package sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_User_Login extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;
	
	@Test
	public void test_01SMEUserLoginPage() {
		extentTest = extentReports.createTest("SME User Login Page");
		driver = getDriver();
		driver.get("http://localhost:4200/login");
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
		
	}
	
	@Test
	public void test_02SMEUserInvalidCredentialsPage() {
		extentTest = extentReports.createTest("SME User Invalid Credentials Page");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("abc@xyz.in");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Hb");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		//driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")));
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Error");//Error message
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Error')]")));
	}
	
	@Test
	public void test_03SMEUserPasswordVisibilityCheck() {
		extentTest = extentReports.createTest("SME User Password Visibility Check");
		driver.findElement(By.xpath("//span[@aria-label='Hide password']")).click();
		//Password should be visible
		assertEquals(true, driver.findElement(By.xpath("//input[contains(@placeholder,'Password') and contains(@type,'text')]")).isDisplayed());
	}
	
	@Test
	public void test_04SMEDashboardPage() {
		extentTest = extentReports.createTest("SME Dashboard Page");
		email = AggregatorToolAddandInviteSMEusers.email;
//				"SME_0010@yopmail.com";
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")));
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Success!");//Success message
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).isDisplayed(), "Coutinue assessment button");
		
	}
	
	@Test
	public void test_05LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		driver.findElement(By.xpath("//span[contains(text(),'Testing')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
	}

}
