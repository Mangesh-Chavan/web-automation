package selenium.scripts.fishtokri;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class fishtokari_customer extends TestConfig {

	@Test
	public void test_01_page() throws InterruptedException {
//		driver.get("http://localhost:4200");
		driver.get("https://fishtokriadmin.antllp.com/#/account/login");
		Thread.sleep(2000);
		// File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType);

	}

	// @Test //forgot pwd
	public void test_02_ForgetPwd() throws InterruptedException {
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(2000);
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//a[@href='/account/forgot-password']")).click();
		driver.findElement(By.xpath("//input[@id='input-email-address']")).sendKeys(" have to insert valid email id");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/account/login']")).click();
	}

	@Test
	public void test_03_Login() throws InterruptedException {
//		driver.findElement(By.id("details-button")).click();
//		driver.findElement(By.id("proceed-link")).click();
		driver.findElement(By.xpath("//input[@id='login-input-user-name-or-email-address']")).sendKeys("Navneet");
		driver.findElement(By.xpath("//input[@id='login-input-password']")).sendKeys("Fishtokri@123");
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@id='login-input-remember-me']")).click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='login-input-remember-me']")).click();
//		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Login successful...!");
	}

	// @Test
	public void test_04_AddProduct() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//span[text()='Product']")).click();
		driver.findElement(By.xpath("//span[text()='Product List']")).click();
		String pathString = "C:/Users/harsh/Downloads/Products detail.xlsx"; // file path

		FileInputStream file = new FileInputStream(pathString);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount : " + rowcount + "colcount : " + colcount);
		for (int i = 9; i <= rowcount; i++) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Add']")).click();
			Thread.sleep(2000);
			XSSFRow celldata = sheet.getRow(i);

			// Imported excell with row/col data
			String Category = celldata.getCell(0).toString();
			String Product = celldata.getCell(1).toString();
			String Description = celldata.getCell(2).toString();
			String SKU = celldata.getCell(3).toString();
			String MRP = celldata.getCell(4).toString();
			String Discount = celldata.getCell(5).toString();
			// String Pieces = celldata.getCell(6).getStringCellValue();
			String Serving = celldata.getCell(6).toString();
			String Storage = celldata.getCell(7).toString();
			String NetWeight = celldata.getCell(8).toString();
			String GrossWeight = celldata.getCell(9).toString();
			String CookingTime = celldata.getCell(10).toString();

			// Putting data to perticular fields
			driver.findElement(By.xpath("//select[@id='category']")).sendKeys(new CharSequence[] { Category });
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@formcontrolname='productName']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productName']")).sendKeys(Product);
			driver.findElement(By.xpath("//textarea[@formcontrolname='productDescription']")).clear();
			driver.findElement(By.xpath("//textarea[@formcontrolname='productDescription']")).sendKeys(Description);
			String imagePath = "C:/Users/harsh/Downloads/Category";
			try {
				driver.findElement(By.xpath("//input[@type='file']"))
						.sendKeys(imagePath + "/" + Category + "/" + Product + ".png");// C:/Users/harsh/Downloads/Category/Chicken/Chicken
																						// boneless cubes.png
			} catch (Exception e) {
				driver.findElement(By.xpath("//input[@type='file']"))
						.sendKeys("C:/Users/harsh/Downloads/Category/default.png");
			}

			driver.findElement(By.xpath("//input[@formcontrolname='productSKU']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productSKU']")).sendKeys(SKU);
			// driver.findElement(By.xpath("//select[@formcontrolname='productAttributeId']")).clear();
			driver.findElement(By.xpath("//select[@formcontrolname='productAttributeId']")).sendKeys("Weight");
			// driver.findElement(By.xpath("//select[@formcontrolname='productAttributeUnit']")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//select[@formcontrolname='ProductSubAttributeId']")).sendKeys("GM");
			driver.findElement(By.xpath("//input[@formcontrolname='productGrossWeight']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productGrossWeight']")).sendKeys(GrossWeight);
			driver.findElement(By.xpath("//input[@formcontrolname='productNetWeight']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productNetWeight']")).sendKeys(NetWeight);
			driver.findElement(By.xpath("//input[@formcontrolname='productMRP']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productMRP']")).sendKeys(MRP);
			driver.findElement(By.xpath("//input[@formcontrolname='productDiscount']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='productDiscount']")).sendKeys(Discount);
			driver.findElement(By.xpath("//input[@formcontrolname='Servings']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='Servings']")).sendKeys(Serving);
			driver.findElement(By.xpath("//input[@formcontrolname='Storage']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='Storage']")).sendKeys(Storage);
			driver.findElement(By.xpath("//input[@formcontrolname='CookingTime']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='CookingTime']")).sendKeys(CookingTime);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 900)");
			Thread.sleep(1000);

			driver.findElement(By.xpath("//button[@type='submit']")).click();

