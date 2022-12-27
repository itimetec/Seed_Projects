package com.itt.api.util;

public enum RequestParam {
    HELLO("hello"),
    WORLD("world");

    private String requestParam;

    RequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    @Override
    public String toString() {
        return this.requestParam;
    }
}
