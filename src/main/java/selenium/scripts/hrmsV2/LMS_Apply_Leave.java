package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_Apply_Leave extends TestConfig {

	@Test(priority = 0)
	public void Login() {

		driver.get("https://hrmsxapi.antllp.com/Account/Login");
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Advanced')]")).click();
			driver.findElement(By.xpath("//a[contains(text(),'hrmsxapi')]")).click();
		} catch (Exception e) {
		}
		driver.findElement(By.id("LoginInput_UserNameOrEmailAddress")).sendKeys("DoctorStrange");
		driver.findElement(By.id("LoginInput_Password")).sendKeys("Doctor@7758658963");
		driver.findElement(By.name("Action")).click();
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("DoctorStrange");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Doctor@7758658963");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
	}
	
	@Test(priority = 2)
	public void LMS_Page() {
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
	}

	@Test
	public void TEST01_LeavesTypesPage() {
		extentTest = extentReports.createTest("All Type Leaves Page");
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Apply Leave')]")).isDisplayed(),
				"Apply Leave Button should be visible");
		driver.findElement(By.xpath("//button[contains(text(),'Apply Leave')]")).click();

	}

	@Test
	public void TEST02_Apply_PL() {
		if (Login_Page.isAdmin  || Login_Page.isHR) {
			extentTest = extentReports.createTest("Apply PL Page");
			assertTrue(driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.isDisplayed(), "Priviledge Leave Button should be visible");
			driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.click();
			sleep(1000);
			assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).isDisplayed(),
					"Date Field should be visible");
			LocalDate date = LocalDate.now().plusDays(120);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
			String weekDay = (date.getDayOfWeek()).toString();
			int leavesCount = 2;
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Next month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			int expectednumber = 1;
			date = date.plusDays(expectednumber);
			weekDay = (date.getDayOfWeek()).toString();
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//mat-label[text()='To']/following::button[@aria-label='Open calendar']"))
					.click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Next month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='toDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			// driver.findElement(By.xpath("//label[contains(text(),'First-Half
			// Of')]")).click();
			assertEquals(driver.findElement(By.xpath("//span[contains(@class,'badge count')]")).getText(),
					leavesCount + " Days", "Leave days should not include Saturday or Sunday");
			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Test");
			driver.findElement(By.xpath("//span[text()='Apply ']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"You're successfully applied for leave.", "Success message should be displayed");

		}
	}

	@Test
	public void TEST03_Validate_Duplicate_PL() {
		if (Login_Page.isAdmin || Login_Page.isHR) {
			extentTest = extentReports.createTest("Validate Duplicate PL");
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			assertTrue(driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.isDisplayed(), "Priviledge Leave Button should be visible");
			driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.click();
			sleep(1000);
			assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).isDisplayed(),
					"Date Field should be visible");
			LocalDate date = LocalDate.now().plusDays(120);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
			String weekDay = (date.getDayOfWeek()).toString();
			int leavesCount = 2;
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Next month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			int expectednumber = 1;
			date = date.plusDays(expectednumber);
			weekDay = (date.getDayOfWeek()).toString();
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//mat-label[text()='To']/following::button[@aria-label='Open calendar']"))
					.click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Next month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='toDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			// driver.findElement(By.xpath("//label[contains(text(),'First-Half
			// Of')]")).click();
			assertEquals(driver.findElement(By.xpath("//span[contains(@class,'badge count')]")).getText(),
					leavesCount + " Days", "Leave days should not include Saturday or Sunday");
			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Test");
			driver.findElement(By.xpath("//span[text()='Apply ']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "You've Already Applied For A Leave For These Dates !",
					"Error message should be displayed");
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			driver.findElement(By.xpath("//button[contains(@class,'close')]")).click();
		}
	}

	@Test
	public void TEST02_New_Emp_Apply_PL() {
		if (Login_Page.isEmployee) {
			extentTest = extentReports.createTest("Apply PL Page");
			assertTrue(driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.isDisplayed(), "Priviledge Leave Button should be visible");
			driver.findElement(By.xpath(
					"//span[contains(text(),'Privilege Leave')]/following::button[contains(text(),'Apply Leave')]"))
					.click();
			sleep(1000);
			assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).isDisplayed(),
					"Date Field should be visible");
			LocalDate date = LocalDate.now().minusDays(20);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
			String weekDay = (date.getDayOfWeek()).toString();
			int leavesCount = 2;
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			int expectednumber = 1;
			date = date.plusDays(expectednumber);
			weekDay = (date.getDayOfWeek()).toString();
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//mat-label[text()='To']/following::button[@aria-label='Open calendar']"))
					.click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='toDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			// driver.findElement(By.xpath("//label[contains(text(),'First-Half
			// Of')]")).click();
			assertEquals(driver.findElement(By.xpath("//span[contains(@class,'badge count')]")).getText(),
					leavesCount + " Days", "Leave days should not include Saturday or Sunday");
			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Test");
			driver.findElement(By.xpath("//span[text()='Apply ']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"You can apply for leave after 3 months from your joining date !",
					"Error message should be displayed");
		}
	}

	@Test
	public void TEST03_New_Emp_Apply_LWP() {
		if (Login_Page.isEmployee) {
			extentTest = extentReports.createTest("Apply LWP Page");
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			driver.findElement(By.xpath("//button[contains(@class,'close')]")).click();
			assertTrue(driver.findElement(By.xpath(
					"//span[contains(text(),'Leave Without Pay')]/following::button[contains(text(),'Apply Leave')]"))
					.isDisplayed(), "Priviledge Leave Button should be visible");
			driver.findElement(By.xpath(
					"//span[contains(text(),'Leave Without Pay')]/following::button[contains(text(),'Apply Leave')]"))
					.click();
			sleep(1000);
			assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).isDisplayed(),
					"Date Field should be visible");
			LocalDate date = LocalDate.now().minusDays(20);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
			String weekDay = (date.getDayOfWeek()).toString();
			int leavesCount = 2;
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			String compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='fromDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			int expectednumber = 1;
			date = date.plusDays(expectednumber);
			weekDay = (date.getDayOfWeek()).toString();
			if (weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY")) {
				date = date.plusDays(2);
			}
			compDate = date.format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			driver.findElement(By.xpath("//mat-label[text()='To']/following::button[@aria-label='Open calendar']"))
					.click();
			while (!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' " + date.getDayOfMonth() + " ']")).click();
			// driver.findElement(By.xpath("//input[@formcontrolname='toDate']")).sendKeys(date.format(formatter).toString()
			// + Keys.TAB);
			// driver.findElement(By.xpath("//label[contains(text(),'First-Half
			// Of')]")).click();
			assertEquals(driver.findElement(By.xpath("//span[contains(@class,'badge count')]")).getText(),
					leavesCount + " Days", "Leave days should not include Saturday or Sunday");
			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Test");
			driver.findElement(By.xpath("//span[text()='Apply ']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"You're successfully applied for leave.",
					"Success message should be displayed");
		}
	}
}
