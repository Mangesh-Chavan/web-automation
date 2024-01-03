package sme360x;

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

public class SME_Forgot_Reset_Password_Flow extends TestConfig{
	
	public static WebDriver driver;
	
	private String password;
	
	private String email;
	
	public String getPassword() {
		int Number = (int) Math.floor(1000 + Math.random() * 9000);
		String password = "Antllp@"+ Number;
		return password;
	}
	
	@Test
	public void test01_SMEUserLoginPage() {
		extentTest = extentReports.createTest("SME User Login Page");
		driver = getDriver();
		driver.get("http://localhost:4200/login");
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
	}
	
	@Test
	public void test02_SMEUserResetPasswordPage() {
		extentTest = extentReports.createTest("SME User Reset Password Page");
		driver.findElement(By.xpath("//a[text()='Forgotten password?']")).click();
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
	}
	
	@Test
	public void test03_SMEUserResetPasswordPage2() throws InterruptedException {
		extentTest = extentReports.createTest("Aggregator Reset Password Page 2");
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		email = "SME_0008@yopmail.com";
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Send me instructions')]/preceding-sibling::span"));
//		Actions a =new Actions(driver);
//		a.click(element).build().perform();
		jsExecutor.executeScript("arguments[0].click();", element);
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		//code to get link from mail
		driver = getDriver();
		driver.get("https://yopmail.com/");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(email+Keys.ENTER);
//		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifinbox")));
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
			driver.findElement(By.id("rc-anchor-container")).click();
			sleep(2000L);
		} catch (Exception e) {
		}
		driver.switchTo().defaultContent();
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("refresh")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifinbox")));
		driver.findElement(By.xpath("//div[contains(text(),'Reset')]/parent::button")).click();
		driver.switchTo().defaultContent();
		
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
		driver.findElement(By.xpath("//span[text()='Reset password']/parent::span")).click();
		for(String s:driver.getWindowHandles())
		{
			if(!s.equals(parentWindow))
				driver.switchTo().window(s);
		}
		String generatedURL = driver.getCurrentUrl();
		String partialURL = (generatedURL.split(".org"))[1];
		String URL = "http://localhost:4200"+partialURL;
		System.out.println(URL);
		driver.get(URL);
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='New password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).isDisplayed());//Confirm password Field Check
		
	}
	
	@Test
	public void test04_SMEUserLoginPage2() {
		extentTest = extentReports.createTest("Aggregator Login Page 2");
		driver.findElement(By.xpath("//input[@placeholder='New password']")).sendKeys("Abcdef@12345");
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).sendKeys("Abcd@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Reset my password')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")));
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Error");//Error message
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Error')]")));//Error message
		password = getPassword();
		System.out.println(password);
		driver.findElement(By.xpath("//input[@placeholder='New password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='New password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).sendKeys(password);
		element = driver.findElement(By.xpath("//span[contains(text(),'Reset my password')]/preceding-sibling::span"));
		a =new Actions(driver);
		a.click(element).build().perform();
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
	}
	
	@Test
	public void test05_SMEUserDashboardPage() {
		extentTest = extentReports.createTest("Aggregator Dashboard Page");
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
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
	public void test_06LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		driver.findElement(By.xpath("//span[contains(text(),'Testing')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
	}
}
