package selenium.scripts.SME.Aggregator;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.FixMethodOrder;
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

@FixMethodOrder
public class ForgotResetPasswordFlow extends TestConfig{
	
	public static WebDriver driver;
	private String password;
	
	public String getPassword() {
		String password = "Antllp@"+ Math.random();
		return password;
	}
	
	@Test
	public void test01_AggregatorLoginPage() {
		driver = getDriver();
		driver.get("http://localhost:4200/aggregator/dashboard");
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
	}
	
	@Test
	public void test02_AggregatorResetPasswordPage() {
		driver.findElement(By.xpath("//a[text()='Forgotten password?']")).click();
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
	}
	
	@Test
	public void test03_AggregatorResetPasswordPage2() throws InterruptedException {
		extentTest = extentReports.createTest("Aggregator Reset Password Page 2");
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("aggadd435@yopmail.com");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Send me instructions')]/preceding-sibling::span"));
//		Actions a =new Actions(driver);
//		a.click(element).build().perform();
		jsExecutor.executeScript("arguments[0].click();", element);
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		//code to get link from mail
		driver = getDriver();
		driver.get("https://yopmail.com/");
		driver.findElement(By.id("login")).sendKeys("aggadd435@yopmail.com"+Keys.ENTER);
//		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifinbox")));
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
			//new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
		driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div")).click();
		}
		catch(Exception e) {}
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
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).isDisplayed());//Confirm password Field Check
		
	}
	
	@Test
	public void test04_AggregatorLoginPage2() {
		extentTest = extentReports.createTest("Aggregator Login Page 2");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Abcdef@12345");
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).sendKeys("Abcd@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Reset my password and sign me in')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Error')]")).isDisplayed());//Error message
		password = getPassword();
		System.out.println(password);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Confirm password']")).sendKeys(password);
		element = driver.findElement(By.xpath("//span[contains(text(),'Reset my password and sign me in')]/preceding-sibling::span"));
		a =new Actions(driver);
		a.click(element).build().perform();
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
	}
	
	@Test
	public void test05_AggregatorDashboardPage() {
		extentTest = extentReports.createTest("Aggregator Dashboard Page");
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("aggadd435@yopmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		assertEquals(true, driver.findElement(By.xpath("//h1[text()='SME Overview']")).isDisplayed());//SME Overview label
		String nameString= driver.findElement(By.xpath("//span[@class='mdc-button__label']")).getText();
		assertEquals(nameString.contains("mangesh"), true,"Name is not visible");
		
	}
	
	@Test
	public void test06_LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/following::mat-icon[contains(text(),'down')]"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
		a.click(element).build().perform();
	}

}
