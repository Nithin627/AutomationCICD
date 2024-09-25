package com.nithin.testComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nithin.resourses.ExtentReportTestNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportTestNG.getReportObject();

//	To run the tests parallel
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
//        System.out.println("Test started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // It will assign unique thread Id of ErrorValidation Test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//        System.out.println("Test passed: " + result.getName());
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//        System.out.println("Test failed: " + result.getName());
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {

			e.printStackTrace();
		}

//		Take a screen shot
		String filePath = null;
		;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Attach to a report
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
//        System.out.println("Test skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        System.out.println("Test failed but within success percentage: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
//        System.out.println("Tests started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
//        System.out.println("Tests finished: " + context.getName());
		extent.flush();
	}

}
