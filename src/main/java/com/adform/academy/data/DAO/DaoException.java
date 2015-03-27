package com.adform.academy.data.DAO;


import com.adform.academy.data.exception.ProjectException;

public class DaoException extends ProjectException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

}
