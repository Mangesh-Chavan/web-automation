package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Attendace extends TestConfig{
	
	LocalDate date;
	
	JavascriptExecutor js;
	
	String compDate;

	private WebElement element;

	@Test
	public void TEST01_AttendacePage() {
		extentTest = extentReports.createTest("Attendace Page");
		sleep(1000);
		driver.findElement(By.xpath("//span[text()='Attendance']")).click();
		sleep(2000);
		date = LocalDate.now();
		String month = date.format(DateTimeFormatter.ofPattern("MMMM"));
		String year = date.format(DateTimeFormatter.ofPattern("yyyy"));
		assertEquals(driver.findElement(By.xpath("//mat-select[@formcontrolname='month']/div/div/span/span")).getText(), month ,"Should show current month");
		assertEquals(driver.findElement(By.xpath("//mat-select[@formcontrolname='year']/div/div/span/span")).getText(), year,"Should show current year");
		element = driver.findElement(By.xpath("//button[text()=' Mark Attendance ']"));
		js = (JavascriptExecutor) driver;
		assertTrue(driver.findElement(By.xpath("//button[text()=' Mark Attendance ']")).isDisplayed(), "Mark Attendace tile should be displayed");
	}
	
	@Test
	public void TEST02_MarkAttendacewithPL() {
		extentTest = extentReports.createTest("Mark Attendace on Applied PL date");
		element.click();
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
		date = LocalDate.now().minusDays(20);
		String weekDay = (date.getDayOfWeek()).toString();
		if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
			date = date.plusDays(2);
		}
		compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		System.out.println("date is "+compDate);
		while (!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"You have applied leave on same dates.",
				"Error message should be displayed");//popoup is opened
		//close
	}
	
	@Test
	public void TEST03_MarkAttendacewithWeekoff() {
		extentTest = extentReports.createTest("Mark Attendace on Weekends");
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		if(driver.getPageSource().contains("Request"))
		{
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		}
		element.click();
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
		date = LocalDate.now().minusDays(20);
		String weekDay = (date.getDayOfWeek()).toString();
		while(!(weekDay.equals("SUNDAY")))
		{
			date = date.minusDays(1);	
			weekDay = (date.getDayOfWeek()).toString();
		}
		compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		
		while (!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			sleep(1000);
		}
		driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"Sorry, Attendance cannot be marked on weekends",
				"Error message should be displayed");
	}
	
	@Test
	public void TEST04_MarkAttendacewithHoliday() {
		extentTest = extentReports.createTest("Mark Attendace on Holidays");
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		if(driver.getPageSource().contains("Request"))
		{
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		}
		element.click();
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
		while (!driver.getPageSource().contains("MAY 2023")) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' 1 ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"Sorry, Attendance cannot be marked on holidays",
				"Error message should be displayed");
	}
	
	@Test
	public void TEST05_MarkAttendaceforFutureDate() {
		extentTest = extentReports.createTest("Mark Attendace for Future Date");
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		if(driver.getPageSource().contains("Request"))
		{
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		}
		element.click();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Select Date')]")).click();
		date = LocalDate.now().plusDays(20);
		String weekDay = (date.getDayOfWeek()).toString();
		if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
			date = date.plusDays(2);
		}
		String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		
		while (!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Next month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"You Cannot Mark Attendance Requests For Future Dates !",
				"Error message should be displayed");
	}
	
	@Test
	public void TEST06_MarkAttendace() {
		extentTest = extentReports.createTest("Mark Attendace");
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		if(driver.getPageSource().contains("Request"))
		{
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		}
		element.click();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Select Date')]")).click();
		date = LocalDate.now().minusDays(10);
		String weekDay = (date.getDayOfWeek()).toString();
		if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
			date = date.plusDays(2);
		}
		String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		
		while (!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"Attendance Request Successfully Submitted",
				"Success message should be displayed");
	}
	
	@Test
	public void TEST07_DuplicateMarkAttendace() {
		extentTest = extentReports.createTest("Mark Attendace");
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		if(driver.getPageSource().contains("Request"))
		{
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		}
		element.click();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Select Date')]")).click();
		date = LocalDate.now().minusDays(10);
		String weekDay = (date.getDayOfWeek()).toString();
		if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
			date = date.plusDays(2);
		}
		String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
		
		while (!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
		sleep(500);
		driver.findElement(By.xpath("//input[@formcontrolname='checkIn']")).sendKeys("09:00AM");
		driver.findElement(By.xpath("//input[@formcontrolname='checkOut']")).sendKeys("06:30PM");
		driver.findElement(By.xpath("//button[text()='Request']")).click();
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
				"Request is Approved or Pending for same date.",
				"Error message should be displayed");
	}

}
