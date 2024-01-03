package selenium.scripts.SME.Aggregator;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

//@FixMethodOrder
public class LandingPageDashboardhome extends TestConfig {
	public static WebDriver driver;
	public static boolean condition = false;
	
	@Test
	public void test01_AggregatorDashboardPage() {
		extentTest = extentReports.createTest("Aggregator Dashboard Page");
		driver = getDriver();
		driver.get("http://localhost:4200/aggregator/dashboard");
		LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("mm");
        String formatedDateTime = now.format(format);
        int minutes = Integer.parseInt(formatedDateTime);
        System.out.println(formatedDateTime);
        if(minutes>00 && minutes<15 || minutes > 30 && minutes<45)
        {
			driver.findElement(By.xpath("//input[@type='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Info@nimbustech.in");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Ha");
			condition = true;
        }else {
        	driver.findElement(By.xpath("//input[@type='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("agg3141@yopmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Ant@123456");
        }
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
//		Actions a =new Actions(driver);
//		a.click(element).build().perform();
		sleep(1000);
		String status = driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
		assertEquals(status, "Success!");//Success message
//		assertEquals(true, driver.findElement(By.xpath("//h1[text()='SME Overview']")).isDisplayed());//SME Overview label
	}

	
	@Test
	public void test02_AggregatorSMEParticipationTable() {
		extentTest = extentReports.createTest("Aggregator SME Participation Table");
		List<WebElement> elements = driver.findElements(By.xpath("//tbody[@class='mdc-data-table__content']/tr[@role='row']"));
		int size = elements.size();
		System.out.println(size);
		assertEquals(true, size == 50 );
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Company ']")).isDisplayed());//Company Column
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Sector ']")).isDisplayed());//Sector Column
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Country ']")).isDisplayed());//Country Column
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Headcount ']")).isDisplayed());//Headcount Column
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Revenue ']")).isDisplayed());//Revenue Column
		assertEquals(true, driver.findElement(By.xpath("//div[text()='Status']")).isDisplayed());//Status Column
		//sort button enebled
		JavascriptExecutor jExecutor= (JavascriptExecutor) driver;
		jExecutor.executeScript("arguments[0].scrollBy(0, 681);", driver.findElement(By.xpath("//div[text()=' Company ']")));
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Company ']/parent::div/parent::th[@aria-sort='none']")).isDisplayed());//default sort last added first
		driver.findElement(By.xpath("//div[text()=' Company ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Company ']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
		driver.findElement(By.xpath("//div[text()=' Company ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Company ']/parent::div/parent::th[@aria-sort='descending']")).isDisplayed());//descending order z to a
		driver.findElement(By.xpath("//div[text()=' Sector ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Sector ']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
		driver.findElement(By.xpath("//div[text()=' Country ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Country ']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
		driver.findElement(By.xpath("//div[text()=' Headcount ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Headcount ']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
		driver.findElement(By.xpath("//div[text()=' Revenue ']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()=' Revenue ']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
		driver.findElement(By.xpath("//div[text()='Status']")).click();
		assertEquals(true, driver.findElement(By.xpath("//div[text()='Status']/parent::div/parent::th[@aria-sort='ascending']")).isDisplayed());//ascending order a to z
	}
	
	@Test
	public void test03_AggregatorAdminPage() {
		if(condition) {
			extentTest = extentReports.createTest("Aggregator Admin Page");
			assertEquals(true, driver.findElement(By.xpath("//h1[text()='SME Overview']")).isDisplayed());//SME Overview label
			assertEquals(true, driver.findElement(By.xpath("//h1[text()='SME Overview']/parent::div/parent::div/following-sibling::div[@class='row']")).isDisplayed());//SME Overview Tabel
			assertEquals(true, driver.findElement(By.xpath("//table[contains(@class,'aggregator-sme-table')]")).isDisplayed());//SME Participation Tabel
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'Add new SME User')]")).isDisplayed());//Add new SME User
			WebElement element = driver.findElement(By.xpath("//span[text()='Harshad Satarkar']/preceding-sibling::span"));
			Actions a =new Actions(driver);
			a.click(element).build().perform();
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' SME Management ')]")).isDisplayed());//SME Management label
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Team Management ')]")).isDisplayed());//Team Management label
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Profile Management ')]")).isDisplayed());//Profile Management label
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Log Out ')]")).isDisplayed());//Log Out label
			element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
	}
	
	
	@Test
	public void test04_AggregatorViewerPage() {
		if(!condition) {
			extentTest = extentReports.createTest("Aggregator Viewer Page");
			assertEquals(true, driver.findElement(By.xpath("//table[contains(@class,'aggregator-sme-table')]")).isDisplayed());//SME Participation Tabel
			WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/preceding-sibling::span"));
			Actions a =new Actions(driver);
			a.click(element).build().perform();
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Profile Management ')]")).isDisplayed());//Profile Management label
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),' Log Out ')]")).isDisplayed());//Log Out label
			a.click(element).build().perform();
		}
	}
	
	@Test
	public void test05_LogoutPage() {
		extentTest = extentReports.createTest("Logout Page");
		WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/following::mat-icon[contains(text(),'down')]"));
		Actions a =new Actions(driver);
		a.click(element).build().perform();
		element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
		a.click(element).build().perform();
	}
}
