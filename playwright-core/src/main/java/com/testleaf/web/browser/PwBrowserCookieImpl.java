package com.testleaf.web.browser;

import com.microsoft.playwright.options.Cookie;

public class PwBrowserCookieImpl implements BrowserCookie {

    private final Cookie playwrightCookie;

    public PwBrowserCookieImpl(Cookie playwrightCookie) {
        this.playwrightCookie = playwrightCookie;
    }

    public PwBrowserCookieImpl(String name, String value, String domain, String path, boolean isSecure, boolean isHttpOnly, long expiry) {
        this.playwrightCookie = new Cookie(name, value);
        this.playwrightCookie.setDomain(domain);
        this.playwrightCookie.setPath(path);
        this.playwrightCookie.setSecure(isSecure);
        this.playwrightCookie.setHttpOnly(isHttpOnly);
        this.playwrightCookie.setExpires(expiry > 0 ? expiry / 1000 : -1);
    }

    public Cookie getPlaywrightCookie() {
        return playwrightCookie;
    }

    @Override
    public String getName() {
        return playwrightCookie.name;
    }

    @Override
    public String getValue() {
        return playwrightCookie.value;
    }

    @Override
    public String getDomain() {
        return playwrightCookie.domain;
    }

    @Override
    public String getPath() {
        return playwrightCookie.path;
    }

    @Override
    public boolean isSecure() {
        return playwrightCookie.secure;
    }

    @Override
    public boolean isHttpOnly() {
        return playwrightCookie.httpOnly;
    }

    @Override
    public long getExpiry() {
        return playwrightCookie.expires > 0 ? (long) (playwrightCookie.expires * 1000) : -1;
    }

}
