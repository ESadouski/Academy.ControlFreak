package com.adform.academy.data.entity;

public class Scheme {

    private String name;
    private double version;
    private Field[] fields;

    public Scheme(String name, double version, Field[] fields) {
        this.name = name;
        this.version = version;
        this.fields = fields;
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

    public Field[] getFields() {
        return fields;
    }

    public Field getField(int index) {
        return fields[index];
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }
}
