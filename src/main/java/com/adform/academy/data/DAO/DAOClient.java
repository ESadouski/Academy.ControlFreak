package com.adform.academy.data.DAO;

import com.adform.academy.data.entity.Scheme;

import java.util.List;

public interface DAOClient {

    public void addScheme(Scheme scheme);

    public Scheme getScheme(String group, String name, String version);

    public Scheme getScheme(String group, String name);

    public List<Scheme> getGroupOfScheme(String groupName);

    public void updateScheme(Scheme scheme);

    public void deleteScheme(Scheme scheme);

}
