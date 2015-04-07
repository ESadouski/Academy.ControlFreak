package com.adform.academy.data.entity;

import java.util.List;

public class Scheme {

    private String group;
    private String name;
    private int version;
    private List<Field> fields;

    public Scheme(String group, String name, int version, List fields) {
        this.group = group;
        this.name = name;
        this.version = version;
        this.fields = fields;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public List getFields() {
        return fields;
    }

    public Field getField(int index) {
        return fields.get(index);
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
