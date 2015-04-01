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

        Field[] fields = {new Field("fild", "[a-b]")};
        Scheme schemeInput = new Scheme("new2", 1, fields);

        clientAE.addScheme("New", schemeInput);
        Scheme schemeOutput = clientAE.getScheme("New", "new2", 1);
        clientAE.getGroupOfScheme("New");

        System.out.println(schemeOutput.getField(0).getName() + schemeOutput.getField(0).getPattern());

    }

}
