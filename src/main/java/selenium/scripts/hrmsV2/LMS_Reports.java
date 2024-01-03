package selenium.scripts.hrmsV2;

import static org.testng.Assert.assertTrue;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class LMS_Reports extends TestConfig{
	
	
	LocalDate date;
	
	@Test
	public void TEST01_Reports()
	{
		if(Login_Page.isHR){
			extentTest = extentReports.createTest("Reports Page");
			driver.findElement(By.xpath("//button[text()=' Reports ']")).click();
			assertTrue(driver.findElement(By.xpath("//button[text()='Leave Report']")).isDisplayed(),"Leave Report tile should be visible");
			assertTrue(driver.findElement(By.xpath("//button[text()='Attendance Report']")).isDisplayed(),"Attendance Report tile should be visible");
		}
	}
	
	@Test
	public void TEST02_Leave_Report() {
		if(Login_Page.isHR) {
			driver.findElement(By.xpath("//button[text()='Leave Report']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='leaveFrom']")).click();
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			driver.findElement(By.xpath("//span[text()=' 1 ']")).click();
			sleep(500);
			driver.findElement(By.xpath("//input[@formcontrolname='leaveTo']")).click();
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			driver.findElement(By.xpath("//span[text()=' 30 ']")).click();
			sleep(1000);
			driver.findElement(By.xpath("//mat-select[@formcontrolname='empID']")).sendKeys(new CharSequence[]{"1000"+Keys.DOWN} );
			driver.findElement(By.xpath("//span[text()='Download']")).click();
			sleep(10000L);
			String filename = getFileName(driver);
			String path = "C:/Users/DELL/Downloads/"+ filename;
			if(path.contains("xlsx") && path.length() > 0)
			{
//				File f = new File(path);
//				fileContent = getfileContent(f);
				assertTrue(path.contains("Leave"),filename + "Wrong should not get Downloaded");
			}
			else {
				assertTrue(false,"File not downloaded");
			}
		}
	}
	
	@Test
	public void TEST03_Attendance_Report() {
		if(Login_Page.isHR) {
			driver.findElement(By.xpath("//button[text()='Attendance Report']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='attendanceFrom']")).click();
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			driver.findElement(By.xpath("//span[text()=' 1 ']")).click();
			sleep(500);
			driver.findElement(By.xpath("//input[@formcontrolname='attendanceTo']")).click();
			driver.findElement(By.xpath("//button[contains(@aria-label,'Previous month')]")).click();
			driver.findElement(By.xpath("//span[text()=' 30 ']")).click();
			sleep(1000);
			//driver.findElement(By.xpath("//mat-select[@formcontrolname='empID']")).sendKeys("100" +Keys.DOWN+Keys.ENTER);
			driver.findElement(By.xpath("//span[text()='Download']")).click();
			sleep(10000L);
			String filename = getFileName(driver);
			String path = "C:/Users/DELL/Downloads/"+ filename;
			if(path.contains("xlsx") && path.length() > 0)
			{
//				File f = new File(path);
//				fileContent = getfileContent(f);
				assertTrue(path.contains("report"),filename + "Wrong should not get Downloaded");
			}
			else {
				assertTrue(false,"File not downloaded");
			}
		}
	}

}
