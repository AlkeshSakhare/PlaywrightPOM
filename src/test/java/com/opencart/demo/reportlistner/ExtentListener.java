package com.opencart.demo.reportlistner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.opencart.demo.playwrightfactory.PlaywrightFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = PlaywrightFactory.takeScreenshot(result.getMethod().getMethodName());
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
        String path = PlaywrightFactory.takeScreenshot(result.getMethod().getMethodName());
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}