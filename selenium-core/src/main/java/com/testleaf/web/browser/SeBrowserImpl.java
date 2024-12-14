package com.testleaf.web.browser;

import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.*;
import com.testleaf.web.element.decorated.SeButtonLogDecorator;
import com.testleaf.web.element.decorated.SeButtonSnapDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class SeBrowserImpl implements Browser {

    protected WebDriver driver;

    public SeBrowserImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void navigateTo(String url) {
        this.driver.get(url);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Override
    public void maximize() {
        this.driver.manage().window().maximize();
    }

    @Override
    public void closeBrowser() {
        this.driver.close();
    }

    @Override
    public void quitBrowser() {
        this.driver.quit();
    }

    @Override
    public void clickOkOnAlert() {
        this.driver.switchTo().alert().accept();
    }

    private WebElement findElement(LocatorType locatorType, String locator) {
		return switch (locatorType) {
			case ID -> this.driver.findElement(By.id(locator));
			case NAME -> this.driver.findElement(By.name(locator));
			case CLASS -> this.driver.findElement(By.className(locator));
			case LINK_TEXT -> this.driver.findElement(By.linkText(locator));
			case XPATH -> this.driver.findElement(By.xpath(locator));
			default -> throw new IllegalArgumentException("Unsupported Locator Type :" + locatorType);
		};
	}

    @Override
    public Element locateElement(LocatorType locatorType, String element) {
        return new SeElementImpl(findElement(locatorType, element));
    }

    @Override
    public Edit locateEdit(LocatorType locatorType, String element) {
        return new SeEditImpl(findElement(locatorType, element));
    }

    @Override
    public Button locateButton(LocatorType locatorType, String element) {
        //new SeButtonImpl(findElement(locatorType, Element));
        Button button = new SeButtonImpl(findElement(locatorType, element));
        //return new SeButtonLogDecorator(button);
        return new SeButtonSnapDecorator(new SeButtonLogDecorator(button));
    }

    @Override
    public Link locateLink(LocatorType locatorType, String element) {
        return new SeLinkImpl(findElement(locatorType, element));
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void addCookies(Set<BrowserCookie> cookies) {
        for (BrowserCookie browserCookie : cookies) {
            SeBrowserCookieImpl seBrowserCookieImpl = (SeBrowserCookieImpl) browserCookie;
            driver.manage().addCookie(seBrowserCookieImpl.getSeleniumCookie());
        }
    }

    @Override
    public Set<BrowserCookie> getCookies() {
        Set<Cookie> seleniumCookies = driver.manage().getCookies();
        Set<BrowserCookie> browserCookies = new HashSet<>();

        for (Cookie seleniumCookie : seleniumCookies) {
            browserCookies.add(new SeBrowserCookieImpl(seleniumCookie));
        }

        return browserCookies;
    }

    @Override
    public void clearAllCookies() {
        driver.manage().deleteAllCookies();  // Clear all cookies in Selenium
    }
}