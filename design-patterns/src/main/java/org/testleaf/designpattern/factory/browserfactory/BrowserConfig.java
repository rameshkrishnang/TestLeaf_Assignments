package org.testleaf.designpattern.factory.browserfactory;

public class BrowserConfig {

    private boolean headless;
    private boolean maximized;
    private String windowSize;
    private boolean acceptInsecureCerts;

    // Constructor
    public BrowserConfig(boolean headless, boolean maximized, String windowSize, boolean acceptInsecureCerts) {
        this.headless = headless;
        this.maximized = maximized;
        this.windowSize = windowSize;
        this.acceptInsecureCerts = acceptInsecureCerts;
    }

    // Getters and Setters
    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

    public String getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(String windowSize) {
        this.windowSize = windowSize;
    }

    public boolean isAcceptInsecureCerts() {
        return acceptInsecureCerts;
    }

    public void setAcceptInsecureCerts(boolean acceptInsecureCerts) {
        this.acceptInsecureCerts = acceptInsecureCerts;
    }

}
