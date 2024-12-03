package org.testleaf.factory.designpattern.factory.browserfactory;

import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;

public class BrowserTest {

    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.launchBrowserWithUrl(BrowserType.EDGE);
        BrowserFactory.launchUrl(driver);
        BrowserFactory.closeBrowser(driver);

        driver = BrowserFactory.launchBrowserWithUrl(BrowserType.CHROME);
        BrowserFactory.launchUrl(driver);
        BrowserFactory.closeBrowser(driver);
    }

}