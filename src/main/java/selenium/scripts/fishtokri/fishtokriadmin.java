package selenium.scripts.fishtokri;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class fishtokriadmin extends TestConfig {
	@Test
	public void test01() throws InterruptedException {
		extentTest = extentReports.createTest("check URL");
		driver.get("https://fishtokriadmin.antllp.com/account/login");
		Thread.sleep(2000);
	}

	@Test
	public void test02() {
		extentTest = extentReports.createTest("Login with credential");
		driver.findElement(By.id("login-input-user-name-or-email-address")).sendKeys("Shridhar_Shende");
		driver.findElement(By.id("login-input-password")).sendKeys("fishtokri@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	@Test
	public void test03() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Home screen");
		driver.findElement(By.xpath("//span[.='Inventery Managment']")).click();
		driver.findElement(By.xpath("//span[.='Add Inventery']")).click();

		FileInputStream file = new FileInputStream("C:\\Users\\DELL\\Downloads\\Products detail.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount :" + rowcount + "colcount :" + colcount);
		for (int i = 156; i < rowcount; i++) {
			Thread.sleep(3000);

			XSSFRow celldata = sheet.getRow(i);// this line select data from i th row.
			String Category = celldata.getCell(0).toString();
			String ProductName = celldata.getCell(1).toString();

			driver.findElement(By.xpath("//span[@tabindex='-1']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@aria-label='multiselect-search']")).sendKeys(ProductName);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::div")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//select[@formcontrolname=\"batchNumber\"]")).sendKeys("2023");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@placeholder='Enter Quantity']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Enter Quantity']")).sendKeys("10");
			Thread.sleep(1000);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@type='submit']")));
			// driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
	}
}
