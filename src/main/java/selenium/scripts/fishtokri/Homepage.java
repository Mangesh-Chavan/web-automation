package selenium.scripts.fishtokri;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class Homepage extends TestConfig{

	JavascriptExecutor js;
	
	WebElement element;
	
	@Test
	public void TEST01_Homepage() {
		extentTest = extentReports.createTest("Homepage");
		driver.get("https://fishtokri.antllp.com");
		try {
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		}catch(Exception e) {}
		assertEquals(driver.getTitle(),"FishTokri","Home page should be visible");
		assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo')]")).isDisplayed(), "Logo should be visible");
		assertTrue(driver.findElement(By.className("location-box")).isDisplayed(), "Location feild should be visible");
		assertTrue(driver.findElement(By.xpath("//span[text()='Categories']")).isDisplayed(), "Category dropdown should be visible");
		assertTrue(driver.findElement(By.xpath("//input[@type='search']")).isDisplayed(), "Search feild should be visible");
		assertTrue(driver.findElement(By.xpath("//h6[text()='Login']")).isDisplayed(), "Login button should be visible");
		assertTrue(driver.findElement(By.xpath("//img[contains(@src,'cart')]")).isDisplayed(), "cart button should be visible");
	}
	
	@Test
	public void TEST02_FindProducThroughCategory() {
		extentTest = extentReports.createTest("Find Product Through Category");
		driver.findElement(By.xpath("//span[text()='Categories']")).click();
		assertTrue(driver.findElement(By.xpath("//ul[@class='category-list']")).isDisplayed(), "Product Categories should be visible");
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//h6[text()='Dry Fish']"))).perform();
		//driver.findElement(By.xpath("//h6[text()='Dry Fish']")).click();
		assertTrue(driver.findElement(By.xpath("//div[@class='list-1']/ul")).isDisplayed(), "Product should be visible");
	}
	
	@Test
	public void TEST03_FindProducThroughSearchBar() {
		driver.findElement(By.xpath("//img[contains(@src,'logo')]")).click();
		extentTest = extentReports.createTest("Find Product Through SearchBar");
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(new CharSequence[] {"Bombil"});
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Bombil')]")).isDisplayed(), "Product should be visible");
		driver.findElement(By.xpath("//img[contains(@src,'logo')]")).click();
		assertFalse(driver.findElement(By.xpath("//div[contains(@class,'displaySearch')]")).isDisplayed(),"Result Should get hide upon clicking outside of searchbar");
	}
	
	@Test
	public void TEST03_FindProductThroughCategoryGrid() {
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.BACK_SPACE);
		extentTest = extentReports.createTest("Find Product Through Category Grid");
		js = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath("//div[contains(@class,'col-xxl-3 col-lg-4')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		assertTrue(element.getAttribute("class").contains("col-lg-3"), "Should be 4 grid category as per Designed");
	}
	
	@Test
	public void TEST04_DynamicProductDetails() {
		extentTest = extentReports.createTest("Dynamic Product Description");
		element = driver.findElement(By.xpath("//h2[text()='Combo Offers']"));
		sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Pieces')]")).getText(), "No. of Pieces: 50-80 Pcs","Every product details should be dynamic for each product");
		
	}
	
	@Test
	public void TEST05_OffersAndCoupons() {
		extentTest = extentReports.createTest("Offers And Coupons");
		element = driver.findElement(By.xpath("//section[@class='footer banner-section']"));
		sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		element = driver.findElement(By.xpath("//img[contains(@src,'egg')]/following::h5"));
		assertTrue(element.getText().contains("Egg"), "Offer should be Relevant to image");
	}
	
	//@Test
	public void TEST06_Keyfeatures() {
		extentTest = extentReports.createTest("Keyfeatures");
		element = driver.findElement(By.xpath("//button[text()=' Read More ']"));
		sleep(2000);
		
	}
	
	
}
