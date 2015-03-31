package com.adform.academy.data.entity;

public class Field {

    private String name;
    private String type;
    private String pattern;

    public Field(String name, String type, String pattern) {
        this.name = name;
        this.type = type;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
