package com.adform.academy.DAO;

public class DAOFactory {

    private static DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance(){
        return instance;
    }

    private DAOFactory() {
    }

    public DAOClient getDAO(DAOClientType type) throws DaoException {
        switch (type){
            case AE :
                return DAOAerospikeClient.getInstance();
            default:
                throw new DaoException("No such client");
        }

    }


}
