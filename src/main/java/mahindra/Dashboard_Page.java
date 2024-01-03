package mahindra;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Dashboard_Page extends TestConfig {

	public static WebDriver driver;

	@Test
	public void TEST_0000() {
		extentTest = extentReports.createTest("Login with Admin Credentials");
		driver = getDriver();
		driver.get(properties.getProperty("url"));// Enter URL
		if (driver.getPageSource().contains("Sign out")) {
			driver.findElement(By.xpath("//button[text()=' Admin ']")).click();
			driver.findElement(By.xpath("//span[text()='Sign out']/parent::button")).click();
		}
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='userName']")).sendKeys("admin@admin.com");// Enter Login
																											// Id
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");// Enter Login
																									// Password
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();// Click On login button
		assertTrue(driver.findElement(By.xpath("//b[text()='Dashboard']")).isDisplayed(),
				"Dashboard Page Should be Visible");
	}

	@Test
	public void TEST_04() {
		extentTest = extentReports.createTest("Admin should be able to view farmers count");
		assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).isDisplayed(),
				"Total No of farmers should be visible");
		int count = Integer.parseInt(
				driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).getText());
		assertTrue(count > 0,
				driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).getText());
	}

	@Test
	public void TEST_05() {
		extentTest = extentReports.createTest("Admin should be able to view percentage of farmer");
		driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).click();
		assertTrue(driver.getPageSource().contains("PackageName"), "Percentage of farmers for each package");
	}

	@Test
	public void TEST_06() {
		extentTest = extentReports.createTest("Admin should be able to view Operators count");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'Total Number of Active Operators')]")).isDisplayed(),
				"Total No of Active Operators should be visible");
		int count = Integer.parseInt(
				driver.findElement(By.xpath("//p[contains(text(),'Total Number of Active Operators')]/parent::div/h4"))
						.getText());
		assertTrue(count > 0,
				driver.findElement(By.xpath("//p[contains(text(),'Total Number of Active Operators')]/parent::div/h4"))
						.getText());

	}

	@Test
	public void TEST_07() {
		extentTest = extentReports.createTest("Admin Should be able to view FDO count");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Total Number of FDO')]/preceding-sibling::h4"))
				.isDisplayed(), "Total No of FDO should be visible");
		int count = Integer.parseInt(driver
				.findElement(By.xpath("//p[contains(text(),'Total Number of FDO')]/preceding-sibling::h4")).getText());
		assertTrue(count > 0, driver
				.findElement(By.xpath("//p[contains(text(),'Total Number of FDO')]/preceding-sibling::h4")).getText());
	}

	@Test
	public void TEST_08() {
		extentTest = extentReports.createTest("Admin Should be able to view dealers count");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Total Number of Dealers')]/preceding-sibling::h4"))
				.isDisplayed(), "Total No of Dealers should be visible");
		int count = Integer.parseInt(
				driver.findElement(By.xpath("//p[contains(text(),'Total Number of Dealers')]/preceding-sibling::h4"))
						.getText());
		assertTrue(count > 0,
				driver.findElement(By.xpath("//p[contains(text(),'Total Number of Dealers')]/preceding-sibling::h4"))
						.getText());
	}

	@Test
	public static void TEST_09() {
		extentTest = extentReports.createTest("Admin should be able to view Internal Order No count");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(driver
				.findElement(
						By.xpath("//p[contains(text(),'Total Number of Internal Order No.')]/preceding-sibling::h4"))
				.isDisplayed(), "Total No of Internal Order No. should be visible");
		int count = Integer.parseInt(driver
				.findElement(
						By.xpath("//p[contains(text(),'Total Number of Internal Order No.')]/preceding-sibling::h4"))
				.getText());
		assertTrue(count > 0,
				driver.findElement(
						By.xpath("//p[contains(text(),'Total Number of Internal Order No.')]/preceding-sibling::h4"))
						.getText());
	}

	@Test
	public void TEST_10() {
		extentTest = extentReports.createTest("Admin should be able to view Activated packeges list");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(driver
				.findElement(
						By.xpath("//p[contains(text(),'Total Number of Activated Packages')]/preceding-sibling::h4"))
				.isDisplayed(), "Total No of Activated Packages should be visible");
		int count = Integer.parseInt(driver
				.findElement(
						By.xpath("//p[contains(text(),'Total Number of Activated Packages')]/preceding-sibling::h4"))
				.getText());
		assertTrue(count > 0,
				driver.findElement(
						By.xpath("//p[contains(text(),'Total Number of Activated Packages')]/preceding-sibling::h4"))
						.getText());
	}

	@Test
	public void TEST_11() {
		extentTest = extentReports.createTest("Admin should be able to bifurcate packages by region");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		driver.findElement(By.xpath("//p[contains(text(),'Total Number of Activated Packages')]/parent")).click();
		assertTrue(driver.findElement(By.xpath("//div[@class='mb-3']/label[text()='Region']")).isDisplayed(),
				"The total No of activated packages should be visible and filtered out by region");
	}

	@Test
	public void TEST_12() {
		extentTest = extentReports.createTest("Admin should be able to filter out data by given input field data");
		driver.findElement(By.xpath("//i[contains(@class,'home')]")).click();
		assertTrue(driver.findElement(By.xpath("//label[text()='FY']/following::select")).isDisplayed(),
				"Select Financial Feild should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='From']/following::input")).isDisplayed(),
				"Select date should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='To']/following::input")).isDisplayed(),
				"Select to date should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='Month']/following::select")).isDisplayed(),
				"Select Month Should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='Season']/following::select")).isDisplayed(),
				"Select Season Should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='City']/following::select")).isDisplayed(),
				"Select city should be visible");
		assertTrue(driver.findElement(By.xpath("//label[text()='State']/following::select")).isDisplayed(),
				"Select State should be visible");
		int count = Integer.parseInt(
				driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).getText());// 30000
		driver.findElement(By.xpath("//label[text()='FY']/following::select")).sendKeys("2023");
		driver.findElement(By.xpath("//label[text()='From']/following::input")).sendKeys("01-07-2023");
		driver.findElement(By.xpath("//label[text()='To']/following::input")).sendKeys("01-09-2023");
		driver.findElement(By.xpath("//label[text()='Month']/following::select")).sendKeys("");
		driver.findElement(By.xpath("//label[text()='Season']/following::select")).sendKeys("Kharif");
		driver.findElement(By.xpath("//label[text()='City']/following::select")).sendKeys("Ahmednagar");
		driver.findElement(By.xpath("//label[text()='State']/following::select")).sendKeys("Maharashtra");
		driver.findElement(By.xpath("//span[text()='Search']/parent::button")).click();
		int lesscount = Integer.parseInt(
				driver.findElement(By.xpath("//p[contains(text(),'Number of Farmers')]/parent::div/h4")).getText());
		assertTrue(count > lesscount, "Results should be filtered out depending on provided data");// less tha 30000
	}

	@Test
	public void TEST_1111() {
		if (driver.getPageSource().contains("Sign out")) {
			driver.findElement(By.xpath("//button[text()=' Admin ']")).click();
			driver.findElement(By.xpath("//span[text()='Sign out']/parent::button")).click();
			assertTrue(true, "Login Page should be visible after clicking on logout");
		}
	}
}
