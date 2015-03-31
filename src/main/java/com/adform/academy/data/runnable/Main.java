package com.adform.academy.data.runnable;

import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DaoException {
        System.out.println("Hello world");
        JOptionPane.showMessageDialog(null,"Hello");

        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

    }

}
