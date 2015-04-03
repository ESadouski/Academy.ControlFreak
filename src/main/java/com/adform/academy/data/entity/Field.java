package com.adform.academy.data.entity;

public class Field {

    private String name;
    private String pattern;

    public Field(String name, String pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Field field = (Field) o;
        if (!name.equals(field.name)) {
            return false;
        }
        if (!pattern.equals(field.pattern)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pattern.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Field: " + "name= " + name + ", pattern= " + pattern;
    }
}
