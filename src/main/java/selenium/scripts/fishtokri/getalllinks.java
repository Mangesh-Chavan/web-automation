package selenium.scripts.fishtokri;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class getalllinks extends TestConfig {

	@Test
	public void testlink() {
		extentTest = extentReports.createTest("check URL");
		driver.get("https://www.amazon.in");
		List<WebElement> links = driver.findElements(By.tagName("A"));
		System.out.println("Number of Links in the Page is " + links.size());

		for (int i = 1; i <= links.size(); i = i + 1) {
			System.out.println("Name of links " + i + links.get(i).getText());
			driver.close();
		}

	}
}
