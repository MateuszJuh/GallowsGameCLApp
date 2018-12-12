package com.GallowsGame.models;

public enum RequestMethod {
    POST("POST"),GET("GET"),PUT("PUT"),DELETE("DELETE");

    private String method;

    RequestMethod(String method) {
        this.method = method;
    }

    public String get(){return method;}
}
