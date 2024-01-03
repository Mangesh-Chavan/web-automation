package selenium.scripts.khal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import selenium.configuration.TestConfig;
import selenium.scripts.Excel_operations;

public class Link_check extends TestConfig {
	
		public static String path;
		public static String url;
		public static int rowcount;
		
		@Test
		public void Broken_links() throws InterruptedException, IOException{
				extentTest = extentReports.createTest("Khal broken link");
				path = ".\\reports\\website_testing.xlsx";
				url = properties.getProperty("url");
				driver.get(url);
				driver.findElement(By.xpath("//span[text()='Sign in']/parent::div")).click();
				driver.findElement(By.xpath("//input[@name='username']")).sendKeys("mangeshchavan02");
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Mangu@1234");
				driver.findElement(By.xpath("//input[@value='Login']")).click();
				String sheetname = url.split("/")[2];
				System.out.println("this is name of sheet" +sheetname);
				new Excel_operations(path);
				rowcount = 0;
				Excel_operations.setCellData(sheetname, 0, 0, "Link");
				Excel_operations.setCellData(sheetname, 0, 1, "Is Broken");
				Excel_operations.setCellData(sheetname, 0, 2, "RT");//1
				sleep(2000);
				List<WebElement> anchor_elements = driver.findElements(By.tagName("a"));
				System.out.println("Number of Links "+anchor_elements.size());
				for(WebElement element:anchor_elements)
				{
						if(!(element.getAttribute("href")==null))
						{
							if(element.getAttribute("href").contains("https://"))
							{
								String urlString = element.getAttribute("href");
								rowcount++;
								Excel_operations.setCellData(sheetname, rowcount , 0, urlString);
								System.out.println(rowcount);
								URL Check_link = new URL(urlString);
								HttpsURLConnection httpconn = (HttpsURLConnection) Check_link.openConnection();
								Thread.sleep(2000);
								httpconn.connect();
								int rescode = httpconn.getResponseCode();
								double Conntime = httpconn.getReadTimeout();
								if(rescode>=400)
									{
										Excel_operations.setCellData(sheetname, rowcount , 1, "True");
										Excel_operations.setCellData(sheetname, rowcount , 2, "N/A");//2
									}
								else
									{
										Excel_operations.setCellData(sheetname, rowcount , 1, "False");
										Excel_operations.setCellData(sheetname, rowcount , 2, ""+Conntime+"" );//3
										HttpURLConnection.setFollowRedirects(true);
									}
							}
						}
				}	
				System.out.println("End of Operation");
		}
}