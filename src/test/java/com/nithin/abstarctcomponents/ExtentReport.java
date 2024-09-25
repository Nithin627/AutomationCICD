package com.nithin.abstarctcomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	public ExtentReports extent;


	public void config() {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\abstarctcomponents\\index.html";
//		Extentreports and Extent Spark reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nithin");
		extent.setSystemInfo("OS", "Windows 11");

	}


	public void initializedemo() {
		ExtentTest test = extent.createTest("Initial Demo");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		test.fail("Result wrong");
		extent.flush();
	}

}
