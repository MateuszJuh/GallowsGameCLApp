package com.GallowsGame.exceptions;

public class APIConnectionError extends RuntimeException {
    public APIConnectionError(String s) {
        super(s);
    }
}
