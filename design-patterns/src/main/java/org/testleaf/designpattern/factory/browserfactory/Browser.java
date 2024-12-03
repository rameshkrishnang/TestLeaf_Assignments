package org.testleaf.designpattern.factory.browserfactory;

import org.openqa.selenium.WebDriver;

public interface Browser {
    WebDriver launchBrowser();
    WebDriver launchBrowserWithConfig(BrowserConfig config);
}