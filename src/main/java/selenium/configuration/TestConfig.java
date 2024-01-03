package selenium.configuration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestConfig extends TestCore {

	public static WebDriver driver;

	JavascriptExecutor jsExecutor;

	public static ExtentReports extentReports;

	public static ExtentSparkReporter extentSparkReporter;

	public static ExtentTest extentTest;

	boolean location = false;

	public static FileReader fileToBeRead;

	public static Properties properties;

	@BeforeTest
	public WebDriver init() throws IOException {
		// No need to setup for chromepath
		String path = System.getProperty("user.dir") + "./src/test/resources/configfiles/properties";
		fileToBeRead = new FileReader(path);
		properties = new Properties();
		properties.load(fileToBeRead);
		driver = TestCore.BrowserSelection(properties.getProperty("browser"), location);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "./reports/report.html");
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setDocumentTitle("MyReport");
		extentReports.attachReporter(extentSparkReporter);
		return driver;
	}

	// @AfterMethod
	public void teardown(ITestResult result) throws IOException {
		extentTest.assignCategory(result.getTestClass().getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, result.getName() + " Failed");// to add name in test case
			// extentTest.log(Status.FAIL, result.getThrowable().getLocalizedMessage());//to
			// add exception in report

			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath, "Captured Result");

			// String actualString = getActual(result.getName()+".png");
			// extentTest.addScreenCaptureFromPath(actualString,"Baseline");
			extentTest.info(result.getThrowable().getLocalizedMessage());
			// extentTest.info(MediaEntityBuilder.createScreenCaptureFromPath(actualString,
			// "Baseline").build());

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, result.getName() + " Skipped");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			// System.out.println(result.getName()+".png");

			extentTest.log(Status.PASS, result.getName() + " Passed");

			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath, "Captured Result");
		}
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String fileName = screenshotName + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File fileSource = tScreenshot.getScreenshotAs(OutputType.FILE);

		// FailedTestsScreenshots
		String directoryPath = System.getProperty("user.dir") + "./reports/screenshots/" + fileName + ".png";
		File fileDestination = new File(directoryPath);
		FileUtils.copyFile(fileSource, fileDestination);
		String destinationPath = "../reports/screenshots/" + fileName + ".png";
		return destinationPath;

	}

	public static String getActual(String fileName) throws IOException {
		String directoryPath = System.getProperty("user.dir") + "./reports/design";
		File directory = new File(directoryPath);
		String destinationPath = "";
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files) {
				if (fileName.contains(file.getName())) {
					destinationPath = "../reports/design/" + file.getName();

				}
			}
		}
		return destinationPath;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public String getFileName(WebDriver driver) {
		String mainwindow = driver.getWindowHandle();
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.open()");
		for (String winHandle : driver.getWindowHandles()) {

			driver.switchTo().window(winHandle);
		}
		driver.get("chrome://downloads");

		sleep(2000);

		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("downloads-manager")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		sleep(3000);
		// get the latest downloaded file name
		String fileName = "";
		try {
			fileName = (String) js.executeScript(
					"return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");

		} catch (Exception e) {

		}

//		WebElement downloads_manager= driver.findElement(By.tagName("downloads-manager"));//shadow host
//		WebElement Shadow_root = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", downloads_manager);
//		WebElement maincontainer = Shadow_root.findElement(By.cssSelector("div#mainContainer"));
//		WebElement downloadsList = maincontainer.findElement(By.cssSelector("iron-list#downloadsList"));
//		WebElement downloads_item = downloadsList.findElement(By.tagName("downloads-item"));
//		WebElement Shadow_root1 = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", downloads_item);
//		WebElement date = Shadow_root1.findElement(By.cssSelector("div#date"));
//		String fileName = date.getText();
		System.out.println("Downloaded File Name: " + fileName);

		driver.close();

		driver.switchTo().window(mainwindow);

		return fileName;
	}

	public static String getPdfContent(String url) {

		URL pdfURL;
		InputStream is;
		BufferedInputStream bis;
		PDDocument doc;
		PDFTextStripper strip;
		String stripText = "";
		try {
			pdfURL = new URL(url);
			is = pdfURL.openStream();

			bis = new BufferedInputStream(is);

			doc = PDDocument.load(bis);

			int pages = doc.getNumberOfPages();

			System.out.println("The Total Number of Pages " + pages);

			strip = new PDFTextStripper();

			strip.setStartPage(1);

			strip.setEndPage(2);

			stripText = strip.getText(doc);

			System.out.println(stripText);

			doc.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stripText;

	}

	public static String getfileContent(File f) {

		String fileContent = "";
		try {
			// Create f1 object of the file to read data
			Scanner dataReader = new Scanner(f);
			while (dataReader.hasNextLine()) {
				String fileData = dataReader.nextLine();
				System.out.println(fileData);
			}
			dataReader.close();
		} catch (Exception exception) {
			System.out.println("Unexcpected error occurred!");
			exception.printStackTrace();
		}
		return fileContent;
	}

	@AfterTest
	public void destroy() {
		extentReports.flush();
		// driver.quit();
		if (location) {
			try {
				Runtime.getRuntime().exec("cmd /c start stop_docker.bat");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
