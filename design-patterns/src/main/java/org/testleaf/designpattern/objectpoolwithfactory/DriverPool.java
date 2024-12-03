package org.testleaf.designpattern.objectpoolwithfactory;

import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class DriverPool {

    private final ConcurrentMap<BrowserType, ConcurrentLinkedQueue<WebDriver>> driverPool;

    public DriverPool() {
        driverPool = new ConcurrentHashMap<>();
        for (BrowserType browserType : BrowserType.values()) {
            driverPool.put(browserType, new ConcurrentLinkedQueue<>());
        }
    }

    private WebDriver createDriver(BrowserType browserType) {
        WebDriver driver = BrowserFactory.launchBrowserWithUrl(browserType);
        System.out.println("Created new driver for " + browserType + ": " + driver.hashCode());
        return driver;
    }

    public WebDriver getDriver(BrowserType browserType) {
        ConcurrentLinkedQueue<WebDriver> pool = driverPool.get(browserType);
        WebDriver driver = pool.poll();
        if (driver == null) {
            driver = createDriver(browserType);
        }
        return driver;
    }

    public void releaseDriver(BrowserType browserType, WebDriver driver) {
        if (driver != null) {
            driverPool.get(browserType).offer(driver);
        }
    }

    public void closeBrowsers() {
        driverPool.forEach((browserType, driverQueue) -> {
            System.out.println("Closing drivers for: " + browserType);
            for (WebDriver driver : driverQueue) {
                driver.quit();
            }
            driverQueue.clear();
        });
        driverPool.clear();
    }
}
