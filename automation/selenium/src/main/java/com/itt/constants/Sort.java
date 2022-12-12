package com.itt.constants;

public enum Sort {

    ASCENDING("ascending"),
    DESCENDING("descending");

    private String order;

    Sort(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.order;
    }
}
