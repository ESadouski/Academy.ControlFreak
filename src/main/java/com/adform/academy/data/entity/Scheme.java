package com.adform.academy.data.entity;

import java.io.Serializable;
import java.util.List;

public class Scheme implements Serializable{

    private Group group;
    private String name;
    private int version;
    private List<Field> fields;

    public Scheme(Group group, String name, int version) {
        this.group = group;
        this.name = name;
        this.version = version;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
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
