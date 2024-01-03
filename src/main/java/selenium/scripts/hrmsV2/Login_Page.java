package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;


public class Login_Page extends TestConfig{
	
	int minutes = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm")));
    
	static boolean isManager, isEmployee, isAdmin, isHR;
	
	@Test
	public void TEST01_LoginPage() {
		extentTest = extentReports.createTest("Login Page");
//		driver.get("https://hrmsxapi.antllp.com/Account/Login");
//		try {
//		driver.findElement(By.xpath("//button[contains(text(),'Advanced')]")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'hrmsxapi')]")).click();
//		}catch(Exception e) {}
//		driver.get("https://hrmsxapi.antllp.com/Account/Login");
//		driver.findElement(By.id("LoginInput_UserNameOrEmailAddress")).sendKeys("admin");
//		driver.findElement(By.id("LoginInput_Password")).sendKeys("1q2w3E*");
//		driver.findElement(By.name("Action")).click();
		driver.get("http://localhost:4200/login");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).isDisplayed(), "EMP code feild should be displayed");
		assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='password']")).isDisplayed(), "password feild should be displayed");
		assertTrue(driver.findElement(By.xpath("//a[text()='Forgot password']")).isDisplayed(),"Forget password feild should be visible");
	}
	
	@Test
	public void TEST02_ValidateLoginPage() {
		extentTest = extentReports.createTest("Validate Login Page");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("xyz");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contans(text(),'Please']")));
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
	}
	
	//@Test
	public void Test03_ForgetPasswordPage() {
		extentTest = extentReports.createTest("Forget Password Page");
		driver.findElement(By.xpath("//a[text()='Forgot password']")).click();
		assertTrue(driver.getCurrentUrl().contains("forgot"),"Forget Password Should be functional");
		driver.navigate().back();
		
	}
	
	@Test
	public void TEST04_DashboardPage() {
		extentTest = extentReports.createTest("Dashboard Page");
		if(minutes>00 && minutes<15){
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()='Log In']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'Welcome')]/span")).isDisplayed(), "Welcome message should be displyed");
			isEmployee=false;
			isManager=false;
			isAdmin=true;
			isHR=true;
		}else if (minutes > 15 && minutes<30) {
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()='Log In']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			isEmployee=false;
			isManager=false;
			isAdmin=true;
			isHR=true;
		}else if (minutes>30 && minutes<45){
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()='Log In']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			isEmployee=false;
			isManager=false;
			isAdmin=true;
			isHR=true;
		}else{
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='userNameOrEmailAddress']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()='Log In']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			isEmployee=false;
			isManager=false;
			isAdmin=true;
			isHR=true;
		}
		System.out.println(isEmployee+"\n"+isManager+"\n"+isAdmin+"\n"+isHR+"\n");
	}
	
	
}
