package com.adform.academy.data.entity;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable{

    private String name;
    private List<Scheme> list;

    public Group(String name, List<Scheme> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Scheme> getList() {
        return list;
    }

    public void setList(List<Scheme> list) {
        this.list = list;
    }

    public void addScheme(Scheme scheme){
        System.out.println();
        list.add(list.size(), scheme);
    }
}
