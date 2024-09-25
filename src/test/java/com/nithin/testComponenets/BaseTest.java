package com.nithin.testComponenets;

import org.openqa.selenium.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.base.pageobject.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	List<HashMap<String, String>> data;

	public WebDriver InitialiseDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\resourses\\global.properties");
		prop.load(file);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			ChromeOptions option = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}

			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firfox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Enter browser");

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

//		WebDriverManager.chromedriver().setup();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) {

//		read json to string
		try {
			String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

//		convert string to hash map

			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//screenshot//" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir") + "//screenshot//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = InitialiseDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
