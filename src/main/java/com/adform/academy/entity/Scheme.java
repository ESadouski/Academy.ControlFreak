package com.adform.academy.entity;

public class Scheme {

    private String name;
    private double version;

    public Scheme(String name, double version) {
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
