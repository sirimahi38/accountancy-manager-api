package com.ca.account.manager.exception;

import org.aspectj.bridge.IMessage;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message) {
        super(message);
    }
}
