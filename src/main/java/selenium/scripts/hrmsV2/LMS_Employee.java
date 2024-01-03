package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_Employee extends TestConfig {
	
	JavascriptExecutor js;
	
	
	
	
	
	@Test
	public void TEST01_EmployeeList() {
		extentTest = extentReports.createTest("Employee List Page");
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Employee List')]")).isDisplayed(),"Employee List should be visible");
		driver.findElement(By.xpath("//button[contains(text(),'Employee List')]")).click();
		
	}
	
	
	
	@Test
	public void TEST02_PersonalDetails() {
		extentTest = extentReports.createTest("PersonalDetails");
		js = (JavascriptExecutor) driver;
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Add Employee Details')]")).isDisplayed(),"Add Employee button should be visible");
		driver.findElement(By.xpath("//span[contains(text(),'Add Employee Details')]")).click();
		WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='employeeCode']"));
		String empcode = element.getAttribute("value");
		driver.findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='fatherName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='surname']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@formcontrolname='motherName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='phoneNumber']")).sendKeys("9757477797");
		driver.findElement(By.xpath("//input[@formcontrolname='alternateNumber']")).sendKeys("8898730207");
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(empcode+"@antllp.com");
		driver.findElement(By.xpath("//input[@formcontrolname='personalEmail']")).sendKeys("test@gmail.com"+ Keys.TAB);
		sleep(100);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='bloodGroup']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='A+']")).click();
		String compDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
		driver.findElement(By.xpath("//span[text()='"+compDate+"']")).click();
		while(!driver.getPageSource().contains("1996"))
		{
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous')]")).click();
		}
		LocalDate date = LocalDate.now().plusDays(2);
		compDate = date.format(DateTimeFormatter.ofPattern("MMM")).toUpperCase();
		driver.findElement(By.xpath("//span[text()=' 1996 ']")).click();
		sleep(100);
		driver.findElement(By.xpath("//span[text()=' "+compDate+" ']")).click();
		sleep(100);
		driver.findElement(By.xpath("//span[text()=' "+date.getDayOfMonth()+" ']")).click();
		sleep(100);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='gender']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Male']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='maritalStatus']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Married']")).click();
		sleep(100);
		driver.findElement(By.xpath("//input[@formcontrolname='currentAddress']")).sendKeys("Tester Testing");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		String address = driver.findElement(By.xpath("//input[@formcontrolname='permanantAddress']")).getAttribute("ng-reflect-model");
		assertEquals(address, "Tester Testing","on clicking checkbox Permanat address should be same as actual address");
		//driver.findElement(By.xpath("//input[@formcontrolname='attendanceBonus']")).sendKeys("300");
		int pf = (int) (((350000/12)*0.4)*0.12);
		driver.findElement(By.xpath("//input[@formcontrolname='pf']")).sendKeys(pf+"" + Keys.TAB);
		sleep(100);
		driver.findElement(By.xpath("//mat-label[text()='Date Of Joining']/following::button[@aria-label='Open calendar']")).click();
		driver.findElement(By.xpath("//span[text()=' "+LocalDate.now().getDayOfMonth()+" ']")).click();
		sleep(100);
		driver.findElement(By.xpath("//input[@formcontrolname='officeLocation']")).sendKeys("Mahape"+Keys.TAB);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='reportsTo']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='New Approver']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='departmentId']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='IT']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='designationId']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Test Engineer']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='roleId']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Employee']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='panNumber']")).sendKeys("CPCBC2203X");
		driver.findElement(By.xpath("//input[@formcontrolname='aadharNumber']")).sendKeys("577278913572");
		driver.findElement(By.xpath("//input[@formcontrolname='passportNumber']")).sendKeys("F7897867");
		driver.findElement(By.xpath("//input[@formcontrolname='uanNumber']")).sendKeys("101784387191");
		driver.findElement(By.xpath("//input[@formcontrolname='ctc']")).sendKeys("350000");
		driver.findElement(By.xpath("//input[@formcontrolname='shiftTime']")).sendKeys("09:30");
		sleep(500);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='inActiveReason']")).click();
		sleep(500);
		driver.findElement(By.xpath("//mat-option/span[text()='Active']")).click();
		sleep(3000);
		driver.findElement(By.xpath("//span[text()='Personal Details']/following::span[text()='Save']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Employee details has been added.","Success message should be displayed");
		
	}
	
	@Test
	public void TEST03_EmgContactDetails() {
		extentTest = extentReports.createTest("Emg Contact Details");
		driver.findElement(By.xpath("//span[text()='Personal Details']/following::span[text()=' Next ']")).click();
		driver.findElement(By.xpath("//mat-label[text()='Name']/following::input[@formcontrolname='name']")).sendKeys("Mahesh");
		driver.findElement(By.xpath("//input[@formcontrolname='relationship']")).sendKeys("Brother");
		driver.findElement(By.xpath("//mat-label[text()='Name']/following::input[@formcontrolname='phoneNumber']")).sendKeys("8108938994");
		driver.findElement(By.xpath("//mat-label[text()='Relationship']/following::span[text()='Save']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Employee details has been added.","Success message should be displayed");
		
	}
	
	@Test
	public void TEST04_BankInfoDetails() {
		extentTest = extentReports.createTest("Bank Info Details");
		driver.findElement(By.xpath("//mat-label[text()='Relationship']/following::span[text()=' Next ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='bankName']")).isDisplayed();
		driver.findElement(By.xpath("//input[@formcontrolname='bankAccountNumber']")).isDisplayed();
		driver.findElement(By.xpath("//input[@formcontrolname='ifscCode']")).isDisplayed();
		driver.findElement(By.xpath("//input[@formcontrolname='micrNumber']")).isDisplayed();
		driver.findElement(By.xpath("//mat-label[text()='Bank Name']/following::span[text()='Save']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Employee details has been added.","Success message should be displayed");
	}
	
	@Test
	public void TEST05_EducationDetails() {
		extentTest = extentReports.createTest("Education Details");
		driver.findElement(By.xpath("//mat-label[text()='Bank Name']/following::span[text()=' Next ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='collegeUniversityName']")).sendKeys("University of Mumbai");
		driver.findElement(By.xpath("//input[@formcontrolname='degree']")).sendKeys("MBA");
		driver.findElement(By.xpath("//mat-select[@formcontrolname='educationYear']")).click();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='2019']")));
		//driver.findElement(By.xpath("//span[text()='Add ']")).click();
		driver.findElement(By.xpath("//mat-label[text()='Degree']/following::span[text()='Save']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Employee details has been added.","Success message should be displayed");
	}
	
	@Test
	public void TEST06_WorkExperiancelDetails() {
		extentTest = extentReports.createTest("Work Experiancel Details");
		driver.findElement(By.xpath("//mat-label[text()='Degree']/following::span[text()=' Next ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='companyName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@formcontrolname='post']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@formcontrolname='from']")).sendKeys("25/01/2022");
		driver.findElement(By.xpath("//input[@formcontrolname='to']")).sendKeys("08/05/2023");
		//driver.findElement(By.xpath("//mat-label[text()='Post']/following::span[text()='Add ']")).click();
		driver.findElement(By.xpath("//mat-label[text()='Post']/following::span[text()='Save']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Employee details has been added.","Success message should be displayed");
	}
	
	
	@Test
	public void TEST07_AddNotifierPage() {
		extentTest = extentReports.createTest("Add Notifier Page");
		driver.findElement(By.xpath("//span[text()='Add Viewer']")).click();
		driver.findElement(By.xpath("//input[@role='combobox']")).click();
		driver.findElement(By.xpath("//span[contains(text(),' New Approver ')]")).click();
		driver.findElement(By.xpath("//span[text()=' Create ']")).click();
	}
}
