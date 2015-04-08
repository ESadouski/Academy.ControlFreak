package com.adform.academy.data.dao;

import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;

public interface DAOClient {

    public void addScheme(Scheme scheme) throws DaoException;

    public Scheme getScheme(String group, String name, int version) throws DaoException;

    public Group getGroupOfScheme(String groupName) throws DaoException;

    public void deleteScheme(Scheme scheme);

}
