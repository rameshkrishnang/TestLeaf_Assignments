package org.testleaf.designpattern.objectpool.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedList;
import java.util.Queue;

public class WebDriverPool {

    private final Queue<WebDriver> driverPool = new LinkedList<>();

    private WebDriver createDriver() {
        WebDriver driver = new ChromeDriver();
        System.out.println(driver.hashCode());
        return driver;
    }

    public WebDriver getDriver() {
        if (!driverPool.isEmpty()) {
            return driverPool.poll();
        }else{
            return createDriver();
        }
    }

    public void releaseDriver(WebDriver driver) {
        if (driver != null) {
            driverPool.offer(driver);
        }
    }

    public void closeBrowser() {
        for (WebDriver driver : driverPool) {
            driver.quit();
        }
        driverPool.clear();
    }
}
