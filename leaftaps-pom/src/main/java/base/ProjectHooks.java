package base;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.browser.Browser;
import config.ConfigurationManager;
import config.RetryContext;
import org.testng.annotations.*;

public class ProjectHooks {

    public String leadID;
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<RetryContext> retryContext = ThreadLocal.withInitial(RetryContext::new);

    public static Browser getBrowser() {
        return browser.get();
    }

    public static RetryContext getRetryContext() {
        return retryContext.get();
    }

    @BeforeMethod
    @Parameters({"browserEngine", "browserType"})
    public void preCondition(@Optional("SELENIUM") String browserEngineParam, @Optional("CHROME") String browserTypeParam) {
        BrowserTestEngine browserEngine;
        BrowserType browserType;

        if (RetryContext.isRetry()) {
            // Switch to a different browser engine and type for retries
            int retryAttempt = RetryContext.getRetryAttempt();
            browserEngine = (retryAttempt % 2 == 0) ? BrowserTestEngine.PLAYWRIGHT : BrowserTestEngine.SELENIUM;
            browserType = (retryAttempt % 2 == 0) ? BrowserType.EDGE : BrowserType.FIREFOX;
            System.out.println("Retry detected. Switching to: " + browserEngine + " and " + browserType);
        } else {
            // Use default values for the first attempt
            browserEngine = BrowserTestEngine.valueOf(browserEngineParam.toUpperCase());
            browserType = BrowserType.valueOf(browserTypeParam.toUpperCase());
        }

        // Get the browser instance from DriverManager
        Browser br = DriverManager.getBrowser(browserEngine, browserType);
        browser.set(br);

        // Navigate to the URL and maximize
        getBrowser().navigateTo(ConfigurationManager.configuration().url());
        getBrowser().maximize();
    }

    @AfterMethod
    public void postCondition() {
        // Close the browser
        getBrowser().closeBrowser();
    }

    @AfterTest
    public void postTestCondition() {
        // Close the browser
        getBrowser().quitBrowser();
    }

}
