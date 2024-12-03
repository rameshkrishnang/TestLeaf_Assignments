package org.testleaf.designpattern.objectpoolwithfactory;

import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.factory.browserfactory.BrowserConfig;
import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class DriverPoolWithBrowserConfig {

    private final ConcurrentMap<BrowserType, ConcurrentMap<BrowserConfig, ConcurrentLinkedQueue<WebDriver>>> driverPool;

    public DriverPoolWithBrowserConfig() {
        driverPool = new ConcurrentHashMap<>();
        for (BrowserType browserType : BrowserType.values()) {
            driverPool.put(browserType, new ConcurrentHashMap<>());
        }
    }

    private WebDriver createDriver(BrowserType browserType, BrowserConfig config) {
        WebDriver driver = BrowserFactory.launchBrowserWithUrl(browserType, config);
        System.out.println("Created new driver for " + browserType + ": " + driver.hashCode());
        return driver;
    }

    public WebDriver getDriver(BrowserType browserType, BrowserConfig config) {
        ConcurrentMap<BrowserConfig, ConcurrentLinkedQueue<WebDriver>> configPool = driverPool.get(browserType);
        ConcurrentLinkedQueue<WebDriver> pool = configPool.computeIfAbsent(config, k -> new ConcurrentLinkedQueue<>());

        WebDriver driver = pool.poll();
        if (driver == null) {
            driver = createDriver(browserType, config);
        }
        return driver;
    }

    public void releaseDriver(BrowserType browserType, BrowserConfig config, WebDriver driver) {
        if (driver != null) {
            ConcurrentMap<BrowserConfig, ConcurrentLinkedQueue<WebDriver>> configPool = driverPool.get(browserType);
            ConcurrentLinkedQueue<WebDriver> pool = configPool.get(config);
            pool.offer(driver);
        }
    }

    public void closeBrowsers() {
        driverPool.forEach((browserType, configMap) -> {
            configMap.forEach((config, driverQueue) -> {
                for (WebDriver driver : driverQueue) {
                    if (driver != null) {
                        driver.quit();
                    }
                }
                driverQueue.clear();
            });
            configMap.clear();
        });
        driverPool.clear();
    }
}
