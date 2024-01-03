package mahindra;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class adminpanel extends TestConfig {
	@Test
	public void test01_Login() throws InterruptedException {
		extentTest = extentReports.createTest("check URL");
		// driver.get("http://localhost:4200/#/login");
		driver.get("https://mahindraadmin.antllp.com/");
		driver.findElement(By.xpath("//input[@class=\"form-control ng-untouched ng-pristine ng-invalid\"]"))
				.sendKeys("admin@admin.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[@class='px-4 btn btn-primary']")).click();
		Thread.sleep(2000);
	}

	@Test
	public void test02_Dashboard() throws InterruptedException {
		extentTest = extentReports.createTest("login");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		// Scroll up the page
		js.executeScript("window.scrollTo(0, 0)");
//		driver.findElement(By.xpath("//a[@href='/application-masters/crops']")).click();
//		driver.findElement(By.xpath("//a[@href='#/application-masters/crops']")).click();

	}

	@Test
	public void test03_Administration() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("dashboard");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Add']/parent::button")).click();
		driver.findElement(By.id("mat-input-1")).sendKeys("gehu2");
		driver.findElement(By.name("fileUpload")).click();
		Thread.sleep(3000);
		// FileInputStream file = new
		// FileInputStream("C:\\Users\\DELL\\Downloads.webp");
		// XSSFWorkbook workbook = new XSSFWorkbook(file);
		driver.findElement(By.id("fileUpload")).sendKeys("C:\\Users\\DELL\\Downloads.webp");
		driver.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();

	}

}
