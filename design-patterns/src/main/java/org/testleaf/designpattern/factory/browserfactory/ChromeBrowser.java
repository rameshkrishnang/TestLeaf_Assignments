package org.testleaf.designpattern.factory.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Logger;

public class ChromeBrowser implements Browser {

    private static final Logger log = Logger.getLogger(ChromeBrowser.class.getName());

    @Override
    public WebDriver launchBrowser() {
        log.info("Launching Chrome browser");
        return new ChromeDriver();
    }

    @Override
    public WebDriver launchBrowserWithConfig(BrowserConfig config) {
        log.info("Launching Chrome browser with Config: " + config);
        ChromeOptions chromeOptions = new ChromeOptions();
        configureOptions(chromeOptions, config);
        return new ChromeDriver(chromeOptions);
    }

    private static void configureOptions(Object options, BrowserConfig config) {
        if (options instanceof ChromeOptions) {
            ChromeOptions chromeOptions = (ChromeOptions) options;
            if (config.isHeadless()) {
                chromeOptions.addArguments("--headless");
            }
            if (config.isMaximized()) {
                chromeOptions.addArguments("--start-maximized");
            }
            if (config.getWindowSize() != null) {
                chromeOptions.addArguments("--window-size=" + config.getWindowSize());
            }
            if (config.isAcceptInsecureCerts()) {
                chromeOptions.setAcceptInsecureCerts(true);
            }
        }
    }

}
