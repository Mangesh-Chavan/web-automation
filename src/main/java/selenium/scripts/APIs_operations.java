package selenium.scripts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;

public class APIs_operations extends TestConfig {

	public static String path;

	@Test
	public void apis_collection() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Test");
		path = ".\\reports\\API reports.xlsx"; // api
		new Excel_operations(path);
		driver.get("https://mahindraapi.antllp.com/swagger/index.html"); // Hiting url
		List<WebElement> tags = driver.findElements(By.xpath("//div/h3/a/span")); // Getting APIs tags(Heading) Auth,
																					// Basic directory...
		Excel_operations.setCellData("Mahindra", 0, 0, "Function");
		Excel_operations.setCellData("Mahindra", 0, 1, "Api Name"); // Book...
		for (WebElement tag : tags) {
			String tag_name = tag.getText();
			int rownum = Excel_operations.getlastrow();
			Excel_operations.setCellData("Mahindra", rownum + 1, 0, tag_name);
			// System.out.println("\n" + tag_name);
			int apies = driver
					.findElements(By.xpath(
							"//span[text()='" + tag_name + "']/parent::a/parent::h3/following-sibling::div/div/span"))
					.size(); // Getting no. of methods- Get, Put..
			for (int index = 1; index <= apies; index++) {
				WebElement api_name = driver.findElement(By.xpath(
						"//span[text()='" + tag_name + "']/following::span[contains(text(),'api')][" + index + "]")); // :extract
				rownum = Excel_operations.getlastrow();
				Excel_operations.setCellData("Mahindra", rownum + 1, 1, api_name.getText());
			}
		}
	}
}

