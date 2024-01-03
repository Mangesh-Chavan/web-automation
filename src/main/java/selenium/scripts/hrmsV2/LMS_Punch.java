package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_Punch extends TestConfig{
	
	@Test
	public void TEST01_LMSPage() {
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
	}
	
	@Test
	public void TEST02_Punch_IN() {
		extentTest = extentReports.createTest("Punch IN");
		driver.findElement(By.xpath("//button[contains(text(),'Punch')]")).click();
		driver.findElement(By.xpath("//button[text()='In']")).click();
		assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'IN')]")).getText(), "Your 'IN'-Time is marked successfully.","Success message should be displayed");
	}
	
	@Test
	public void TEST03_Duplicate_Punch_IN() {
		extentTest = extentReports.createTest("Duplicate Punch IN");
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Punch')]")).click();
		driver.findElement(By.xpath("//button[text()='In']")).click();
		assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'IN')]")).getText(), "Your 'IN'-Time is already marked.","Duplicate login should be displayed");
		//driver.findElement(By.xpath("//button[text()='Done']")).click();
	}
	
	@Test
	public void TEST04__Punch_OUT() {
		extentTest = extentReports.createTest("Punch OUT");
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Punch')]")).click();
		driver.findElement(By.xpath("//button[text()='Out']")).click();
		assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'out')]")).getText(), "Your 'out'-Time is marked successfully.","Sccess message should be displayed");
	}
	
	@Test
	public void TEST05_Duplicate_Punch_OUT() {
		extentTest = extentReports.createTest("Duplicate Punch OUT");
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Punch')]")).click();
		driver.findElement(By.xpath("//button[text()='Out']")).click();
		assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'out')]")).getText(), "Your 'out'-Time is marked successfully.","Success message should be displayed");
		driver.findElement(By.xpath("//button[text()='Done']")).click();
	}
	
}
