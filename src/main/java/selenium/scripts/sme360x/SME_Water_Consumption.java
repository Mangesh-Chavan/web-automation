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

public class SME_Water_Consumption extends TestConfig{
	
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
		if(!driver.findElement(By.xpath("//h1[text()='Water Consumption']")).isDisplayed()) {
			driver.findElement(By.xpath("//h3[text()='4']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		}
		driver.findElement(By.xpath("//h1[text()='Water Consumption']/following::span[text()=' Back ']")).click();
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='noxEmissions']/following::span[text()='Next question']")));
		sleep(1000L);
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Water consumption')]")).isDisplayed(),"Water Value Exchange Page");
	}
	
	@Test
	public void test_02WaterPollutionOverviewPage() {
		extentTest = extentReports.createTest("Water Pollution Overview Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[3]")).isDisplayed(),"Progress Bar Validation");
	}
	
	@Test
	public void test_03ValidateOverviewofWaterPollution() {
		extentTest = extentReports.createTest("Validate Overview of Water Pollution");
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//li[text()=' Total freshwater withdrawal ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Total freshwater withdrawal')]")).isDisplayed(),"Tooltip 1");
		driver.findElement(By.xpath("//li[text()=' Total recycled water ']/mat-icon")).click();
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Total recycled water')]")).isDisplayed(),"Tooltip 2");
		driver.findElement(By.xpath("//h1[text()='Water Consumption']/following::span[contains(text(),'skip this section')]")).click();
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Wastewater discharge')]")).isDisplayed(),"Wastewater discharge Value Exchange Page");
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
		javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='4']")));
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		driver.findElement(By.xpath("//h1[text()='Water Consumption']/following::span[contains(text(),'continue')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']")).isDisplayed(),"Freshwater withdrawal Question");
	}
	
	@Test
	public void test_04ValidateFreshwaterWithdrawalQuestion() {
		extentTest = extentReports.createTest("Validate Freshwater Withdrawal Question");
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='11']")).getText(), "11","Number Count 11");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']")).getAttribute("type"), "number","Freshwater withdrawal Question should be number");
		driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']")).sendKeys("200");
		driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='12']")).getText(), "12","Number Count 12");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']")).isDisplayed(),"Recycled Water Usage Question");
		driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']/following::span[text()=' Back ']")).click();
		
		driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']/following::input[@type='radio']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='freshwaterWithdrawal']/following::span[text()='Next question']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Please')]")));
		assertEquals(driver.findElement(By.xpath("//span[text()='Question ']/b[text()='12']")).getText(), "12","Number Count 12");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']")).isDisplayed(),"Recycled Water Usage Question");
		assertEquals(driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']")).getAttribute("type"), "number","Recycled Water Usage Question feild should be number");

	}
	
	
	@Test
	public void test_05ValidateRecycledWaterUsageQuestion() {
		extentTest = extentReports.createTest("Validate Recycled Water Usage Question");
		if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']")).sendKeys("200");
			driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Wastewater discharge')]")).isDisplayed(),"Wastewater discharge Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[4]")).isDisplayed(),"Progress Bar 4 Validation");
			
        }
		else
		{
			driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']/following::input[@type='radio']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='recycledWater']/following::span[text()='Next question']")).click();
			assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Wastewater discharge')]")).isDisplayed(),"Wastewater discharge Value Exchange Page");
			javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-icon[text()='close']/preceding-sibling::span")));
			assertTrue(driver.findElement(By.xpath("(//li[contains(@class,'completed')])[4]")).isDisplayed(),"Progress Bar 4 Validation");
			
		}
	}

}
