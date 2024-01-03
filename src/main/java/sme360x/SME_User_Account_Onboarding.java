package sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_User_Account_Onboarding extends TestConfig {
	public static WebDriver driver;

	public static String email;

	String parentWindow = "";

	@Test
	public void test_01InvitationLinkToJoinSMETool() {
		extentTest = extentReports.createTest("Invitation Link To Join SME Tool");
		driver = getDriver();
		email =  AggregatorToolAddandInviteSMEusers.email;
				//"SME_0010@yopmail.com";
		driver.get("https://yopmail.com/");
		driver.findElement(By.id("login")).sendKeys(email + Keys.ENTER);
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
			driver.findElement(By.id("rc-anchor-container")).click();
			sleep(2000L);
		} catch (Exception e) {
		}
		driver.switchTo().defaultContent();
		parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("refresh")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifinbox")));
		driver.findElement(By.xpath("//div[contains(text(),'Welcome')]/parent::button")).click();
		driver.switchTo().defaultContent();

		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ifmail")));
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Set')]/parent::a")).isDisplayed());

	}

	@Test
	public void test_02InvitaionLinkPage() {
		extentTest = extentReports.createTest("Invitaion Link Page");
		driver.findElement(By.xpath("//span[contains(text(),'Set')]/parent::a")).click();
		for (String s : driver.getWindowHandles()) {
			if (!s.equals(parentWindow))
				driver.switchTo().window(s);
		}
		String generatedURL = driver.getCurrentUrl();
		String partialURL = (generatedURL.split(".org"))[1];
		String URL = "http://localhost:4200"+partialURL;
		System.out.println(URL);
		driver.get(URL);
		assertTrue(driver.findElement(By.xpath("//mat-label[text()=' First Name']/following-sibling::mat-form-field"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//mat-label[text()=' Last Name']/following-sibling::mat-form-field"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.xpath("//mat-label[text()='Email address']/following-sibling::mat-form-field/div"))
				.isDisplayed());
		sleep(5000);
		WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
		String actualEmail = element.getAttribute("value");
		assertEquals(element.getAttribute("readonly"), "true");
		assertEquals(actualEmail,email );
		assertTrue(driver.findElement(By.xpath("//mat-label[text()=' Password']/following-sibling::mat-form-field"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.xpath("//mat-label[text()=' Confirm password']/following-sibling::mat-form-field"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//input[@type='checkbox']")).isEnabled());
		assertTrue(driver.findElement(By.xpath("//span[text()='Create my account']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[text()='Sign in']")).isDisplayed());
	}

	@Test
	public void test_03SignInPage() {
		extentTest = extentReports.createTest("Sign In Page");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Sign in']")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='email']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='password']")).isDisplayed());
		assertTrue(
				driver.findElement(By.xpath("//span[text()='Sign In Now']")).isDisplayed());
	}

	@Test
	public void test_04ValidationChecks() {
		extentTest = extentReports.createTest("Validation Checks");
		driver.navigate().back();
		assertEquals(
				driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("aria-required"),
				"true");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).getAttribute("aria-required"),
				"true");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='password']")).getAttribute("aria-required"),
				"true");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']"))
				.getAttribute("aria-required"), "true");
		// assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("aria-required"),
		// "true");
		for (int i = 0; i < 11; i++) {
			driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("ANTLLPTECH");
			driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("ANTLLPTECH" + Keys.TAB);
		}
		assertTrue(driver.findElement(By.xpath("//mat-error[1]")).isDisplayed(),"Character Lenth Rule Volatiled");
		assertEquals(driver.findElement(By.xpath("//mat-error[1]")).getText(), "Please enter a valid first name");
		assertTrue(driver.findElement(By.xpath("//mat-error[2]")).isDisplayed(),"Character Lenth Rule Volatiled");
		assertEquals(driver.findElement(By.xpath("//mat-error[2]")).getText(), "Please enter a valid last name");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Testing");
		assertTrue(driver.findElement(By.xpath("//mat-error[3]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//mat-error[3]")).getText(),
				"Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, "
						+ "one number, and one special character.");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Ant@123456");
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys("Testing"+Keys.TAB);
		assertTrue(driver.findElement(By.xpath("//mat-error[4]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//mat-error[4]")).getText(), "Passwords do not match. Please try again.");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Create my account']")));
		assertTrue(driver.findElement(By.xpath("//mat-error[5]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//mat-error[5]")).getText(), "You must agree to the terms and conditions to continue.");
	}
	
	@Test
	public void test_05AccountSetupSuccessfulPage() {
		extentTest = extentReports.createTest("Account Setup Successful Page");
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys("Testing@123"+Keys.TAB);
		sleep(1000L);
		driver.findElement(By.tagName("mat-checkbox")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Create my account']")));
		String style = driver.findElement(By.xpath("//div[contains(@class,'mdc-linear-progress__primary-bar')]")).getAttribute("style");
		boolean progress = style.contains("0.5");
		assertTrue(progress);
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='companyname']")).isDisplayed(),"Company Name");
		assertTrue(driver.findElement(By.xpath("//div[@class='company-location']")).isDisplayed(),"Company Location");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='address']")).isDisplayed(),"Company Address");
		assertTrue(driver.findElement(By.xpath("//mat-label[text()='Zip or postcode']/following-sibling::mat-form-field")).isDisplayed(),"Company Zipcode");
		assertTrue(driver.findElement(By.xpath("//mat-label[text()='Country']/following-sibling::mat-form-field")).isDisplayed(),"Company Country");
		assertTrue(driver.findElement(By.xpath("//span[text()='Confirm location & address ']")).isDisplayed(),"Confirm location buttom");
	}
	
	
	@Test
	public void test_06ValidationChecks2() {
		extentTest = extentReports.createTest("Validation Checks 2");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='companyname']")).getAttribute("aria-required"),"true");
		assertEquals(driver.findElement(By.xpath("//mat-label[text()='Company location']/span[@class='required-span']")).isDisplayed(),true);
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='address']")).getAttribute("aria-required"),"true");
		String expectedName = email.split("@")[0];
		WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='companyname']"));
		String actualName = element.getAttribute("value");
		assertEquals(actualName,expectedName);
		assertEquals(element.getAttribute("readonly"), "true");
		driver.findElement(By.xpath("//div[@class='company-location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter a location']")).sendKeys(new CharSequence[] {"ANTLLP"} );
		sleep(2000L);
		driver.findElement(By.xpath("//input[@placeholder='Enter a location']")).sendKeys(new CharSequence[] { Keys.ARROW_DOWN , Keys.ENTER} );
		element = driver.findElement(By.xpath("//input[@formcontrolname='zipcode']"));
