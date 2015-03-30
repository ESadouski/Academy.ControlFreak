package com.adform.academy.DAO;



import com.adform.academy.entity.Scheme;

import java.util.List;

public interface DAOClient {

    public void addScheme(String group, Scheme scheme);

    public Scheme getScheme(String group, String name, double version);

    public Scheme getScheme(String group, String name);

    public List<Scheme> getGroupOfScheme(String groupName);

    public void updateScheme(Scheme scheme);

    public void deleteScheme(Scheme scheme);



}
