package com.adform.academy.data.dao;

import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;

public interface DAOClient {

    public void addScheme(Scheme scheme);

    public Scheme getScheme(String group, String name, int version);

//    public Scheme getScheme(String group, String name);

    public Group getGroupOfScheme(String groupName);

    public Group getGroupOfScheme(Scheme scheme);

    public void updateScheme(Scheme scheme);

    public void deleteScheme(Scheme scheme);

}
