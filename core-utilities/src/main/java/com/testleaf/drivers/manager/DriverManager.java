package com.testleaf.drivers.manager;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.web.browser.Browser;
import com.testleaf.web.browser.PwBrowserFactory;
import com.testleaf.web.browser.PwBrowserImpl;
import com.testleaf.web.browser.SeBrowserFactory;

public class DriverManager {

    public static Browser getBrowser(BrowserTestEngine browserEngine, BrowserType browserType) {
        return setupBrowser(browserEngine, browserType);
    }

    public static Browser getBrowserWithAPI(BrowserTestEngine browserEngine, BrowserType browserType) {
        return setupBrowserWithAPI(browserEngine, browserType);
    }

    private static Browser setupBrowser(BrowserTestEngine browserEngine, BrowserType browserType) {
        return switch (browserEngine) {
            case SELENIUM -> new SeBrowserFactory().createBrowser(browserType);
            case PLAYWRIGHT -> new PwBrowserFactory().createBrowser(browserType);
            default -> throw new IllegalArgumentException("Unexpected value: " + browserEngine);
        };
    }

    private static Browser setupBrowserWithAPI(BrowserTestEngine browserEngine, BrowserType browserType) {
        return switch (browserEngine) {
            case SELENIUM -> new SeBrowserAPIImpl(browserType);
            case PLAYWRIGHT -> new PwBrowserImpl(browserType);
            default -> throw new IllegalArgumentException("Unexpected value: " + browserEngine);
        };
    }
}
