package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS extends TestConfig{
	
	@Test
	public void TEST01_LMSPage() {
		extentTest = extentReports.createTest("LMS Dashboard");
		assertTrue(driver.findElement(By.xpath("//span[text()='LMS']")).isDisplayed(),"LMS Tab should be visible");
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
//		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Apply Leave')]")).isDisplayed(),"Apply Leave Button should be visible");
//		driver.findElement(By.xpath("//button[contains(text(),'Apply Leave')]")).click();
		
	}
	
	//@Test
	public void Test_08ApplyLeavePage() {
		extentTest = extentReports.createTest("Apply PL Page");
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]")).isDisplayed(),"Priviledge Leave Button should be visible");
		driver.findElement(By.xpath("//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]")).click();
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).isDisplayed(),"Date Field should be visible");
		LocalDate date = LocalDate.now().plusDays(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String weekDay = (date.getDayOfWeek()).toString();
		int leavesCount=2;
		if(weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY"))
		{
			date = LocalDate.now().plusDays(12);
		}
		driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).sendKeys(date.format(formatter).toString() + Keys.TAB);
		int expectednumber = 2;
		date = date.plusDays(expectednumber);
		weekDay = (date.getDayOfWeek()).toString();
		if(weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY"))
		{
			date = date.plusDays(2);
			leavesCount=3;
		}
		driver.findElement(By.xpath("//input[@formcontrolname='toDate']")).sendKeys(date.format(formatter).toString() + Keys.TAB);
		//driver.findElement(By.xpath("//label[contains(text(),'First-Half Of')]")).click();
		assertEquals(driver.findElement(By.xpath("//span[contains(@class,'badge count')]")), leavesCount+" Days","Leave days should not include Saturday or Sunday");
		driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Test");
		driver.findElement(By.xpath("//span[text()='Apply ']")).click();
	}
	
	//@Test
	public void Test_09ApplyCompoffRequestPage() {
		extentTest = extentReports.createTest("Compoff Request Page");
		assertTrue(driver.findElement(By.xpath("//span[text()='LMS']")).isDisplayed(),"LMS Tab should be visible");
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Comp Off Request')]")).isDisplayed(),"Comp Off Request should be visible");
		driver.findElement(By.xpath("//button[contains(text(),'Comp Off Request')]")).click();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String weekDay = (date.getDayOfWeek()).toString();
		while(!(weekDay.equals("SUNDAY")))
		{
			date = date.minusDays(1);	
			weekDay = (date.getDayOfWeek()).toString();
		}
		driver.findElement(By.xpath("//input[@formcontrolname='workingDate']")).sendKeys(date.format(formatter).toString() + Keys.TAB );
		driver.findElement(By.xpath("//input[@formcontrolname='reason']")).sendKeys("WeekOff");
		driver.findElement(By.xpath("//span[text()='Apply ']")).click();
		
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String records = date.format(formatter);
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "CompOff request send successfully.","Success message should be displayed");
		List<WebElement> elements = driver.findElements(By.xpath("//td[text()='CompOff']/following-sibling::td"));
		for(WebElement e:elements)
		{
			assertTrue(e.getText().contains(records),"newly added record should be displyed");
		}
		
	}
	
	//@Test
	public void Test_10Employeelist() {
		extentTest = extentReports.createTest("Employee List Page");
		assertTrue(driver.findElement(By.xpath("//span[text()='LMS']")).isDisplayed(),"LMS Tab should be visible");
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Employee List')]")).isDisplayed(),"Employee List should be visible");
		driver.findElement(By.xpath("//button[contains(text(),'Employee List')]")).click();
		
	}
	
	//@Test
	public void Test_11AddEmployee() {
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Add Employee Details')]")).isDisplayed(),"Add Employee button should be visible");
		driver.findElement(By.xpath("//span[contains(text(),'Add Employee Details')]")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='employeeCode']")).sendKeys("1034");
		driver.findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='fatherName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='surname']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@formcontrolname='motherName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='phoneNumber']")).sendKeys("9757477797");
		driver.findElement(By.xpath("//input[@formcontrolname='alternateNumber']")).sendKeys("8898730207");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("test@antllp.com");
		driver.findElement(By.xpath("//input[@formcontrolname='personalEmail']")).sendKeys("test@gmail.com"+ Keys.TAB);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='bloodGroup']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='A+']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='02/07/2023';", driver.findElement(By.xpath("//input[@formcontrolname='dateOfBirth']")));
		driver.findElement(By.xpath("//mat-select[@formcontrolname='gender']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Male']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='maritalStatus']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Married']")).click();
		
	}
}
