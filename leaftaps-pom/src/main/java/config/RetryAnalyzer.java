package config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Max retries allowed

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            RetryContext.setRetry(true); // Mark that this is a retry
            RetryContext.incrementRetryAttempt();
            return true;
        }
        return false;
    }
}
