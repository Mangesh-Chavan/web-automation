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

public class SME_Air_Pollution extends TestConfig{
	
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
		if(!driver.findElement(By.xpath("//h1[text()='Air Pollution']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='3']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Air Pollution']/following::span[text()=' Back ']")).click();
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Air pollution')]")).isDisplayed(),"Air Value Exchange Page");
	}
	
	@Test
	public void test_02AirPollutionOverviewPage() {
		extentTest = extentReports.createTest("Air Pollution Overview Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[2]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_03ValidateOverviewofAirPollution() {
		extentTest = extentReports.createTest("Validate Overview of Air Pollution");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Particulate Matter emissions ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Particulate Matter includes')]")).isDisplayed(),"Tooltip 1");
		driver.findElement(By.xpath("//li[text()=' SOx emissions ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'SOx emissions')]")).isDisplayed(),"Tooltip 2");
		driver.findElement(By.xpath("//li[text()=' NOx emissions ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'NOx emissions')]")).isDisplayed(),"Tooltip 3");
		driver.findElement(By.xpath("//h1[text()='Air Pollution']/following::span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Water consumption')]")).isDisplayed(),"Water Value Exchange Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='3']")));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//h1[text()='Air Pollution']/following::span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']")).isDisplayed(),"PM Emissions Question");
	}
	
	@Test
	public void test_04ValidatePMEmissionsQuestion() {
		extentTest = extentReports.createTest("Validate PM Emissions Question");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='8']")).getText(), "8","Number Count 8");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']")).getAttribute("type"), "number","PM Emissions feild should be number");
		driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='9']")).getText(), "9","Number Count 9");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']")).isDisplayed(),"SOx Emissions question");
		driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='particularMatter']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='9']")).getText(), "9","Number Count 9");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']")).isDisplayed(),"SOx Emissions question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']")).getAttribute("type"), "number","SOx Emissions should be number");

	}
	
	@Test
	public void test_05ValidateSOxEmissionsQuestion() {
		extentTest = extentReports.createTest("Validate SOx Emissions Question");
		driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='10']")).getText(), "10","Number Count 10");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']")).isDisplayed(),"NOx Emissions Question");
		driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='soxEmissions']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='10']")).getText(), "10","Number Count 10");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']")).isDisplayed(),"NOx Emissions Question");
		
	}
	
	@Test
	public void test_06ValidateNoxEmissionsQuestion() {
		extentTest = extentReports.createTest("Validate Nox Emissions Question");
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']")).sendKeys("200");
			driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Water consumption')]")).isDisplayed(),"Water Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[3]")).isDisplayed(),"Progress Bar 3 Validation");
        }
		else
		{
			driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Water consumption')]")).isDisplayed(),"Water Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[3]")).isDisplayed(),"Progress Bar 3 Validation");
		}
		
	}

}
