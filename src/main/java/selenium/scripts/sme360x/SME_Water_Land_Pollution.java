package selenium.scripts.sme360x;

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

public class SME_Water_Land_Pollution extends TestConfig{
	
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
		if(!driver.findElement(By.xpath("//h1[text()='Water & Land Pollution']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='5']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Water & Land Pollution']/following::span[text()=' Back ']")).click();
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Wastewater discharge')]")).isDisplayed(),"Wastewater discharge Value Exchange Page");
	}
	
	@Test
	public void test_02WaterPollutionOverviewPage() {
		extentTest = extentReports.createTest("Water Pollution Overview Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[4]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_03ValidateOverviewofWaterPollution() {
		extentTest = extentReports.createTest("Validate Overview of Water Pollution");
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Quantity of Chemical Oxygen Demand ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Chemical Oxygen Demand')]")).isDisplayed(),"Tooltip 1");
		driver.findElement(By.xpath("//li[text()=' Quantity of Total Nitrogen ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Nitrogen concentration')]")).isDisplayed(),"Tooltip 2");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()=' Quantity of Total Phosphorus ']/mat-icon")));
		//driver.findElement(By.xpath("//li[text()=' Quantity of Total Phosphorus ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Phosphorus concentration')]")).isDisplayed(),"Tooltip 3");
		driver.findElement(By.xpath("//h1[text()='Water & Land Pollution']/following::span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Waste represents')]")).isDisplayed(),"Waste represents Value Exchange Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='5']")));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//h1[text()='Water & Land Pollution']/following::span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']")).isDisplayed(),"Chemical Oxygen Demand Question");
	}
	
	@Test
	public void test_04ValidateFreshwaterWithdrawalQuestion() {
		extentTest = extentReports.createTest("Validate Freshwater Withdrawal Question");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='13']")).getText(), "13","Number Count 13");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']")).getAttribute("type"), "number","Chemical Oxygen Demand Question should be number");
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='14']")).getText(), "14","Number Count 14");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']")).isDisplayed(),"Total Nitrogen Question");
		
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']/following::span[text()=' Back ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='totalOxygen']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='14']")).getText(), "14","Number Count 14");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']")).isDisplayed(),"Total Nitrogen Question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']")).getAttribute("type"), "number","Total Nitrogen Question feild should be number");

	}
	
	@Test
	public void test_05ValidateTotalNitrogenQuestion() {
		extentTest = extentReports.createTest("Validate Total Nitrogen Question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='15']")).getText(), "15","Number Count 15");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']")).isDisplayed(),"Total Nitrogen Question");
		
		driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']/following::span[text()=' Back ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='totalNitrogen']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='15']")).getText(), "15","Number Count 15");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']")).isDisplayed(),"Total Phosphorous Question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']")).getAttribute("type"), "number","Total Phosphorous Question feild should be number");

	}
	
	@Test
	public void test_06ValidateTotalPhosphorousQuestion() {
		extentTest = extentReports.createTest("Validate Total Phosphorous Question");
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']")).sendKeys("200");
			driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Waste represents')]")).isDisplayed(),"Waste represents Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[5]")).isDisplayed(),"Progress Bar 5 Validation");
        }
		else
		{
			driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='totalPhosphorous']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Waste represents')]")).isDisplayed(),"Waste represents Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[5]")).isDisplayed(),"Progress Bar 5 Validation");
		}

	}

}
