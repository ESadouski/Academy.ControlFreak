package com.adform.academy.data.runnable;

import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DaoException {
        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

        Field field1 = new Field("range", "[a-b]");
        Field field2 = new Field("tel", "[0-9]");
        Field field3 = new Field("cost", "[0-9]");
        List<Field> list = new LinkedList<>();
        list.add(field1);
        list.add(field2);
        list.add(field3);

        Scheme scheme = new Scheme("advert4", "click23456", 1);
        scheme.setFields(list);

        List schemes = new LinkedList<Scheme>();
        schemes.add(schemes.size(), scheme);

        Group group = new Group("advert", schemes);

        scheme.setGroup(group.getName());
//
//        Group group = clientAE.getGroupOfScheme("advert4");
        clientAE.addScheme(scheme);

//        Group group1 = clientAE.getGroupOfScheme("advert4");
        clientAE.deleteScheme(scheme);

//        Scheme schemeOut = clientAE.getScheme("advert4", "click23", 1);
//        ;

        System.out.println("all right");

//        Group group2 = clientAE.getGroupOfScheme("advert4");

//        Scheme schemeOutput = clientAE.getScheme("click", "advertising", 1);
//        clientAE.getGroupOfScheme("New");
//
//        System.out.println("name : " + schemeOutput.getName() + "\nversion : " + schemeOutput.getVersion()+"\n");
//        System.out.println("Fields:\n" + schemeOutput.getField(0).getName() + schemeOutput.getField(0).getPattern());

    }

}
