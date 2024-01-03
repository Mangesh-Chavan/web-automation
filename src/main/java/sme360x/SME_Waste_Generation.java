package sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_Waste_Generation extends TestConfig{
	public static WebDriver driver;
	
	public static String email;
	
	JavascriptExecutor javascriptExecutor;
	
	int minutes;
	
	@Test
	public void test_01ValueExchangePage() {
		extentTest = extentReports.createTest("Value Exchange Page");
		LocalDateTime now = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("mm");
	    String formatedDateTime = now.format(format);
	    minutes = Integer.parseInt(formatedDateTime);
		driver = getDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		email =  AggregatorToolAddandInviteSMEusers.email;
				//"SME_0007@yopmail.com";
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Testing@123");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign In Now')]/preceding-sibling::span"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed(), "Dashboard Heading");
		assertTrue(driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).isDisplayed(), "Resume my assessment button");
		driver.findElement(By.xpath("//span[text()=' Resume my assessment ']")).click();
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
			driver.findElement(By.xpath("//div[@aria-label='Dismiss']")).click();
		}catch(NoSuchElementException e) {}
		if(!driver.findElement(By.xpath("//h1[text()='Waste Generation']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='6']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Waste Generation']/following::span[text()=' Back ']")).click();
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Waste represents')]")).isDisplayed(),"Waste represents Value Exchange Page");
	}
	
	@Test
	public void test_02WasteGenerationOverviewPage() {
		extentTest = extentReports.createTest("Waste Generation Overview Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[5]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_03ValidateOverviewofWasteGeneration() {
		extentTest = extentReports.createTest("Validate Overview of Waste Generation");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Hazardous waste generated & disposal method ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Hazardous waste')]")).isDisplayed(),"Tooltip 1");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()=' Non-hazardous waste generated & disposal method ']/mat-icon")));
		//driver.findElement(By.xpath("//li[text()=' Non-hazardous waste generated & disposal method ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Non-hazardous waste')]")).isDisplayed(),"Tooltip 2");
		driver.findElement(By.xpath("//h1[text()='Waste Generation']/following::span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Nature provides')]")).isDisplayed(),"Biodiversity & Ecosystem Value Exchange Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='6']")));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//h1[text()='Waste Generation']/following::span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'hazardous waste')]")).isDisplayed(),"Hazardous Waste Question");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='16']")).getText(), "16","Number Count 16");
	}
	
	@Test
	public void test_04ValidateHazardousWasteDisposalMethod() {
		extentTest = extentReports.createTest("Validate Freshwater Withdrawal Question");
		driver.findElement(By.xpath("//h2[contains(text(),'hazardous waste')]/following::mat-icon[contains(text(),'add')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'hazardous waste')]/following::mat-select[@formcontrolname='disposalMethod'][2]")));
		driver.findElement(By.xpath("//h2[contains(text(),'hazardous waste')]/following::mat-icon[contains(text(),'do_not_disturb')]")).click();
		
		sleep(1000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='disposalMethod']")).sendKeys("Open");
		sleep(1000L);
		driver.findElement(By.xpath("//input[@formcontrolname='annualQuantity']")).sendKeys("1000");
		sleep(1000L);
		driver.findElement(By.xpath("//input[@formcontrolname='annualQuantity']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='17']")).getText(), "17","Number Count 17");
		assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-select[@formcontrolname='disposalMethod']")).isDisplayed(),"Non-hazardous Waste Question.");
		
		driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::span[text()=' Back ']")).click();
		driver.findElement(By.xpath("//h2[contains(text(),'hazardous waste')]/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='annualQuantity']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='17']")).getText(), "17","Number Count 17");
		assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-select[@formcontrolname='disposalMethod']")).isDisplayed(),"Non-hazardous Waste Question.");

	}
	
	@Test
	public void test_06ValidateNonhazardousWasteDisposalMethod() {
		extentTest = extentReports.createTest("Validate Total Phosphorous Question");
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-icon[contains(text(),'add')]")).click();
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-select[@formcontrolname='disposalMethod'][2]")));
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-icon[contains(text(),'do_not_disturb')]")).click();
			
			sleep(1000L);
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::mat-select[@formcontrolname='disposalMethod']")).sendKeys("Recycling");
			sleep(1000L);
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::input[@formcontrolname='annualQuantity']")).sendKeys("1000");
			sleep(1000L);
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::input[@formcontrolname='annualQuantity']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Nature provides')]")).isDisplayed(),"Biodiversity & Ecosystem Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[5]")).isDisplayed(),"Progress Bar 5 Validation");
			
			
        }
		else
		{
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::input[@formcontrolname='annualQuantity']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Nature provides')]")).isDisplayed(),"Biodiversity & Ecosystem Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[6]")).isDisplayed(),"Progress Bar 6 Validation");
		}

	}


}
