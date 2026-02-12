package com.framework.enums;

import com.framework.exceptions.BrowserNotSupportedException;

import java.util.Arrays;

public enum BrowserType {
    CHROME,
    EDGE,
    FIREFOX;

    public static BrowserType fromString(String browser) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(browser))
                .findFirst()
                .orElseThrow(() -> new BrowserNotSupportedException(
                        "Browser not supported: " + browser
                                + ". Supported browsers: " + Arrays.toString(values())));
    }
}
