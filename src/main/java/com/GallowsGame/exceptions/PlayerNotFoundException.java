package com.GallowsGame.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String s) {
        super(s);
    }
}
