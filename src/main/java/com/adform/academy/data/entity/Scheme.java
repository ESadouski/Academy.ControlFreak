package com.adform.academy.data.entity;

public class Scheme {

    private String name;
    private int version;
    private Field[] fields;

    public Scheme(String name, int version, Field[] fields) {
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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
