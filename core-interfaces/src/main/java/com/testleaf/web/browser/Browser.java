package com.testleaf.web.browser;

import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Element;
import com.testleaf.web.element.Link;

public interface Browser {

    /**
     * Navigates to the specified URL in the browser.
     *
     * @param url the URL to navigate to.
     */
    void navigateTo(String url);

    /**
     * Maximizes the browser window.
     */
    void maximize();

    /**
     * Closes the browser and ends the session.
     */
    void closeBrowser();

    /**
     * Closes the browser tabs and ends the session.
     */
    void quitBrowser();

    /**
     * Retrieves the title of the current web page.
     *
     * @return the title of the current web page as a String.
     */
    String getTitle();

    /**
     * Locates a generic web element on the page using the specified locator type and value.
     *
     * @param locatorType the type of locator (e.g., ID, XPath, CSS).
     * @param element     the locator value to find the element.
     * @return an instance of the `Element` class representing the located element.
     */
    Element locateElement(LocatorType locatorType, String element);

    /**
     * Locates an editable web element (e.g., input fields or text areas) on the page
     * using the specified locator type and value.
     *
     * @param locatorType the type of locator (e.g., ID, XPath, CSS).
     * @param element     the locator value to find the editable element.
     * @return an instance of the `Edit` class representing the located editable element.
     */
    Edit locateEdit(LocatorType locatorType, String element);

    /**
     * Locates a button element on the page using the specified locator type and value.
     *
     * @param locatorType the type of locator (e.g., ID, XPath, CSS).
     * @param element     the locator value to find the button.
     * @return an instance of the `Button` class representing the located button element.
     */
    Button locateButton(LocatorType locatorType, String element);

    /**
     * Locates a link element on the page using the specified locator type and value.
     *
     * @param locatorType the type of locator (e.g., ID, XPath, CSS).
     * @param element     the locator value to find the link.
     * @return an instance of the `Link` class representing the located link element.
     */
    Link locateLink(LocatorType locatorType, String element);
}
