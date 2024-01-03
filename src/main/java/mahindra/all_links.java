package mahindra;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.configuration.TestConfig;

public class all_links extends TestConfig {
	public void test01_login() throws InterruptedException {
		driver.get("https://mahindraadmin.antllp.com/");
		driver.get("https://mahindraadmin.antllp.com/");
		driver.findElement(By.xpath("//input[@class=\"form-control ng-untouched ng-pristine ng-invalid\"]"))
				.sendKeys("admin@admin.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[@class='px-4 btn btn-primary']")).click();
		Thread.sleep(2000);

	}

	public void test02_Getlinks() {
		List<WebElement> anchors = driver.findElements(By.tagName("a"));
		System.out.println(anchors.size());
		System.out.println(driver.findElement(By.tagName("a")).getSize());

	}

}
