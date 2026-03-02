package com.opencart.demo.reportlistner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("./reports/ExtentReport.html");

            spark.config().setReportName("Playwright Automation Report");
            spark.config().setDocumentTitle("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 🔥 Dynamic values
            String environment = System.getProperty("environment", "LOCAL");
            String browser = System.getProperty("browser", "edge");
            String os = System.getProperty("os.name");
            String javaVersion = System.getProperty("java.version");

            extent.setSystemInfo("Environment", environment);
            extent.setSystemInfo("Browser", browser);
            extent.setSystemInfo("Operating System", os);
            extent.setSystemInfo("Java Version", javaVersion);
            extent.setSystemInfo("Framework", "Playwright + TestNG");
        }

        return extent;
    }
}