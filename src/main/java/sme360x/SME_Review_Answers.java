package sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class SME_Review_Answers extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;
	
	JavascriptExecutor javascriptExecutor;
	
	@Test
	public void test_01ReviewAnswersScreen() {
		extentTest = extentReports.createTest("Review Answers Screen");
	}
	
	@Test
	public void test_02ValidateReviewScreen() {
		extentTest = extentReports.createTest("Validate Review Screen");
		int questions = driver.findElements(By.xpath("//p[@class='review-question']")).size();
		assertEquals(questions, 19, questions + " Question are visible");
		int answers = driver.findElements(By.xpath("//div[@class='review-value']")).size();
		int feilds = driver.findElements(By.xpath("//div[contains(@class,'review-editicon')]")).size();
		assertEquals(feilds, 19, answers + " Answers are visible");
		
		assertEquals(feilds-answers, 19-answers, feilds-answers + " Answers are skipped");
		
		for(int i=1;i<8;i++) {
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])["+ i +"]")).isDisplayed(),"Progress Bar "+ i +" Validation");
		}
		
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Download assessment')]")).isDisplayed()," Download assessment for review ");
		assertTrue(driver.findElement(By.xpath("//button[@id='review-save']")).isDisplayed()," Save & Exit");
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'result')]")).isDisplayed(),"Continue to my results");
		
	}
	
	
	@Test
	public void test_03ValidateReviewMyAnswer() {
		extentTest = extentReports.createTest("Validate Review My Answer");
		String actualString = driver.findElement(By.xpath("//p[contains(text(),'Chemical Oxygen Demand')]")).getText();
		driver.findElement(By.xpath("//p[contains(text(),'Chemical Oxygen Demand')]/following::a[text()=' Edit ']")).click();
		String exactString = driver.findElement(By.xpath("//h2[contains(text(),'Chemical Oxygen Demand')]")).getText();
		assertEquals(actualString.contains("Chemical Oxygen"), exactString.contains("Chemical Oxygen"),"Redirect to Same Qestion");
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']")).sendKeys("400");
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']/following::span[text()='Next question']")).click();
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='14']")).getText(), "14","Number Count 14");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']")).isDisplayed(),"Total Nitrogen Question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']/following::span[text()=' Back ']")).click();
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='13']")).getText(), "13","Number Count 13");
		
		driver.findElement(By.xpath("//span[text()='Question ']/b[text()='13']/following::button[@id='ap-review']")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Review my answers']")).isDisplayed(),"Review My Answers");
	}
	
	@Test
	public void test_04ValidateReviewScreen2() {
		extentTest = extentReports.createTest("Validate Review Screen 2");
		driver.findElement(By.xpath("//h1[text()='Review my answers']/following::span[text()=' Back ']")).click();
		if(driver.findElement(By.xpath("//p[contains(text(),'location')]/following::div[@class='review-value']")).isDisplayed())
		{
			assertEquals(driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']/following::b[text()='19'][2]")).getText(), "19","Number Count 19");
		}
		else {
			assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='18']")).getText(), "18","Number Count 18");
		}
	}
	
	
	
}
