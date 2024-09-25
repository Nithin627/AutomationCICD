package com.nithin.resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\resourses\\index.html";
//		Extentreports and Extent Spark reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nithin");
		extent.setSystemInfo("OS", "Windows 11");

		return extent;
	}

}
