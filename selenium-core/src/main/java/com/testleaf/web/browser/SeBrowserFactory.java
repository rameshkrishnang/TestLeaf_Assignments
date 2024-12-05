package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public class SeBrowserFactory implements BrowserFactory {

    private static final WebDriverPoolCapabilitiesFactory poolFactory = new WebDriverPoolCapabilitiesFactory(new SeWebDriverFactory());

    public Browser createBrowser(BrowserType browserType) {
        Capabilities capabilities = new MutableCapabilities();
        WebDriver driver = poolFactory.getDriver(browserType, capabilities);
        return new PooledSeBrowserImpl(driver, poolFactory, browserType, capabilities);
    }
}