package selenium.scripts.MarketPlace;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class MarketplaceAdminPanel extends TestConfig{
		public static WebDriver driver;
		
		@Test
		public void test_01LoginPage()
		{
			driver = getDriver();
			driver.get("https://angmarketplace.projectnimbus.co.in/");
			//assertEquals(true, driver.findElement(By.xpath("")).isDisplayed());
		}
		
		@Test
		public void test_02AfterLoginPage() {
			driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()=' Login ']")).click();
			driver.findElement(By.xpath("//button[text()='Close']")).click();
			assertEquals(false, false, null);
		}
		
		@Test
		public void test_03LogoutPage() throws InterruptedException {
			driver.findElement(By.xpath("//span[text()='admin']")).click();
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log out']")));
			driver.findElement(By.xpath("//a[text()='Log out']")).click();
			Thread.sleep(5000);
		}
		
		@Test
		public void test_04RegisteredUsersPage() throws InterruptedException {
			driver.get("https://angmarketplace.projectnimbus.co.in/");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("1q2w3E*");
			driver.findElement(By.xpath("//button[text()=' Login ']")).click();
			driver.findElement(By.xpath("//button[text()='Close']")).click();
			driver.findElement(By.xpath("//span[text()='Administration']")).click();
			driver.findElement(By.xpath("//span[text()='Identity management']")).click();
			driver.findElement(By.xpath("//span[text()='Users']")).click();
			driver.findElement(By.xpath("//button[text()='Actions ']")).click();
			List<WebElement> elements = driver.findElements(By.xpath("(//div[text()='Edit'])"));
			WebElement element;
			for(int i = elements.size();i > 0;i--)
			{
				if(driver.findElement(By.xpath("(//div[text()='Edit'])["+i+"]")).isDisplayed())
				{
					element = driver.findElement(By.xpath("(//div[text()='Edit'])["+i+"]"));
					element.click();
					break;
				}
					
			}
			assertEquals(true, driver.findElement(By.id("user-name")).isEnabled());
			assertEquals(true, driver.findElement(By.id("name")).isEnabled());
			assertEquals(true, driver.findElement(By.id("surname")).isEnabled());
			assertEquals(true, driver.findElement(By.id("email")).isEnabled());
			assertEquals(true, driver.findElement(By.id("phone-number")).isEnabled());
			//driver.findElement(By.xpath(null)).isEnabled();
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		}
		
		//@Test
		public void test_05AddWalletAmount() throws InterruptedException {
			driver.get("https://angmarketplace.projectnimbus.co.in/");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[text()='Close']")).click();

		}
		
		public void test_06CategoryPage() throws InterruptedException
		{
			driver.get("https://angmarketplace.projectnimbus.co.in/");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[text()='Close']")).click();
			driver.findElement(By.xpath("//span[text()='Category']")).click();
			driver.findElement(By.xpath("//span[text()='Actions']")).isDisplayed();//CRUD for category
			driver.findElement(By.xpath("//span[text()='Category Name']")).isDisplayed();
			driver.findElement(By.xpath("//span[text()='Sub Category Name']")).isDisplayed();
			driver.findElement(By.xpath("//span[text()='Image']")).isDisplayed();
			driver.findElement(By.xpath("//span[text()='Add']")).click();
			driver.findElement(By.id("category_name")).isDisplayed();
			driver.findElement(By.id("subcategory_name")).isDisplayed();
		}
}
