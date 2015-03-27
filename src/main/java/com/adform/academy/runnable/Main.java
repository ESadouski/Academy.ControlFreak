package com.adform.academy.runnable;



import com.adform.academy.DAO.DAOClient;
import com.adform.academy.DAO.DAOClientType;
import com.adform.academy.DAO.DAOFactory;
import com.adform.academy.DAO.DaoException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DaoException {
        System.out.println("Hello world");
        JOptionPane.showMessageDialog(null,"Hello");

        DAOFactory factory = DAOFactory.getInstance();
        DAOClient clientAE = factory.getDAO(DAOClientType.AE);

    }

}
