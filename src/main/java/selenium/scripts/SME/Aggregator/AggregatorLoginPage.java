package selenium.scripts.SME.Aggregator;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

@FixMethodOrder
public class AggregatorLoginPage extends TestConfig {
	
	public static WebDriver driver;
	
	@Test
	public void test01_AggregatorLoginPage() {
		extentTest = extentReports.createTest("Aggregator Login Page");
		driver = getDriver();
		driver.get("http://localhost:4200/aggregator/dashboard");
		assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isDisplayed());//Email Field Check
		assertEquals(true, driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed());//Password Field Check
		assertEquals(true, driver.findElement(By.xpath("//a[text()='Forgotten password?']")).isDisplayed());// Forget Password Field Check
		
	}
	
	@Test
	public void test02_AggregatorInvalidCredentialsPage() {
		extentTest = extentReports.createTest("Aggregator Invalid Credentials Page");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Info@nimbustech.in");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Hb");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		//driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")));
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Error");//Error message
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Error')]")));
	}
	
	@Test
	public void test03_AggregatorPasswordvisibilityCheck() {
		extentTest = extentReports.createTest("Aggregator Password Visibility Check");
		driver.findElement(By.id("hide-show")).click();
		//Password should be visible
		assertEquals(true, driver.findElement(By.xpath("//input[contains(@placeholder,'Password') and contains(@type,'text')]")).isDisplayed());
	}
	
	@Test
	public void test04_AggregatorDashboardPage() {
		extentTest = extentReports.createTest("Aggregator Dashboard Page");
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Info@nimbustech.in");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Ha");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")));
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Success!");//Success message
		assertEquals(true, driver.findElement(By.xpath("//h1[text()='SME Overview']")).isDisplayed());//SME Overview label
		String nameString= driver.findElement(By.xpath("//span[@class='mdc-button__label']")).getText();
		assertEquals(nameString.contains("Harshad"), true,"Name is not visible");
	}
	
	@Test
	public void test05_LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/following::mat-icon[contains(text(),'down')]"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
		a.click(element).build().perform();
	}

}
