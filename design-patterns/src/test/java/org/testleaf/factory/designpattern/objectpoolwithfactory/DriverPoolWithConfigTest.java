package org.testleaf.factory.designpattern.objectpoolwithfactory;

import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.factory.browserfactory.BrowserConfig;
import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;
import org.testleaf.designpattern.objectpoolwithfactory.DriverPoolWithBrowserConfig;

public class DriverPoolWithConfigTest {

    private static DriverPoolWithBrowserConfig pool;
    private static final BrowserType browserType = BrowserType.EDGE;
    private static final BrowserConfig config1 = new BrowserConfig(false, true, "1920x1080", true);
    private static final BrowserConfig config2 = new BrowserConfig(true, true, "1366x768", false);

    public static void main(String[] args) {
        pool = new DriverPoolWithBrowserConfig();
        test1();
        test2();
        test3();
        pool.closeBrowsers();
    }

    public static void test1() {
        launchBrowserWithUrl(config1);
    }

    public static void test2() {
        launchBrowserWithUrl(config2);
    }

    public static void test3() {
        launchBrowserWithUrl(config1);
    }

    private static void launchBrowserWithUrl(BrowserConfig config) {
        WebDriver driver = pool.getDriver(browserType, config);
        BrowserFactory.launchUrl(driver);
        pool.releaseDriver(browserType, config, driver);
    }
}