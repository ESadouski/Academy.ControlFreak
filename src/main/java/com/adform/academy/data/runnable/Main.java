package com.adform.academy.data.runnable;

import com.adform.academy.data.DAO.DAOClient;
import com.adform.academy.data.DAO.DAOClientType;
import com.adform.academy.data.DAO.DAOFactory;
import com.adform.academy.data.exception.DAOException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DAOException {
        System.out.println("Hello world");
        JOptionPane.showMessageDialog(null,"Hello");

        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

    }

}
