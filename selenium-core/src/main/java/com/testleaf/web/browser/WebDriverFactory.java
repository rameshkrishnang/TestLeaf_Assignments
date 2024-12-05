package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {

    /**
     * Creates a WebDriver instance for the specified browser type with the given capabilities.
     *
     * @param browserType   the type of browser for which the WebDriver is to be created
     *                      (e.g., Chrome, Firefox, Edge).
     * @param capabilities  the desired capabilities to configure the WebDriver instance
     *                      (e.g., browser options, platform settings).
     * @return a `WebDriver` instance configured for the specified browser type and capabilities.
     */
    WebDriver createDriver(BrowserType browserType, Capabilities capabilities);
}