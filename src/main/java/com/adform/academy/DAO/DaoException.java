package com.adform.academy.DAO;


import com.adform.academy.exception.ProjectException;

public class DaoException extends ProjectException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

}
