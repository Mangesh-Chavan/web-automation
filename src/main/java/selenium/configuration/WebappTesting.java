
package selenium.configuration;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebappTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"D:\\Navneet\\Eclipse-Projects\\selenium\\src\\main\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://leadmanagement.projectnimbus.co.in/");
		driver.findElement(By.xpath("//a[contains(@href,'Login')]")).click();
		driver.findElement(By.xpath("//input[contains(@name,'UserNameOrEmailAddress')]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[contains(@name,'Password')]")).sendKeys("1q2w3E*");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//button[contains(@name,'Action')]")));
		// driver.findElement(By.xpath("//button[contains(@name,'Action')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Lead Master')])[2]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create Lead')])")).click();
		driver.findElement(By.id("ViewModel_Name")).sendKeys("Mangesh");
		driver.findElement(By.id("ViewModel_Address")).sendKeys("abcd efgh");
		driver.findElement(By.id("ViewModel_Email")).sendKeys("man@gmail.com");
		driver.findElement(By.id("ViewModel_Mobile")).sendKeys("8888888888");
		driver.findElement(By.id("ViewModel_ReferenceBy")).sendKeys("abc");
		driver.findElement(By.id("ViewModel_Description")).sendKeys("abcdefgh");
		new Select(driver.findElement(By.id("ViewModel_LeadType"))).selectByValue("2");
		driver.findElement(By.xpath("//span[text()='Save']")).click();

		driver.findElement(By.xpath("(//span[contains(text(),'Meeting Master')])[2]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Schedule Meeting')])")).click();
		driver.findElement(By.id("ViewModel_LeadId")).click();
		driver.findElement(By.xpath("//option[text()='Bharat Thorat']")).click();
		driver.findElement(By.id("ViewModel_Title")).sendKeys("Automation");
		driver.findElement(By.id("ViewModel_Description")).sendKeys("abcdefgh");
		driver.findElement(By.id("ViewModel_MeetingDateTime")).sendKeys("12-05-2023" + Keys.TAB + "1930");
		driver.findElement(By.xpath("//span[text()='Save']")).click();

	}

}
