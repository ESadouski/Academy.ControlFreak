package com.adform.academy.data.DAO;

import com.adform.academy.data.entity.AbstractScheme;

import java.util.List;

public interface DAOClient {

    public void addScheme(AbstractScheme scheme);

    public AbstractScheme getScheme(String group, String name, String version);

    public AbstractScheme getScheme(String group, String name);

    public List<AbstractScheme> getGroupOfScheme(String groupName);

    public void updateScheme(AbstractScheme scheme);

    public void deleteScheme(AbstractScheme scheme);

}
