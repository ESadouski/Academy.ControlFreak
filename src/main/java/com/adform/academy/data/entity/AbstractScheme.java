package com.adform.academy.data.entity;

public abstract class AbstractScheme {

    private String name;
    private double version;

    public AbstractScheme(String name, double version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}
