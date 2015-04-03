package com.adform.academy.data.runnable;

import com.adform.academy.data.dao.*;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Scheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DaoException {
        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

//        Field[] fields = {new Field("range", "[a-b]")};
//        Scheme schemeInput = new Scheme("advertising", 1.0, fields);
//
//        clientAE.addScheme("click", schemeInput);
//        Scheme schemeOutput = clientAE.getScheme("click", "advertising", 1.0);
//        clientAE.getGroupOfScheme("New");
//
//        System.out.println("name : " + schemeOutput.getName() + "\nversion : " + schemeOutput.getVersion()+"\n");
//        System.out.println("Fields:\n" + schemeOutput.getField(0).getName() + schemeOutput.getField(0).getPattern());
        Field[] test = {new Field("f1", "ds"), new Field("f2", "er"), new Field("f3", "dsfafdasf")};
        Scheme scheme = new Scheme("TestSch", 2, test);
        System.out.println( scheme.toString());


    }

}
