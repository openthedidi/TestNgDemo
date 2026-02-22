package com.framework.listeners;

import com.framework.utils.LogUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Log4j2 記錄用
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("========== STARTING: " + getTestName(result) + " ==========");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("========== PASSED: " + getTestName(result)
                + " (duration: " + getDuration(result) + "ms) ==========");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("========== FAILED: " + getTestName(result)
                + " (duration: " + getDuration(result) + "ms) ==========");
        LogUtils.error("Failure reason: ", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("========== SKIPPED: " + getTestName(result) + " ==========");
    }

    private String getTestName(ITestResult result) {
        return result.getTestClass().getName() + "." + result.getMethod().getMethodName();
    }

    private long getDuration(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}
