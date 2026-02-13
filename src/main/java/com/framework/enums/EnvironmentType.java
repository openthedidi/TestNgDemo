package com.framework.enums;

import com.framework.exceptions.EnvironmentNotSupportedException;

import java.util.Arrays;

public enum EnvironmentType {
    DEV,
    SIT,
    PROD;

    public static EnvironmentType fromString(String env) {
        return Arrays.stream(values())
                .filter(e -> e.name().equalsIgnoreCase(env))
                .findFirst()
                .orElseThrow(() -> new EnvironmentNotSupportedException(
                        "Environment not supported: " + env
                                + ". Supported environments: " + Arrays.toString(values())));
    }
}
