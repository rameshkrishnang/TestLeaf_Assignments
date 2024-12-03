package org.testleaf.designpattern.factory.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.logging.Logger;

public class EdgeBrowser implements Browser{

    private static final Logger log = Logger.getLogger(EdgeBrowser.class.getName());

   @Override
    public WebDriver launchBrowser() {
        log.info("Launching Edge Browser");
        return new EdgeDriver();
    }

    @Override
    public WebDriver launchBrowserWithConfig(BrowserConfig config) {
        log.info("Launching Edge browser with Config: " + config);
        EdgeOptions edgeOptions = new EdgeOptions();
        configureOptions(edgeOptions, config);
        return new EdgeDriver(edgeOptions);
    }

    private static void configureOptions(Object options, BrowserConfig config) {
        if (options instanceof EdgeOptions) {
            EdgeOptions edgeOptions = (EdgeOptions) options;
            if (config.isHeadless()) {
                edgeOptions.addArguments("--headless");
            }
            if (config.isMaximized()) {
                edgeOptions.addArguments("--start-maximized");
            }
            if (config.getWindowSize() != null) {
                edgeOptions.addArguments("--window-size=" + config.getWindowSize());
            }
            if (config.isAcceptInsecureCerts()) {
                edgeOptions.setAcceptInsecureCerts(true);
            }
        }
    }

}
