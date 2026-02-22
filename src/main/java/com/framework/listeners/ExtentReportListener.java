package com.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.constants.FrameworkConstants;
import com.framework.driver.DriverManager;
import com.framework.utils.DateUtils;
import com.framework.utils.LogUtils;
import com.framework.utils.ScreenshotUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

/**
 * ExtentReports 測試報告監聽器
 */
public class ExtentReportListener implements ISuiteListener, ITestListener {


    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * ExtentReports 初始化：檔案路徑，browser預設先用chrome
     */
    @Override
    public void onStart(ISuite suite) {
        String reportDir = FrameworkConstants.EXTENT_REPORT_DIR;
        new File(reportDir).mkdirs();

        String reportPath = reportDir + "TestReport_" + DateUtils.getTimestamp() + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName(suite.getName());

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment",
                System.getProperty("environment", "dev"));
        extentReports.setSystemInfo("Browser",
                System.getProperty("browser", "chrome"));

        LogUtils.info("ExtentReports initialized: " + reportPath);
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extentReports != null) {
            extentReports.flush();
            LogUtils.info("ExtentReports flushed");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(
                result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
        extentTest.set(test);
        LogUtils.setExtentLogger(message -> {
            ExtentTest currentTest = extentTest.get();
            if (currentTest != null) {
                currentTest.info(message);
            }
        });
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test PASSED");
        cleanup();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.fail(result.getThrowable());

        // 失敗要截圖
        if (DriverManager.getDriver() != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(
                    result.getMethod().getMethodName());
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    LogUtils.error("Failed to attach screenshot to report", e);
                }
            }
        }
        cleanup();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        test.skip(result.getThrowable());
        extentTest.set(test);
        cleanup();
    }

    private void cleanup() {
        LogUtils.removeExtentLogger();
        extentTest.remove();
    }
}
