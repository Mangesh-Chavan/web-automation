package selenium.scripts.million;

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
		driver.findElement(By.id("2")).sendKeys("harshad@antllp.com");
		driver.findElement(By.id("4")).sendKeys("DnfndL1k");
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
		XSSFSheet sheet = workbook.getSheet("Final List");
		int rowcount = sheet.getLastRowNum();
		System.out.print("hiii"+rowcount);
		int colcount = sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount : " + rowcount + "/tcolcount : " + colcount);
		for (int i = 300; i <= 243; i++) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Create new entry']/parent::a")).click();
			Thread.sleep(2000);
			XSSFRow celldata = sheet.getRow(i);

			// Imported excell with row/col data
			String HSN = celldata.getCell(7).toString();
			String Varient_Name = celldata.getCell(4).toString();
			double Code = Double.parseDouble(celldata.getCell(5).toString());
			int integerCode = (int) Code;
			String Sub_Category = celldata.getCell(3).toString();
			String Product_Name = celldata.getCell(2).toString();
			String Product_Range = celldata.getCell(0).toString();
			String Brand_Range = celldata.getCell(1).toString();
			String Module = celldata.getCell(6).toString();
			String Dimension = celldata.getCell(10).toString();
			System.out.print("here"+Dimension+"space is");
			String Voltage = celldata.getCell(8).toString();
			String Ampere = celldata.getCell(9).toString();
			
			String Std_Master_Packaging = celldata.getCell(11).toString();
			String color1 = celldata.getCell(12).toString();
			
			
			//Add Image 
			driver.findElement(By.xpath("//span[contains(text(),'Click to add an asset')]/parent::button")).click();
			sleep(1000);
			driver.findElement(By.xpath("//span[text()='Add new assets']/following::span[contains(text(),'Search')]/parent::button")).click();
			sleep(1000);
			driver.findElement(By.xpath("//input[@name='search']")).sendKeys(""+integerCode + Keys.ENTER);
			sleep(1000);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//span[text()='Finish']/parent::button")).click();
			
			// Putting data to perticular fields
			driver.findElement(By.id("HSN")).clear();
			driver.findElement(By.id("HSN")).sendKeys(HSN);
			driver.findElement(By.id("Varient_Name")).clear();
			driver.findElement(By.id("Varient_Name")).sendKeys(Varient_Name);
			sleep(2000);
			driver.findElement(By.id("Code")).clear();
			driver.findElement(By.id("Code")).sendKeys(""+integerCode);
			//driver.findElement(By.id("Sub_Category")).sendKeys(new CharSequence[] { Sub_Category });
			sleep(2000);
			driver.findElement(By.id("Sub_Category")).sendKeys(new CharSequence[] { Keys.TAB });
			driver.findElement(By.id("Product_Name")).clear();
			driver.findElement(By.id("Product_Name")).sendKeys(new CharSequence[] { Product_Name });
			driver.findElement(By.id("Product_Range")).sendKeys(new CharSequence[] { Product_Range });
			sleep(2000);
			driver.findElement(By.id("Product_Range")).sendKeys(new CharSequence[] { Keys.TAB });
			driver.findElement(By.id("Brand_Range")).sendKeys(new CharSequence[] { Brand_Range });
			sleep(2000);
			driver.findElement(By.id("Brand_Range")).sendKeys(new CharSequence[] { Keys.TAB });
			driver.findElement(By.id("Module")).clear();
			driver.findElement(By.id("Module")).sendKeys(new CharSequence[] { Module });
			driver.findElement(By.id("Dimension")).clear();
			driver.findElement(By.id("Dimension")).sendKeys(new CharSequence[] { Dimension });
			driver.findElement(By.id("Voltage")).clear();
			driver.findElement(By.id("Voltage")).sendKeys(new CharSequence[] { Voltage });
			driver.findElement(By.id("Ampere")).clear();
			driver.findElement(By.id("Ampere")).sendKeys(new CharSequence[] { Ampere });
			driver.findElement(By.id("Std_Master_Packaging")).clear();
			driver.findElement(By.id("Std_Master_Packaging")).sendKeys(Std_Master_Packaging);
			sleep(2000);
			driver.findElement(By.id("Colors")).sendKeys(new CharSequence[] { color1 });
			sleep(2000);
			driver.findElement(By.id("Colors")).sendKeys(new CharSequence[] { Keys.TAB });
			try {
				String color2 = celldata.getCell(13).toString();
				driver.findElement(By.id("Colors")).sendKeys(new CharSequence[] { color2 });
				sleep(2000);
				driver.findElement(By.id("Colors")).sendKeys(new CharSequence[] { Keys.TAB });
			}catch(Exception e) {}
			driver.findElement(By.xpath("//span[text()='Save']/parent::button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[text()='Publish']/parent::button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Back']/parent::a")).click();
		}

	}

}
