package selenium.scripts.HRMS;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class HRMS_Leave_Management extends TestConfig{
	
		public static WebDriver driver;
		
		@Test
		public void test_01LoginPage() {
			driver = getDriver();
			driver.get("https://hrms.antllp.com/");
			assertEquals(true, driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed());//login button
			assertEquals(true, driver.findElement(By.name("EmployeeId")).isDisplayed());//user name field
			assertEquals(true, driver.findElement(By.name("Password")).isDisplayed());//password Field
		}
		
		@Test
		public void test_02HRMSDashboardPage() {
			driver.findElement(By.name("EmployeeId")).sendKeys("1034");
			driver.findElement(By.name("Password")).sendKeys("Mangu@1432");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			assertEquals(true, driver.findElement(By.xpath("//h4[text()='Apply Leave']/parent::div")).isDisplayed());//Leave Apply icon
		}
		
		@Test
		public void test_03LeaveDetailsPage() {
			driver.findElement(By.xpath("//h4[text()='Apply Leave']/parent::div")).click();
			assertEquals(true, driver.findElement(By.xpath("//a[text()='Apply For Leave']")).isDisplayed());
		}
		
		@Test
		public void test_04LeaveApplyPage() {
			driver.findElement(By.xpath("//a[text()='Apply For Leave']")).click();
			assertEquals(true, driver.findElement(By.name("AttendentId")).isDisplayed());
		}
		
		@Test
		public void test_05LeaveAppliedSuccessfullyPage() throws InterruptedException {
			new Select(driver.findElement(By.name("AttendentId"))).selectByVisibleText("PL (Privilege Leave)");
			
			LocalDate date = LocalDate.now().minusDays(2);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String weekDay = (date.getDayOfWeek()).toString();
			if(weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY"))
			{
				date = LocalDate.now().plusDays(12);
			}
			
			driver.findElement(By.name("FromDate")).sendKeys(date.format(formatter).toString() + Keys.TAB);
			System.out.println(date.format(formatter).toString());
			int expectednumber = 2;
			//date = date.plusDays(expectednumber);
			weekDay = (date.getDayOfWeek()).toString();
			if(weekDay.equals("SATURDAY") || weekDay.equals("SUNDAY"))
			{
				date = date.plusDays(expectednumber+2);
			}
			Thread.sleep(10000);
//			Actions a = new Actions(driver);
//			a.click(driver.findElement(By.name("ToDate"))).sendKeys(Keys.DOWN+"" + Keys.DOWN+""+ Keys.ENTER).build().perform();
//			driver.findElement(By.name("ToDate")).click()
//			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//			jsExecutor.executeScript("arguments[0].setAttribute(value,"+date.format(formatter).toString()+");", driver.findElement(By.name("ToDate")));
//			System.out.println(date.format(formatter).toString());
//			int numberofdays = Integer.parseInt(driver.findElement(By.id("leavedays")).getText().substring(0,1));
//			assertEquals(true, numberofdays == expectednumber+1);
			driver.findElement(By.id("chkSSH")).click();
			//driver.findElement(By.id("chkEFH")).click();
			driver.findElement(By.id("Reason")).sendKeys("Sick Leave");
			driver.findElement(By.id("chkEFH")).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.id("btnSubmit")));
		}
}
