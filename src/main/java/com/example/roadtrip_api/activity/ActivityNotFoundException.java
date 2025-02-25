package com.example.roadtrip_api.activity;

import java.util.NoSuchElementException;

public class ActivityNotFoundException extends NoSuchElementException {
    public ActivityNotFoundException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
