package src.com.adform.academy.data.DAO;

import src.com.adform.academy.data.exception.DAOException;

public class DAOFactory {

    private static DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance(){
        return instance;
    }

    private DAOFactory() {
    }

    public DAOClient getDAO(DAOClientType type) throws DAOException {
        switch (type){
            case AE :
                return DAOAerospikeClient.getInstance();
            default:
                throw new DAOException("No such client");
        }

    }


}
