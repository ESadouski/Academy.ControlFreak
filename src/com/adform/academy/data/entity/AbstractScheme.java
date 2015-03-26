package com.adform.academy.data.entity;

public abstract class AbstractScheme {

    private String name;
    private double version;

    public AbstractScheme(String name, double version) {
        this.name = name;
        this.version = version;
    }
}
