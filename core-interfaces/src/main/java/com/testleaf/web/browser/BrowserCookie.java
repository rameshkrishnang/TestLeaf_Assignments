package com.testleaf.web.browser;

public interface BrowserCookie {
    String getName();

    String getValue();

    String getDomain();

    String getPath();

    boolean isSecure();

    boolean isHttpOnly();

    long getExpiry();
}
