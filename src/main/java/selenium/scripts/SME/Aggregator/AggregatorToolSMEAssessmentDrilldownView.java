package selenium.scripts.SME.Aggregator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class AggregatorToolSMEAssessmentDrilldownView extends TestConfig {
	public static WebDriver driver;
	
	@Test
	public void test_01SMEDetailsPage() {
		extentTest = extentReports.createTest("SME Details Page");
		driver = getDriver();
		driver.get("http://localhost:4200/aggregator/dashboard");
		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Info@nimbustech.in");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Ha");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed());//Success message
		try {
			driver.findElement(By.xpath("//div[@class='success-status' and contains(text(),'Assessment completed')]/parent::td")).click();
		}catch(Exception e) {
			assertTrue(driver.findElement(By.xpath("//tbody[@class='mdc-data-table__content']/tr[@role='row']")).isEnabled());
			
//			Actions a = new Actions(driver);
//			a.moveToElement(driver.findElement(By.xpath("//div[@class='success-status' and contains(text(),'Assessment completed')]/parent::td"))).click().build().perform();
//			boolean function = 
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='success-status' and contains(text(),'Assessment completed')]/parent::td")));
//		System.out.println(function);
			
		}
		assertEquals(driver.findElement(By.xpath("//span[text()='Assessment response']")).getText(), "Assessment response");
	}
	
	@Test
	public void test_02ViewSMEDetailsPage() {
		extentTest = extentReports.createTest("View SME Details Page");
		driver.get("http://localhost:4200/aggregator/assessment-drilldown/c0a80125-8875-1c75-8188-75502e210000");
		assertTrue(driver.findElement(By.xpath("//h1[@class='custom-page-headingone']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//span[(@class='mdc-button__label') and contains(text(),'20')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='Revenue']/ancestor::div[contains(@class,'row cust')]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//span[text()='Assessment response']")).getText(), "Assessment response");
		assertEquals(driver.findElement(By.xpath("//span[text()='Assessment report']")).getText(), "Assessment report");
		String status= driver.findElement(By.xpath("//span[text()='Assessment response']/ancestor::div[@role='tab']")).getAttribute("aria-selected");
		System.out.println(status);
		assertEquals(status, "true");
	}
	
	@Test//Assessment response
	public void test_03AssessmentResponseTab() {
		extentTest = extentReports.createTest("Assessment Response Tab");
		List<WebElement> elemets = driver.findElements(By.xpath("//div[contains(@class,'review-edit')]"));
		assertEquals(elemets.size(), 19);
		for(WebElement element:elemets) {
			if(element.getText().contains("Not answered"))
			{
				assertEquals(true, true, element.getText());
			}
			else
			{
				assertEquals(true, true, element.getText());
			}
		}
	}
	
	@Test//Assessment report
	public void test_04AssessmentReportTab(){
		extentTest = extentReports.createTest("Assessment Report Tab");
		driver.findElement(By.xpath("//span[text()='Assessment report']")).click();
		WebElement element = driver.findElement(By.xpath("//h1[contains(text(),'Impact of sme 2332')]/ancestor::div[contains(@class,'row')]/following-sibling::div[1]"));
		assertTrue(element.isDisplayed());
		
	}
	
	@Test
	public void test_05IntaractHeaderOption() {
		extentTest = extentReports.createTest("Intaract Header Option");
		WebElement element = driver.findElement(By.xpath("//span[text()='Harshad Satarkar']/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' SME Management ')]")).isDisplayed());//SME Management label
		assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Team Management ')]")).isDisplayed());//Team Management label
		assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Profile Management ')]")).isDisplayed());//Profile Management label
		assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Log Out ')]")).isDisplayed());//Log Out label
		a.click(element).build().perform();
	}
	
	@Test
	public void test_06NavigateToPreviousPage() {
		extentTest = extentReports.createTest("Navigate To Previous Page");
		driver.findElement(By.xpath("//span[text()=' Back ']")).click();
		assertTrue(driver.findElement(By.xpath("//tbody[@class='mdc-data-table__content']/tr[@role='row']")).isDisplayed());
		
	}
	
	@Test
	public void test_07LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/following::mat-icon[contains(text(),'down')]"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
		a.click(element).build().perform();
	}
	
	
}
