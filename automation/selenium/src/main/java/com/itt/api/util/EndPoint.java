package com.itt.api.util;

public enum EndPoint {
    USER_END_POINT("api/users");

    private String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return this.endpoint;
    }

}
