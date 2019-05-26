package com.cedaniel200.practice.exception;

import java.io.IOException;

public class ServiceNotAvailableException extends IOException {

    public ServiceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
