package org.testleaf.factory.designpattern.objectpoolwithfactory;

import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.objectpoolwithfactory.DriverPool;

public class DriverPoolTest {

    private static DriverPool pool;
    private static final BrowserType browserType = BrowserType.CHROME;

    public static void main(String[] args) {
        pool = new DriverPool();
        test1();
        test2();
        pool.closeBrowsers();
    }

    public static void test1() {
        launchBrowserWithUrl();
    }

    public static void test2() {
        launchBrowserWithUrl();
    }

    private static void launchBrowserWithUrl() {
        WebDriver driver = pool.getDriver(browserType);
        BrowserFactory.launchUrl(driver);
        pool.releaseDriver(browserType, driver);
    }

}