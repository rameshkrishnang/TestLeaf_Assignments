package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;

public interface BrowserFactory {

    /**
     * Creates a browser instance based on the specified browser type.
     *
     * @param browserType the type of browser to create (e.g., Chrome, Firefox, Edge).
     * @return a `Browser` instance representing the requested browser type.
     */
    Browser createBrowser(BrowserType browserType);
}
