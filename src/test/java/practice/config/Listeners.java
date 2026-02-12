package practice.config;

import org.testng.ITestListener;

public class Listeners implements ITestListener {
    @Override
    public void onTestStart(org.testng.ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(org.testng.ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println("Suite started: " + context.getName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println("Suite finished: " + context.getName());
    }

    @Override
    public void onTestFailedWithTimeout(org.testng.ITestResult result) {
        // Not implemented
    }
}
