package com.adform.academy.data.entity;

import java.io.Serializable;
import java.util.List;

public class Scheme implements Serializable{

    private static final long serialVersionUID = 1L;

    private String group;
    private String name;
    private int version;
    private List<Field> fields;

    public Scheme(String group, String name, int version) {
        this.group = group;
        this.name = name;
        this.version = version;
    }

    public Scheme(String group, String name, int version, List<Field> fields) {
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
        final Scheme scheme = (Scheme) o;
        if (!name.equals(scheme.name)) {
            return false;
        }
        if (!group.equals(scheme.group)) {
            return false;
        }
        if (version != scheme.version) {
            return false;
        }
        if (!fields.equals(scheme.fields)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = group.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + version;
        result = 31 * result + fields.hashCode();
        return result;
    }
}
