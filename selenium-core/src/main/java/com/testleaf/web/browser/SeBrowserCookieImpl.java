package com.testleaf.web.browser;

import org.openqa.selenium.Cookie;

import java.util.Date;

public class SeBrowserCookieImpl implements BrowserCookie {

    private final Cookie seleniumCookie;

    public SeBrowserCookieImpl(Cookie seleniumCookie) {
        this.seleniumCookie = seleniumCookie;
    }

    public SeBrowserCookieImpl(String name, String value, String domain, String path, boolean isSecure, boolean isHttpOnly, long expiry) {
        this.seleniumCookie = new Cookie.Builder(name, value)
                .domain(domain)
                .path(path)
                .isSecure(isSecure)
                .isHttpOnly(isHttpOnly)
                .expiresOn(expiry > 0 ? new Date(expiry) : null)
                .build();
    }

    public Cookie getSeleniumCookie() {
        return seleniumCookie;
    }

    @Override
    public String getName() {
        return seleniumCookie.getName();
    }

    @Override
    public String getValue() {
        return seleniumCookie.getValue();
    }

    @Override
    public String getDomain() {
        return seleniumCookie.getDomain();
    }

    @Override
    public String getPath() {
        return seleniumCookie.getPath();
    }

    @Override
    public boolean isSecure() {
        return seleniumCookie.isSecure();
    }

    @Override
    public boolean isHttpOnly() {
        return seleniumCookie.isHttpOnly();
    }

    @Override
    public long getExpiry() {
        return seleniumCookie.getExpiry() != null ? seleniumCookie.getExpiry().getTime() : -1;
    }
}
