package selenium.scripts.sme360x;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.SME.Aggregator.AggregatorToolAddandInviteSMEusers;

public class SME_GHG_Emissions extends TestConfig{
	
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
		}catch(Exception e) {}
		if(!driver.findElement(By.xpath("//h1[text()='Greenhouse Gas Emissions']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='2']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Greenhouse Gas Emissions']/following::span[text()='Back ']")).click();
		
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Greenhouse Gas (GHG) emissions')]")).isDisplayed(),"GHG Value Exchange Page");

		
	}
	
	@Test
	public void test_02GHGOverviewPage() {
		extentTest = extentReports.createTest("GHG Over view Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("//h1[text()='Greenhouse Gas Emissions']")).isDisplayed(),"Greenhouse Gas Emissions Headline");

	}
	
	@Test
	public void test_03ValidateAskSection() {
		extentTest = extentReports.createTest("Validate Ask Section");
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Scope 2 emissions ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Scope 2 covers')]")).isDisplayed(),"Tooltip 1");
		driver.findElement(By.xpath("//li[text()=' Scope 1 emissions ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Scope 1 covers')]")).isDisplayed(),"Tooltip 2");
		driver.findElement(By.xpath("//li[text()=' Carbon offsets ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Scope 1 or Scope 2')]")).isDisplayed(),"Tooltip 3");
		driver.findElement(By.xpath("//span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Air pollution')]")).isDisplayed(),"Air Value Exchange Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='2']")));
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope2']")).isDisplayed(),"Scope 2 Emissions question");
	}
	
	@Test
	public void test_04ValidateScope2EmissionsQuestion() {
		extentTest = extentReports.createTest("Validate Scope 2 Emissions Question");
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='3']")).getText(), "3","Number Count 3");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope2']")).getAttribute("type"), "number","Scope 2 Emissions feild should be number");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope2']")).sendKeys("200");
		driver.findElement(By.xpath("//span[text()=' Next question ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='5']")).getText(), "5","Number Count 5");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).isDisplayed(),"Scope 1 Emissions question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@type='radio']")).click();
		driver.findElement(By.xpath("//span[text()=' Next question ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='4']")).getText(), "4","Number Count 4");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']")).isDisplayed(),"Electricity Consumtion question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']")).getAttribute("type"), "number","Electricity Consumtion should be number");
		

	}
	
	
	@Test
	public void test_05ValidateElectricityConsumtionQuestion() {
		extentTest = extentReports.createTest("Validate Electricity Consumtion Question");
		driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']")).sendKeys("201");
		driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']/following::span[text()='Next question ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='5']")).getText(), "5","Number Count 5");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).isDisplayed(),"Scope 1 Emissions question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).getAttribute("type"), "number","Scope 1 Emissions feild should be number");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']/following::span[text()='Next question ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='5']")).getText(), "5","Number Count 5");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).isDisplayed(),"Scope 1 Emissions question");
		
	}
	
	@Test
	public void test_06ValidateScope1EmissionsQuestion() {
		extentTest = extentReports.createTest("Validate Scope 1 Emissions Question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='7']")).getText(), "7","Number Count 7");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']")).isDisplayed(),"Avoided Emissions question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']//b[text()='6']")).getText(), "6","Number Count 6");
		assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='fossilFuel']")).isDisplayed(),"Select Fossil Fuels Question");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).isDisplayed(),"Annual quantity Question");
		assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='tonnes']")).isDisplayed(),"Unit Question");
		
	}
	
	
	@Test
	public void test_07ValidateFuelUsageQuestion() {
		extentTest = extentReports.createTest("Validate Fuel Usage Question");
		driver.findElement(By.xpath("//mat-select[@formcontrolname='fossilFuel']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='fossilFuel']/following::span[text()=' Next question ']")).click();
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='7']")).getText(), "7","Number Count 7");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']")).isDisplayed(),"Avoided Emissions question");
		driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//mat-icon[contains(text(),'add')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//mat-select[@formcontrolname='fossilFuel'])[2]")));
		driver.findElement(By.xpath("//mat-icon[contains(text(),'do_not_disturb')]")).click();
		
		sleep(1000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='fossilFuel']")).sendKeys("Natural Gas");
		sleep(1000L);
		driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).sendKeys("600");
		sleep(1000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='tonnes']")).sendKeys("m");
		sleep(1000L);
		driver.findElement(By.xpath("//mat-select[@formcontrolname='fossilFuel']/following::span[text()=' Next question ']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()=' Question ']/b[text()='7']")).getText(), "7","Number Count 7");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']")).isDisplayed(),"Avoided Emissions question");
		
	}
	
	@Test
	public void test_08ValidateAvoidedEmissionsQuestion() {
		extentTest = extentReports.createTest("Validate Avoided Emissions Question");
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']")).sendKeys("200");
			driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Air pollution')]")).isDisplayed(),"Air Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[2]")).isDisplayed(),"Progress Bar 2 Validation");
        }
		else
		{
			driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionEquivalent']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Air pollution')]")).isDisplayed(),"Air Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[2]")).isDisplayed(),"Progress Bar 2 Validation");
		}
		
	}	

}
