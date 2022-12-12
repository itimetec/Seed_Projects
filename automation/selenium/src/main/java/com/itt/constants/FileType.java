package com.itt.constants;

public enum FileType {

    CSV(".csv"),
    JSON(".json"),
    XLSX(".xlsx"),
    XML(".xml"),
    XLS(".xls"),
    YAML(".yml");

    private String name;

    FileType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}