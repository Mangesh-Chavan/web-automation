package selenium.scripts.SME.Aggregator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class AggregatorToolAddandInviteSMEusers extends TestConfig{
		public static WebDriver driver;
		
		public static String email="SME8672.0@yopmail.com";
		
		public static String setEmail() {
			double Number = Math.floor(1000 + Math.random() * 9000);
			email = "SME"+ Number + "@yopmail.com";
			return email;
		}
		

		public String name="";

		private String smeName="";

		private String status="";
		
		JavascriptExecutor js; 
		
		
		@Test
		public void test_01AggregatorToolLandingPage() {
			extentTest = extentReports.createTest("Aggregator Tool Landing Page");
			driver = getDriver();
			driver.get("http://localhost:4200/aggregator/dashboard");
			driver.findElement(By.xpath("//input[@type='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Info@nimbustech.in");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("*#**372#Ha");
			WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Sign in now')]/preceding-sibling::span"));
			Actions a =new Actions(driver);
			a.click(element).build().perform();
			assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Success')]")).isDisplayed(),true );//Success message
			assertEquals(true, driver.findElement(By.xpath("//span[contains(text(),'Add new SME users')]")).isEnabled());//Add new SME users changes as per client requirement
		}
		
		@Test
		public void test_02AddanSMEuserPage() {
			extentTest = extentReports.createTest("Add an SME user Page");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='mdc-button__label' and contains(text(),'Add')]")));
			//driver.findElement(By.xpath("//span[text()='Add new SME User ']")).click();
			assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Add')]")).getText(),"Add SME user");
			assertEquals(driver.findElement(By.xpath("//mat-label[contains(text(),'Email address')]")).getText(),"Email address");
			assertEquals(driver.findElement(By.xpath("//mat-label[contains(text(),'Organization')]")).getText(),"Organization");
			//input[@formcontrolname='email']
			assertEquals(true, driver.findElement(By.xpath("//input[@formcontrolname='email']")).isEnabled());
			assertEquals(true, driver.findElement(By.xpath("//input[@formcontrolname='name']")).isEnabled());
		}
		
		@Test
		public void test_03ValidateEnteredDetails() {
			extentTest = extentReports.createTest("Validate Entered Details");
			assertEquals(true, driver.findElement(By.xpath("//input[@type='email']")).isEnabled());
			String state =driver.findElement(By.xpath("//input[@type='email']")).getAttribute("required");
			assertEquals("true", state);
			assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='language']")).isDisplayed());
			assertEquals(driver.findElement(By.xpath("//mat-select[@formcontrolname='language']")).getAttribute("aria-required"), "true");
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("12345"+Keys.TAB);
			for(int i=0;i<11;i++) {
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys("aaaaaaaaaa"+Keys.LEFT_SHIFT,Keys.TAB);
			}
			
			status = driver.findElement(By.xpath("//input[@type='email']")).getAttribute("aria-describedby");
			assertEquals(driver.findElement(By.xpath("(//mat-error[contains(@id,'"+status+"')])")).getText(), "Email is invalid");
			
//			status = driver.findElement(By.xpath("//input[@type='text']")).getAttribute("aria-describedby");
//			assertEquals(driver.findElement(By.xpath("(//mat-error[contains(@id,'"+status+"')])")).getText(),"Character Lengh Validation For Name");
			
			status = driver.findElement(By.xpath("//mat-select[@formcontrolname='language']")).getAttribute("aria-describedby");
			assertEquals(driver.findElement(By.xpath("(//mat-error[contains(@id,'"+status+"')])")).getText(), "Please select a preferred language for onboarding");
			
			
		}
		
		@Test
		public void test_04AddSMESuccessfulPage() throws InterruptedException {
			extentTest = extentReports.createTest("Add SME Successful Page");
			email=setEmail();
			System.out.println("email is "+email);
			driver.findElement(By.xpath("//input[@type='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
			sleep(10000);
			driver.findElement(By.xpath("//input[@type='text']")).clear();
			name=email.split("@")[0];
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name);
			driver.findElement(By.xpath("//mat-select[@formcontrolname='language']")).click();
			driver.findElement(By.xpath("//span[text()=' English ']")).click();
			driver.findElement(By.xpath("//span[text()='Add new SME user ']/parent::button//span[text()='Add new SME user ']/parent::button")).click();
			status =driver.findElement(By.xpath("//div[@id='toast-container']/div/div[contains(@class,'toast-title')]")).getText();
			sleep(1000);
			assertEquals(status, "Success!");
			
		}
		
		@Test
		public void test_05SMEDetailsTable() {
			extentTest = extentReports.createTest("SME Details Table");
			sleep(5000);
			smeName=driver.findElement(By.xpath("//tbody[@class='mdc-data-table__content']/tr[@role='row']/td[1]")).getText();
			status=driver.findElement(By.xpath("//tbody[@class='mdc-data-table__content']/tr[@role='row']/td[6]")).getText();
			System.out.println("...."+status);
			assertEquals(smeName, name+"\n"+email);
			assertEquals(status, "Invited");
			
		}
		
		//@Test
		public void test_06RequestToChangeBusinessName() {
			extentTest = extentReports.createTest("Request To Change Business Name");
			driver.findElement(By.xpath("//span[text()='Begin environmental assessment']")).click();
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			driver.findElement(By.xpath("//span[text()=' 1 ']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			driver.findElement(By.xpath("//input[@placeholder='Employee count']")).sendKeys("50");//1
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			driver.findElement(By.xpath("//mat-select[@formcontrolname='currency']")).click();
			js = (JavascriptExecutor) driver;
			js.executeScript("argument[0].click;", driver.findElement(By.xpath("//span[text()=' INR ']")));
			driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='revenueAmount']")).sendKeys("55000000");//2
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			try {
				driver.findElement(By.xpath("//mat-icon[text()='close']")).click();
			}
			catch(Exception e) {}
			//driver.findElement(By.xpath("//span[contains(text(),'No I don')]")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Yes I do')]")).click();
			//driver.findElement(By.xpath("//input[@placeholder='Emissions']")).sendKeys("2");//3
			driver.findElement(By.xpath("//input[@type='radio']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='energyProvider']")).sendKeys("2");//4
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='totalEmissionScope1']")).sendKeys("2");//5
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			
			driver.findElement(By.xpath("//button[contains(text(),'Got it!')]")).click();
			driver.findElement(By.xpath("//textarea[contains(@placeholder,'Write a reply…')]")).sendKeys("want to change my business name"+Keys.ENTER);
		}
		
		//@Test
		public void test_07AllowUpdateNameInDashboard() {
			extentTest = extentReports.createTest("Allow Update Name In Dashboard");
			assertEquals(true,driver.findElement(By.xpath("//div[contains(text(),'Action')]")).isDisplayed());
			
			
		}
		
		
		
		@Test
		public void test_08LogoutPage() {
			extentTest = extentReports.createTest("Logout Page");
			WebElement element = driver.findElement(By.xpath("//span[@class='mdc-button__label']/following::mat-icon[contains(text(),'down')]"));
			Actions a =new Actions(driver);
			a.click(element).build().perform();
			element = driver.findElement(By.xpath("//span[contains(text(),'Log Out')]"));
			a.click(element).build().perform();
		}
		
		
		
		
}
