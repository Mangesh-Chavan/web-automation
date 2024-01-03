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

public class SME_Biodiversity_Ecosystem extends TestConfig{
	
	public static WebDriver driver;
	
	public static String email;
	
	JavascriptExecutor javascriptExecutor;
	
	int minutes;

	boolean ansewered;
	
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
		if(!driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='7']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']/following::span[text()=' Back ']")).click();
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h2[contains(text(),'non-hazardous waste')]/following::input[@formcontrolname='annualQuantity']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Nature provides')]")).isDisplayed(),"Biodiversity & Ecosystem Value Exchange Page");
	}
	
	@Test
	public void test_02BiodiversityEcosystemOverviewPage() {
		extentTest = extentReports.createTest("Biodiversity Ecosystem Overview Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[6]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_03ValidateOverviewofBiodiversityEcosystem() {
		extentTest = extentReports.createTest("Validate Overview of Biodiversity Ecosystem");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Area of land occupied ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'permanent land')]")).isDisplayed(),"Tooltip 1");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()=' Location of land occupied ']/mat-icon")));
		//driver.findElement(By.xpath("//li[text()=' Location of land occupied ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'land occupation')]")).isDisplayed(),"Tooltip 2");
		driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']/following::span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//h1[text()='Review my answers']")).isDisplayed(),"Review My Answers");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='7']")));
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']/following::span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'area of land occupied')]")).isDisplayed(),"Hazardous Waste Question");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='18']")).getText(), "18","Number Count 18");
	}
	
	@Test
	public void test_04ValidateLandTransformationQuestion() {
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
		extentTest = extentReports.createTest("Validate Land Transformation Question");
		driver.findElement(By.xpath("//input[@formcontrolname='areaOfLand']")).sendKeys("1000");
		driver.findElement(By.xpath("//input[@formcontrolname='areaOfLand']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//h1[text()='Biodiversity & Ecosystem Services']/following::b[text()='19'][2]")).getText(), "19","Number Count 19");
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'location')]")).isDisplayed(),"Location Question");
		ansewered=true;
        }else {
		driver.findElement(By.xpath("//input[@formcontrolname='areaOfLand']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='areaOfLand']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//h1[text()='Review my answers']")).isDisplayed(),"Review My Answers");
		ansewered=false;
        }
	}
	
	@Test
	public void test_05ValidateExactLocationQuestion() {
		extentTest = extentReports.createTest("Validate Exact Location Question");
		if(ansewered) {
			driver.findElement(By.xpath("//div[contains(text(),'location')]/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//div[contains(text(),'location')]/following::span[text()='Review my answers']")).click();
			assertTrue(driver.findElement(By.xpath("//h1[text()='Review my answers']")).isDisplayed(),"Review My Answers");
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[7]")).isDisplayed(),"Progress Bar 7 Validation");
		}

	}
	
	

}
