package org.testleaf.designpattern.factory.browserfactory;


import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public static WebDriver driver;

    public static WebDriver launchBrowserWithUrl(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                driver = new ChromeBrowser().launchBrowser();
                return driver;
            case EDGE:
                driver = new EdgeBrowser().launchBrowser();
                return driver;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
    }

    public static WebDriver launchBrowserWithUrl(BrowserType browserType, BrowserConfig config) {
        switch (browserType) {
            case CHROME:
                driver = new ChromeBrowser().launchBrowserWithConfig(config);
                return driver;
            case EDGE:
                driver = new EdgeBrowser().launchBrowserWithConfig(config);
                return driver;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
    }

    public static void launchUrl(WebDriver driver) {
        BrowserUtility browserUtility = new BrowserUtility(driver);
        browserUtility.implicitWait();
        browserUtility.maximizeBrowser();
        browserUtility.launchUrl();
    }

    public static void closeBrowser(WebDriver driver) {
        BrowserUtility browserUtility = new BrowserUtility(driver);
        browserUtility.closeBrowser();
    }
}