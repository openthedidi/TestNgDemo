package com.framework.listeners;

import com.framework.config.ConfigManager;
import com.framework.utils.LogUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * 用來設定retry機制
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    private int getMaxRetryCount() {
        return ConfigManager.getInstance().getIntProperty("retry.max.count", 1);
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < getMaxRetryCount()) {
            retryCount++;
            LogUtils.warn("Retrying test: " + result.getMethod().getMethodName()
                    + " (attempt " + retryCount + "/" + getMaxRetryCount() + ")");
            return true;
        }
        return false;
    }
}
