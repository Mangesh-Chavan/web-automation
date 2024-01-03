package mahindra;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.configuration.TestConfig;

public class Users_Page extends TestConfig {

	WebDriver driver;
	WebElement element;

	public void TEST_001() {
		extentTest = extentReports.createTest("Login with Admin Credentials");
		driver = getDriver();
		driver.get("url");// Enter URL
		driver.findElement(By.xpath("")).clear();
		driver.findElement(By.xpath("")).sendKeys("");// Enter Login Id
		driver.findElement(By.xpath("")).clear();
		driver.findElement(By.xpath("")).sendKeys("");// Enter Login Password
		driver.findElement(By.xpath("")).click();// Click On login button
		assertTrue(driver.findElement(By.xpath("//h4[text()='Dashboard']")).isDisplayed(),
				"Dashboard Page Should be Visible");
	}

	public void TEST_13() {
		extentTest = extentReports.createTest("When admin wants to add new User");
		assertTrue(driver.findElement(By.xpath("//span[text()='Administration']/parent::a")).isDisplayed(),
				"Administration Tab should be visible");
		driver.findElement(By.xpath("//span[text()='Administration']/parent::a")).click();
		assertTrue(driver.findElement(By.xpath("//a[text()='Users']")).isDisplayed(), "Users Tab Should be Visible");
		driver.findElement(By.xpath("//a[text()='Users']")).click();
		assertTrue(driver.findElement(By.xpath("//a[text()='Users']")).isDisplayed(),
				"Add User button Should be Visible");
		driver.findElement(By.xpath("//span[text()='Add']/parent::button")).click();
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Ref Code Feild should be Visible");
		element.sendKeys("001");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Username']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Username Feild should be Visible");
		element.sendKeys("Test2023");
		element = driver.findElement(By.xpath("//label[text()='Password']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Password Feild should be Visible");
		element.sendKeys("Test@2023");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='First Name']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "First Name Feild should be Visible");
		element.sendKeys("Test");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Last Name']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Last Name Feild should be Visible");
		element.sendKeys("Tester");
		element = driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Email Feild should be Visible");
		element.sendKeys("Test@antllp.com");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Mobile']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Mobile Feild should be Visible");
		element.sendKeys("7304221252");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Email']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "User Role Feild should be Visible");
		element.sendKeys("FDO");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Operator Type']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Operator Type Field should be Visible");
		element.sendKeys("Machine");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Project Coordinator']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Project Coordinator Field should be Visible");
		element.sendKeys("Joyal");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Fullfillment Officer']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Fullfillment Officer Field should be Visible");
		element.sendKeys("Sachin");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Start Date']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Select Start Date Field should be Visible");
		element.sendKeys("12092023");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='End Date']/following-sibling::input"));
		assertEquals(element.isDisplayed(), "Select End Date Field should be Visible");
		element.sendKeys("30092023");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Cluster']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Cluster Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		element = driver.findElement(By.xpath("//label[text()='Ref Code']/following::label[text()='Reporting Manager']/following-sibling::select"));
		assertEquals(element.isDisplayed(), "Reporting Manager Field should be Visible");
		element.sendKeys("Mangesh Chavan");
		
	}
}
