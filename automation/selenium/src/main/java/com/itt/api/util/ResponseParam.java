package com.itt.api.util;

public enum ResponseParam {
    HELLO("hello"),
    WORLD("world");

    private String responseParam;

    ResponseParam(String responseParam) {
        this.responseParam = responseParam;
    }

    @Override
    public String toString() {
        return this.responseParam;
    }
}
