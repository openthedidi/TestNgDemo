package com.framework.exceptions;

public class PropertyFileNotFoundException extends FrameworkException {

    public PropertyFileNotFoundException(String message) {
        super(message);
    }

    public PropertyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
