package com.framework.exceptions;

public class EnvironmentNotSupportedException extends FrameworkException {

    public EnvironmentNotSupportedException(String message) {
        super(message);
    }

    public EnvironmentNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
