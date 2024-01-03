package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Dashboard_Page extends TestConfig{

	@Test
	public void Test_05HeaderSection() {
		extentTest = extentReports.createTest("Dashboard Header Section");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.now();
		assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'Welcome')]/span")).isDisplayed(), "Logged in user name should be displayed");
		assertEquals(driver.findElement(By.id("curnt-date")).getText(),formatter.format(date),"Date should be visible");
		assertTrue(driver.findElement(By.className("notification-container")).isDisplayed(),"Notification should be visible");
		assertTrue(driver.findElement(By.id("dropdownMenuLink")).isDisplayed(),"Profile Dropdown should be visible");
		
	}
	
	@Test
	public void Test_06CalenderSection() {
		extentTest = extentReports.createTest("Dashboard Calender Section");
		//assertTrue(driver.findElement(By.xpath("Total Leave")).isDisplayed(), "Total Leave chard should be visible");
		assertTrue(driver.findElement(By.xpath("//h5[text()='Working Hours']")).isDisplayed(),"Working Hours should be visible");
		assertTrue(driver.findElement(By.xpath("//h5[text()='Awards & Projects']")).isDisplayed(),"Awards & Projects should be visible");
		assertTrue(driver.findElement(By.xpath("//div[contains(@class,'dashboard-cal')]")).isDisplayed(),"Calender view should be visible");
		assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'On Leave')]")).isDisplayed(),"Who is on leave should be visible");
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Coming Holidays')]")).isDisplayed(),"coming holidays should be visible");
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Birthday')]")).isDisplayed(),"Todays birthday should be visible");
		assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Coming Birthday')]")).isDisplayed(),"Coming Birthday should be visible");
		
	}
}
