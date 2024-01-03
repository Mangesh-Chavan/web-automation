package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_Compoff extends TestConfig{
	
	String compDate="";
	
	LocalDate date;
	
	DateTimeFormatter formatter;
	
	JavascriptExecutor js;
	
	public LocalDate date() {
		date = LocalDate.now();//.minusDays(20);
		String weekDay = (date.getDayOfWeek()).toString();
		while(!(weekDay.equals("SUNDAY")))
		{
			date = date.minusDays(1);	
			weekDay = (date.getDayOfWeek()).toString();
		}
		return date;
		
	}
	
	//@Test(priority = 1)
	public void Login() {
		driver.get("https://hrmsxapi.antllp.com/Account/Login");
		try {
		driver.findElement(By.xpath("//button[contains(text(),'Advanced')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'hrmsxapi')]")).click();
		}catch(Exception e) {}
		LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("mm");
        String formatedDateTime = now.format(format);
        int minutes = Integer.parseInt(formatedDateTime);
        System.out.println(formatedDateTime);
        
        if(minutes>00 && minutes<15 || minutes > 30 && minutes<45) {
		driver.findElement(By.id("LoginInput_UserNameOrEmailAddress")).sendKeys("NewApprover");
		driver.findElement(By.id("LoginInput_Password")).sendKeys("Manager@1234");
		driver.findElement(By.name("Action")).click();
		driver.get("http://localhost:4200/login");
		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("NewApprover");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Manager@1234");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		}else {
        	driver.findElement(By.id("LoginInput_UserNameOrEmailAddress")).sendKeys("admin");
    		driver.findElement(By.id("LoginInput_Password")).sendKeys("1q2w3E*");
    		driver.findElement(By.name("Action")).click();
    		driver.get("http://localhost:4200/login");
    		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
    		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("niesh");
    		driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
    		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
    		driver.findElement(By.xpath("//button[text()='Log In']")).click();
        }
		assertTrue(driver.findElement(By.xpath("//span[text()='LMS']")).isDisplayed(),"LMS Tab should be visible");
	}
	
	@Test
	public void TEST01_LMSPage() {
		driver.findElement(By.xpath("//span[text()='LMS']")).click();
		//assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Comp Off Request')]")).isDisplayed(),"Comp Off Request should be visible");
	}
	
	//@Test(priority = 3)
	public void Cancel_Compoff() {
		if(Login_Page.isManager) {
			
		}
	}
	
	@Test
	public void TEST02_RequestCompoffPage() {
		if(Login_Page.isEmployee) {
			extentTest = extentReports.createTest("Request Compoff Page");
			String records = date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		driver.findElement(By.xpath("//button[contains(text(),'Comp Off Request')]")).click();
		compDate = date().format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();;
		System.out.println(compDate);
		driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while(!driver.getPageSource().contains(compDate)) {
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
		}
		driver.findElement(By.xpath("//span[text()=' "+date.getDayOfMonth()+" ']")).click();
		driver.findElement(By.xpath("//mat-select[@formcontrolname='reason']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'weekend')]")).click();
		driver.findElement(By.xpath("//span[text()='Apply ']")).click();
		
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "CompOff request send successfully.","Success message should be displayed");
		List<WebElement> elements = driver.findElements(By.xpath("//td[text()='CompOff']/following-sibling::td"));
		String expectedResult = records;
		String actualResult="";
		for(WebElement e:elements)
		{
			if(e.getText().contains(records)) {
				actualResult=e.getText();
				break;
			}
		}
		assertEquals(actualResult,expectedResult,"newly added record should be displyed");
		}
	}
	
	@Test
	public void TEST02_AssignCompoffPage() {
		if(Login_Page.isManager) {
			extentTest = extentReports.createTest("Assign Compoff Page");
			js = (JavascriptExecutor) driver;
			driver.findElement(By.xpath("//button[contains(text(),'Assign Comp Off')]")).click();
			driver.findElement(By.xpath("//mat-select[@formcontrolname='userId']")).click();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()=' 10002 - Doctor Strange ']")));
			//driver.findElement(By.xpath("//mat-option/span[contains(text(),'Supriya')]")).click();
			compDate = date().format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
			System.out.println(compDate);
			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
			while(!driver.getPageSource().contains(compDate)) {
				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			}
			driver.findElement(By.xpath("//span[text()=' "+date.getDayOfMonth()+" ']")).click();
			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Testing");
			driver.findElement(By.xpath("//span[text()='Assign']")).click();
			sleep(500);
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "This User Has Already Requested For Comp Off. Please Approve Or Reject From The Table Below !","Error message should be displayed");
		}
	}
	
	
	@Test
	public void TEST03_CancelCompoffRequest() {
		if(Login_Page.isManager) {
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			driver.findElement(By.xpath("//span[text()='All']")).click();
			driver.findElement(By.xpath("//span[text()='Pending']")).click();
			compDate = date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()=' "+compDate+"']/following-sibling::td/button[2]")));
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//td[text()=' "+compDate+"']/following-sibling::td/button[2]")));
			assertEquals(driver.findElement(By.xpath("//td[text()=' "+compDate+"']/following-sibling::td/span")).getText(), "Rejected","Reject text should be displayed");
		}
	}
		
	@Test
	public void TEST04_Assign_Employee_Compff() {
		if(Login_Page.isManager) {
//			driver.findElement(By.xpath("//mat-select[@formcontrolname='userId']")).click();
//			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()=' 10002 - Doctor Strange ']")));
//			//driver.findElement(By.xpath("//mat-option/span[contains(text(),'Supriya')]")).click();
//			compDate = date().format(DateTimeFormatter.ofPattern("MMM yyyy")).toUpperCase();
//			System.out.println(compDate);
//			driver.findElement(By.xpath("//button[@aria-label='Open calendar']")).click();
//			while(!driver.getPageSource().contains(compDate)) {
//				driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
//			}
//			driver.findElement(By.xpath("//span[text()=' "+date.getDayOfMonth()+" ']")).click();
//			driver.findElement(By.xpath("//textarea[@formcontrolname='reason']")).sendKeys("Testing");
			driver.findElement(By.xpath("//span[text()='Assign']")).click();
			sleep(500);
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "CompOff assigned successfully.","Success message should be displayed");
		}
	}
	
}
