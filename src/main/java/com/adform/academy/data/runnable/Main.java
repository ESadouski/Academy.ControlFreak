package com.adform.academy.data.runnable;

import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Scheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DaoException {
        System.out.println("Hello world");
        JOptionPane.showMessageDialog(null,"Hello");

        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

        Field[] fields = {new Field("fild", "[a-z]")};
        Scheme scheme = new Scheme("new", 1.0, fields);

        clientAE.addScheme("New", scheme);
        clientAE.getScheme("New", "new", 1.0);

    }

}