//		boolean status = element.getAttribute("class").contains("readonly");
		assertEquals(element.getAttribute("readonly"),"true");
		element = driver.findElement(By.xpath("//input[@formcontrolname='country']"));
//		status = element.getAttribute("class").contains("readonly");
		assertEquals(element.getAttribute("readonly"),"true");
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].value='400709';", 
//				driver.findElement(By.xpath("//mat-label[text()='Zip or postcode']/following-sibling::mat-form-field/div")));
		element = driver.findElement(By.xpath("//input[@formcontrolname='zipcode']"));
		actualName = element.getAttribute("value");
		assertEquals(actualName,"400710");
		element = driver.findElement(By.xpath("//input[@formcontrolname='country']"));
		actualName = element.getAttribute("value");
		assertEquals(actualName,"India");
	}
	
	
//	@Test
	public void test_07GoogleMapsPage() {
		extentTest = extentReports.createTest("Google Maps Page");
		driver.findElement(By.xpath("//div[@class='company-location']")).click();
		assertTrue(driver.findElement(By.xpath("//div[@aria-label='Map']")).isDisplayed(),"Open Google Map");
		String actualString = driver.findElement(By.xpath("//div[@aria-label='Map']/div")).getAttribute("style");
		System.out.println(actualString);
		WebElement element = driver.findElement(By.xpath("//div[@aria-label='Map']/div/div"));
		Actions action = new Actions(driver);
		action.doubleClick(element).moveByOffset(-1000,0).build().perform();
		String expectedString = driver.findElement(By.xpath("//div[@aria-label='Map']/div")).getAttribute("style");
		System.out.println(actualString);
		assertEquals(actualString, expectedString);
	}
	
	@Test
	public void test_08WorkSectorPage() {
		extentTest = extentReports.createTest("Work Sector Page");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//button[@id='lba-arrow-right']"));
		js.executeScript("window.scrollTo(0, 600);");
//		Actions action = new Actions(driver);
//		action.moveToElement(element).click().build().perform();
		js.executeScript("arguments[0].click();", 
				element);
//		driver.findElement(By.xpath("//button[@id='lba-arrow-right']")).click();
		String style = driver.findElement(By.xpath("//div[contains(@class,'mdc-linear-progress__primary-bar')]")).getAttribute("style");
		boolean progress = style.contains("0.7");
		assertTrue(progress);
		assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='sector']")).isDisplayed(),"Select Sector");
		assertEquals(driver.findElement(By.xpath("//mat-select[@formcontrolname='sector']")).getAttribute("aria-required"),"true");
		assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='subSector']")).isDisplayed(),"Select Sub Sector");
		assertEquals(driver.findElement(By.xpath("//mat-select[@formcontrolname='subSector']")).getAttribute("aria-required"),"true");
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Next')]")).isDisplayed(),"Next Button");
		assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Next')]")).isEnabled(), false);
	}
	
	//@Test
	public void test_09WelcomeScreenPage() {
		extentTest = extentReports.createTest("Welcome Screen Page");
		driver.findElement(By.xpath("//mat-select[@formcontrolname='sector']")).sendKeys("IT");
		sleep(2000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='subSector']")).sendKeys("Computer");
		sleep(2000L);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		js.executeScript("arguments[0].click();",element);
		String status =driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		sleep(1000);
		assertEquals(status, "Success!");
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Welcome')]")).isDisplayed(),"Welcome Message");
	}
	
	//@Test
	public void test_10SMEDadhboardPage() {
		extentTest = extentReports.createTest("SME Dadhboard Page");
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).isDisplayed(), "Coutinue assessment button");
	}
	
	//@Test
	public void test_11BeginAssesmentPage() {
		extentTest = extentReports.createTest("Begin Assesment Page");
		driver.findElement(By.xpath("//span[text()=' Complete your assessment ']")).click();
		assertEquals(driver.findElement(By.xpath("//h1[text()='Assessment Period']")).isDisplayed(), "Assessment Period");
		assertEquals(driver.findElement(By.xpath("//input[@placeholder='Select Assessment Period']")), "Date Filled");
		
	}
	
}
