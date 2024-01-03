package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_HR_Admin extends TestConfig {

	JavascriptExecutor js;
	
	@Test
	public void TEST01_HR_Admin_Page() {
		if (Login_Page.isHR) {
			extentTest = extentReports.createTest("HR Admin Page");
			js = (JavascriptExecutor) driver;
			driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			assertTrue(driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")).isDisplayed(),
					"Dropdown should be visible");
			//assertFalse(driver.findElement(By.xpath("//span[text()='Download Template']")).isEnabled(), "Download button should be Disabled before selection");

		}
	}

	@Test
	public void TEST02_Download_Holidaylist() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Download Holiday");
			driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Holiday')]")).click();
			driver.findElement(By.xpath("//span[text()='Download Template']")).click();
			sleep(10000L);
			//String filename = getFileName(driver);
			String path = "C:/Users/DELL/Downloads/"+ getFileName(driver);
			File f = new File(path);
			//String fileContent;
			
			if(f.canRead() && f.length() > 0)
			{
				//fileContent = getfileContent(f);
				//System.out.println("text=" +fileContent);
				assertTrue(path.contains("Holiday"),"Wrong Template should not get Downloaded");
			}
			else {
				assertTrue(false,"File not downloaded");
			}
		}
		
	}

	@Test
	public void TEST03_Download_EmployeeList() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Download Employeelist");
			driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'New Employee')]")).click();
			driver.findElement(By.xpath("//span[text()='Download Template']")).click();
			sleep(10000L);
			String filename = getFileName(driver);
			String path = "C:/Users/DELL/Downloads/"+ filename;
			//String fileContent="";
			if(path.contains("xlsx") && path.length() > 0)
			{
//				File f = new File(path);
//				fileContent = getfileContent(f);
				assertTrue(path.contains("Employee"),filename + "Wrong should not get Downloaded");
			}
			else {
				assertTrue(false,"File not downloaded");
			}
		}
	}
	
	@Test
	public void TEST04_UploadWrong_HolidayList() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Upload Wrong Exel");
			driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")).click();
			sleep(1000);
			driver.findElement(By.xpath("//span[contains(text(),'Holiday')]")).click();
			sleep(1000);
			driver.findElement(By.xpath("//input[@formcontrolname='uploadFile']")).sendKeys("C://Users//DELL//Downloads//LeaveReport.xlsx");
			//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@formcontrolname='uploadFile']")));
//			String filePath = "C:/Users/DELL/Downloads/HolidayDetails.xlsx";
			
//			Screen s = new Screen();
			
//			Pattern fileinputTestbox = new Pattern("./filepath.jpg");
			
//			Pattern openButton = new Pattern("./open.jpg");
			
//			sleep(10000);
//			try {
//				s.wait(fileinputTestbox,20);
//				s.type(filePath);
//				s.click(openButton);
//			} catch (FindFailed e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			driver.findElement(By.xpath("//span[text()='Upload']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"Somthing went wrong !",
					"Error message should be displayed");
		}
	}
	
	@Test
	public void TEST05_UploadWrong_EmployeeList() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Upload Wrong Exel");
			driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")));
			driver.findElement(By.xpath("//span[contains(text(),'New Employee')]")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='uploadFile']")).sendKeys("C://Users//DELL//Downloads//LeaveReport.xlsx");
			sleep(10000);
			driver.findElement(By.xpath("//span[text()='Upload']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"Somthing went wrong !",
					"Error message should be displayed");
		}
	}
	
	@Test
	public void TEST06_Upload_EmployeeList() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Upload Holidaylist");
			//driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")));
			driver.findElement(By.xpath("//span[contains(text(),'Holiday')]")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='uploadFile']")).sendKeys("C:/Users/DELL/Downloads/HolidayDetails.xlsx");
			sleep(10000);
			driver.findElement(By.xpath("//span[text()='Upload']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"Template uploaded successfully.",
					"Sucess message should be displayed");
		}
	}
	
	//@Test
	public void TEST07_Upload_EmployeeList() {
		if (Login_Page.isAdmin) {
			extentTest = extentReports.createTest("Upload EmployeeList");
			//driver.findElement(By.xpath("//button[text()=' HR Admin ']")).click();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//mat-select[@formcontrolname='uploadType']")));
			driver.findElement(By.xpath("//span[contains(text(),'New Employee')]")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='uploadFile']")).sendKeys("C:/Users/DELL/Downloads/EmployeeInformation.xlsx");
			sleep(10000);
			driver.findElement(By.xpath("//span[text()='Upload']")).click();
			assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),
					"Template uploaded successfully.",
					"Success message should be displayed");
		}
	}
}
