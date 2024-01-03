package selenium.configuration;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCore {
	
	public static WebDriver driver;

	public static WebDriver BrowserSelection(String broserName,boolean location) {
		if(location) {
			try {
				Runtime.getRuntime().exec("cmd /c start start_docker.bat");
				sleep(15000L);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			//System.out.println(desiredCapabilities.getBrowserName());
			desiredCapabilities.setBrowserName(broserName);
			URL url=null;
			try {
				url = new URL("http://localhost:4444/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(url,desiredCapabilities);
			return driver;
		}else {
			
		switch (broserName) {
		  case "edge":
				System.setProperty("webdriver.edge.verboseLogging", "true");  
			    driver = new EdgeDriver();
			    break;
		  case "firefox":
			  	WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			    break;
		  case "ie":
			  	WebDriverManager.iedriver().setup();
			  	driver = new InternetExplorerDriver();
			    break;
		  default:
			  	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\Web_automation\\src\\main\\resources\\chromedriver.exe");  
			    
			  	ChromeOptions options = new ChromeOptions();
			  	options.addArguments("--incognito");
			  	driver = new ChromeDriver(options);
		}
		
		
		return driver;
		}
		
	}
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
