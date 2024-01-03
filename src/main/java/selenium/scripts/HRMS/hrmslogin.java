package selenium.scripts.HRMS;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hrmslogin {
	public static WebDriver driver;
	
	@Test //For Admin login
	public void test_01login() throws InterruptedException { //This line called as method name
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://uathrms.antllp.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='txtEmployeeId']")).sendKeys("1017");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("dummy@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		
	}
	
	//@Test 
	public void test_02ForgotPassword() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='/Home/ForgotPassword']")).click();
		driver.findElement(By.xpath("//input[@id='txtEmpCode']")).sendKeys("1017");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//span[text()='Password sent to your mail.']")).getText());
		
	}
	
	@Test
	public void test_03ApplyLeave() throws InterruptedException {
		driver.findElement(By.xpath("//h4[text()='Apply Leave']")).click();
		System.out.println("We are on "+driver.findElement(By.xpath("//strong[text()='Leave Details']")).getText());
		WebElement filterdrop = driver.findElement(By.id("ddlstatus"));
		Select dropdown = new Select(filterdrop);
		dropdown.selectByIndex(0);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Apply For Leave']")).click();
		WebElement leavedropdown = driver.findElement(By.xpath("//select[@name='AttendentId']"));
		Select dropdown1 = new Select(leavedropdown);
		dropdown1.selectByIndex(1);
		System.out.println(dropdown1.getFirstSelectedOption().getText());
		Thread.sleep(3000);

		System.out.println(driver.findElement(By.xpath("//select[@name='AttendentId']")).getText());
		driver.findElement(By.xpath("//input[@id='fromdate']")).sendKeys("09-06-2023");
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//input[@id='todate']")).sendKeys("09-07-2023");  //To(till) date is not accepting the value.
		System.out.println(driver.findElement(By.id("startdatelable")).isSelected());
		driver.findElement(By.id("startdatelable")).click();
		System.out.println(driver.findElement(By.id("startdatelable")).isSelected());
		driver.findElement(By.xpath("//textarea[@id='Reason']")).sendKeys("Sick leave, because of cold & cough");
		//driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-danger text-white']")).isDisplayed()); //Clicking on Cancel button
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@class='btn btn-danger text-white']")));
		driver.findElement(By.xpath("//span[text()='LMS Dashboard']")).click();
		
	}
	
	@Test
	public void test_04Calendar() throws InterruptedException {
		driver.findElement(By.xpath("//h4[text()='Calendar']")).click();
		driver.findElement(By.xpath("//button[@class='btn mb-3 text-white']")).click();
//		driver.findElement(By.xpath("//button[@class='btn-close text-reset']")).click(); If single line code not excutable then add javascript.
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn-close text-reset']")).isDisplayed());
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@class='btn-close text-reset']")));
		driver.findElement(By.xpath("//span[text()='LMS Dashboard']")).click();
	
	}
	@Test
	
	public void test_05CompoffRequest() throws InterruptedException {
		driver.findElement(By.xpath("//h4[text()='Compoff Request']")).click();
		driver.findElement(By.xpath("//input[@id='txtWorkingDate']")).sendKeys("24-06-2023");
		WebElement dropReason  = driver.findElement(By.id("ddlemployeelst"));
		Select dropdown = new Select(dropReason);
		dropdown.selectByIndex(1);
		//System.out.println(dropdown.getFirstSelectedOption().getText()); 
		driver.findElement(By.xpath("//button[@class='btn text-white']")).click(); //Send request button
		System.out.println(driver.findElement(By.xpath("//span[@data-valmsg-for='WorkingDate']")).getText());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-danger text-white']")).click();
		
	}
	
	@Test
	public void test_06ApproveLeaves() throws InterruptedException {
		driver.findElement(By.xpath("//h4[text()='Approve Leaves']")).click();
		WebElement approvedrop = driver.findElement(By.id("ddlstatus"));
		Select dropdownSelect = new Select(approvedrop);
		dropdownSelect.selectByIndex(1);
		System.out.println(dropdownSelect.getFirstSelectedOption().getText());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='LMS Dashboard']")).click();
				
	}
	
	@Test
	public void test_07AssignCompOff() throws InterruptedException {
		driver.findElement(By.xpath("//h4[text()='Assign CompOff']")).click();
		System.out.println("We are on "+driver.findElement(By.xpath("//h5[text()='Assign Comp Off']")).getText());
		WebElement assigndropElement = driver.findElement(By.id("ddlemployeelst"));
		Select dropdownSelect = new Select(assigndropElement);
		dropdownSelect.selectByIndex(20);
		System.out.println("Assigning Compoff to "+dropdownSelect.getFirstSelectedOption().getText());
//		Assert.assertTrue(driver.findElement(By.id("txtWorkingDate")).isDisplayed());
//		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
//		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.id("txtWorkingDate")));
		driver.findElement(By.id("txtWorkingDate")).click();
		driver.findElement(By.id("Remark")).sendKeys("Assinging compoff because he worked on weekoff");
		System.out.println(driver.findElement(By.id("Remark")).getText());
		driver.findElement(By.xpath("//button[@class='btn text-white']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-danger text-white']")).click();
		
	}
	
	//@Test
	public void test_08Reports() {
		driver.findElement(By.xpath("//h4[text()='Reports']")).click();
		System.out.println("We are finding "+driver.findElement(By.xpath("//h5[text()='Leave Reports']")).getText());
		driver.findElement(By.xpath("//input[@name='fromdate']")).sendKeys("09-06-2023");
		driver.findElement(By.xpath("//input[@name='todate']")).sendKeys("09-06-2023");
		WebElement Leavedrop = driver.findElement(By.xpath("//Select[@class='form-select form-control']"));
		Select dropSelect = new Select(Leavedrop);
		dropSelect.selectByIndex(1);
		System.out.println(dropSelect.getFirstSelectedOption().getText());
		driver.findElement(By.xpath("//button[@class='btn text-truncate text-white']")).click();
	}
	

}
