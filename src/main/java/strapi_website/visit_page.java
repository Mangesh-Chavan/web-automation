package strapi_website;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class visit_page extends TestConfig {

	@Test
	public void test_01_launch() throws InterruptedException {
		driver.get("https://strapi-148484-0.cloudclusters.net/admin/auth/login");
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.id("2")).sendKeys("info@antllp.com");
		driver.findElement(By.id("4")).sendKeys("*#**372#Ha");
		driver.findElement(By.cssSelector("svg[focusable='false']")).click();
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Login']")));
		// driver.findElement(By.xpath("//span[text()='Login']")).click();
	}

	@Test
	public void test_02_AddProduct() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//span[text()='Content Manager']")).click();
		driver.findElement(By.xpath("//span[text()='Product']")).click();
		Thread.sleep(2000);
//		JavascriptExecutor js2 = (JavascriptExecutor) driver;
//		js2.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Create new entry']")));
		// driver.findElement(By.xpath("//span[text()='Create new entry']")).click();
		// C:\Users\harsh\OneDrive\Documents

		String pathString = System.getProperty("user.dir") + "./src/test/resources/testdata/Million Product List.xlsx";// "C:/Users/harsh/Downloads/";
		FileInputStream file = new FileInputStream(pathString);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet2");//246
		int rowcount = sheet.getLastRowNum();//245
		int colcount = sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount : " + rowcount + "/tcolcount : " + colcount);
		for (int i = 28; i <= rowcount; i++) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Create new entry']/parent::a")).click();
			Thread.sleep(2000);
			XSSFRow celldata = sheet.getRow(i);

			// Imported excell with row/col data
			String Title = celldata.getCell(1).toString();
			String Name = celldata.getCell(3).toString();
			String Code = celldata.getCell(2).toString();
			String HSN = celldata.getCell(4).toString();
//			String metatags = celldata.getCell(4).toString();
//			String metadescription = celldata.getCell(5).toString();
			String range = celldata.getCell(0).toString();

			// Putting data to perticular fields
			driver.findElement(By.id("Title")).sendKeys(Title);
			Thread.sleep(1000);
			driver.findElement(By.id("Name")).clear();
			driver.findElement(By.id("Name")).sendKeys(Name);
			driver.findElement(By.id("Code")).clear();
			driver.findElement(By.id("Code")).sendKeys(Code);
			driver.findElement(By.id("HSN")).clear();
			driver.findElement(By.id("HSN")).sendKeys(HSN);
//			driver.findElement(By.id("metatags")).clear();
//			driver.findElement(By.id("metatags")).sendKeys(metatags);
//			driver.findElement(By.id("metadescription")).clear();
//			driver.findElement(By.id("metadescription")).sendKeys(metadescription);
			driver.findElement(By.id("range")).clear();
			driver.findElement(By.id("range")).sendKeys(new CharSequence[] { range });
			Thread.sleep(2000);
			driver.findElement(By.id("range")).sendKeys(new CharSequence[] { Keys.TAB });
//			driver.findElement(By.xpath("//span[contains(text(),'Click to add')]/parent::button")).click();
//			Thread.sleep(15000);
			driver.findElement(By.xpath("//span[text()='Save']/parent::button")).click();
			Thread.sleep(1000);
//			driver.findElement(By.xpath("//span[text()='Publish']/parent::button")).click();
//			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Back']/parent::a")).click();
		}

	}

}