//			new WebDriverWait(driver, Duration.ofSeconds(6))
//					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")));

		}
		workbook.close();
	}

	// @Test
	public void test_05_AddStore() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//span[text()='Masters']")).click();
		driver.findElement(By.xpath("//span[text()='Store']")).click();
		driver.findElement(By.xpath("//span[text()='Store List']")).click();
		driver.findElement(By.xpath("//span[text()='Add']")).click();
		// String path = "C:/Users/harsh/OneDrive/Documents/storelist.xlsx";
		String path = "C:/Users/harsh/Downloads/Products detail.xlsx";
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet2");
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getLastRowNum();
		System.out.println("rocount : " + rowcount + "colcount : " + colcount);
		for (int i = 1; i < rowcount; i++) {
			Thread.sleep(2000);
			XSSFRow celldata = sheet.getRow(i);

			// ExcelSheet column Name
			String StoreName = celldata.getCell(0).toString();
			String StoreNo = celldata.getCell(1).toString();
			// String timeslot = celldata.getCell(2).toString();
			String Pertimeslot = celldata.getCell(3).toString();
			String starttime = celldata.getCell(4).toString();
			String endtime = celldata.getCell(5).toString();
			String Addline1 = celldata.getCell(6).toString();
			String Addline2 = celldata.getCell(7).toString();
			String State = celldata.getCell(8).toString();
			String City = celldata.getCell(9).toString();
			String Pincode = celldata.getCell(10).toString();
			// String Availarea = celldata.getCell(11).toString();
			String Username = celldata.getCell(12).toString();
			String Useremail = celldata.getCell(13).toString();
			String Userrole = celldata.getCell(14).toString();
			String Userpwd = celldata.getCell(15).toString();

			// Website locators to input values
			driver.findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys(StoreName);
			driver.findElement(By.xpath("//input[@formcontrolname='storeNo']")).sendKeys(StoreNo);
			driver.findElement(By.xpath("(//span[@tabindex='-1'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text()='Select All']")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//label[text()='Order Per Time Slot']")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//input[@formcontrolname='storeOrderPerTimeSlot']")).sendKeys(Pertimeslot);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//input[@formcontrolname='storeStartTime']")).sendKeys(starttime);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//input[@formcontrolname='storeEndTime']")).sendKeys(endtime);
			driver.findElement(By.xpath("//input[@formcontrolname='addressLine1']")).sendKeys(Addline1);
			driver.findElement(By.xpath("//input[@formcontrolname='addressLine2']")).sendKeys(Addline2);
			driver.findElement(By.xpath("//select[@formcontrolname='state']")).sendKeys(State);
			driver.findElement(By.xpath("//select[@formcontrolname='city']")).sendKeys(City);
			driver.findElement(By.xpath("//input[@formcontrolname='pincode']")).sendKeys(Pincode);
			driver.findElement(By.xpath("(//span[@tabindex='-1'])[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text()='Select All']")).click();

			driver.findElement(By.xpath("//input[@formcontrolname='userName']")).sendKeys(Username);
			driver.findElement(By.xpath("//input[@formcontrolname='userEmail']")).sendKeys(Useremail);
			driver.findElement(By.xpath("//select[@formcontrolname='roleNames']")).sendKeys(Userrole);
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(Userpwd);
			driver.findElement(By.xpath("//button[@name='submit']")).click();

		}

	}

	// @Test
	public void test_06_AddInventroy() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//span[text()='Inventery Managment']")).click();
		driver.findElement(By.xpath("//span[text()='Add Inventery']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String pathString = "C:/Users/harsh/Downloads/Products detail.xlsx"; // file path

		FileInputStream file = new FileInputStream(pathString);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount : " + rowcount + "colcount : " + colcount);
		for (int i = 155; i <= rowcount; i++) {
			Thread.sleep(2000);
			// driver.findElement(By.xpath("//span[text()='Add']")).click();
//			Thread.sleep(2000);
			XSSFRow celldata = sheet.getRow(i);

			// Imported excell with row/col data
			String Product = celldata.getCell(1).toString();
			String MRP = celldata.getCell(4).toString();
			String Discount = celldata.getCell(5).toString();
			Thread.sleep(100);
			driver.findElement(By.xpath("//span[@tabindex='-1']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(Product); // SKU Gaven
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::div")).click();
			Thread.sleep(3000);
//			driver.findElement(By.xpath("//option[text()=' FhishTokri-STR-001-400601 ']")).click(); // Store selected
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("//select[@formcontrolname='batchNumber']")).sendKeys("2023001"); // Batch																		// selected
//			Thread.sleep(1000);
			// driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::div")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='price']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='price']")).sendKeys(MRP);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@formcontrolname='discount']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='discount']")).sendKeys(Discount);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).sendKeys("100");
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()=' Submit ']")));
			// driver.findElement(By.xpath("//button[text()=' Submit ']")).click();
			Thread.sleep(2000);
		}
		workbook.close();
	}

	@Test
	public void test_07_AddInventroytoStore() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//span[text()='Inventery Managment']")).click();
		driver.findElement(By.xpath("//span[text()='Add Inventery']")).click();
		String pathString = "C:/Users/harsh/Downloads/Products detail.xlsx"; // file path

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		FileInputStream file = new FileInputStream(pathString);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount : " + rowcount + "colcount : " + colcount);
		for (int i = 156; i <= rowcount; i++) {
			Thread.sleep(2000);

			XSSFRow celldata = sheet.getRow(i);

			// Imported excell with row/col data
			String Category = celldata.getCell(0).toString();
			String Product = celldata.getCell(1).toString();
			String Description = celldata.getCell(2).toString();
			String SKU = celldata.getCell(3).toString();
			String MRP = celldata.getCell(4).toString();
			String Discount = celldata.getCell(5).toString();
			// String Pieces = celldata.getCell(6).getStringCellValue();
			String Serving = celldata.getCell(6).toString();
			String Storage = celldata.getCell(7).toString();
			String NetWeight = celldata.getCell(8).toString();
			String GrossWeight = celldata.getCell(9).toString();
			String CookingTime = celldata.getCell(10).toString();

			driver.findElement(By.xpath("//span[@tabindex='-1']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(Product);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::div")).click();
//			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			Thread.sleep(2000);
//			driver.findElement(By.xpath("//select[@formcontrolname='storeId']")).sendKeys("Fish");
			driver.findElement(By.xpath("//label[text()='stores']")).click();
//			Thread.sleep(1000);
			driver.findElement(By.xpath("//select[@formcontrolname='batchNumber']")).sendKeys("2023");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).clear();
			driver.findElement(By.xpath("//input[@formcontrolname='quantity']")).sendKeys("20");
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()=' Submit ']")));
			// driver.findElement(By.xpath("//button[text()=' Submit ']")).click();
			Thread.sleep(2000);
		}
		workbook.close();
	}

}
