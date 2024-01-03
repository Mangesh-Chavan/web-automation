package selenium.scripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LM_Lead_Master_Management extends TestConfig {
	
	public static WebDriver driver;
	
	@Test
	public void test01_HomePage() {
		driver = getDriver();
		driver.get("https://leadmanagement.projectnimbus.co.in/");
		assertEquals(true, driver.findElement(By.xpath("//a[contains(@href,'Login')]")).isDisplayed());
	}
	
	@Test
	public void test02_LoginPage() {
		driver.findElement(By.xpath("//a[contains(@href,'Login')]")).click();
		assertEquals(true, driver.findElement(By.xpath("//input[contains(@name,'UserNameOrEmailAddress')]")).isDisplayed());
		
	}
	
	@Test
	public void test03_AfterLoginPage(){
		driver.findElement(By.xpath("//input[contains(@name,'UserNameOrEmailAddress')]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[contains(@name,'Password')]")).sendKeys("1q2w3E*");
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(@name,'Action')]")));
		assertEquals(true, driver.findElement(By.xpath("(//span[contains(text(),'Lead Master')])[2]")).isDisplayed());
	}
	
	@Test
	public void test04_LeadMasterManagementPage(){
		driver.findElement(By.xpath("(//span[contains(text(),'Lead Master')])[2]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create Lead')])")).click();
		driver.findElement(By.id("ViewModel_Name")).sendKeys("Mangesh");
		driver.findElement(By.id("ViewModel_Address")).sendKeys("abcd efgh");
		driver.findElement(By.id("ViewModel_Email")).sendKeys("man@gmail.com");
		driver.findElement(By.id("ViewModel_Mobile")).sendKeys("8888888888");
		driver.findElement(By.id("ViewModel_ReferenceBy")).sendKeys("abc");
		driver.findElement(By.id("ViewModel_Description")).sendKeys("abcdefgh");
		new Select(driver.findElement(By.id("ViewModel_LeadType"))).selectByValue("2");
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		assertEquals(false, driver.findElement(By.xpath("//span[text()='Save']")).isDisplayed());
		
	}
	
	@Test
	public void test05_LeadMasterUpdatePage(){
	
	}
}
