package com.adform.academy.data.DAO;

import com.adform.academy.data.entity.AbstractScheme;
import com.aerospike.client.AerospikeClient;

import java.util.List;

public class DAOAerospikeClient implements DAOClient {

    private static final String HOST = "localhost";
    private static final int PORT = 3000;

    private static DAOAerospikeClient instance = new DAOAerospikeClient();

    private AerospikeClient client = new AerospikeClient(HOST, PORT);

    private DAOAerospikeClient() {
    }

    public static DAOAerospikeClient getInstance(){
        return instance;
    }

    @Override
    public void addScheme(AbstractScheme scheme) {
        String name = scheme.getName();
        double version = scheme.getVersion();


    }

    @Override
    public AbstractScheme getScheme(String group, String name, String version) {
        return null;
    }

    @Override
    public AbstractScheme getScheme(String group, String name) {
        return null;
    }

    @Override
    public List<AbstractScheme> getGroupOfScheme(String groupName) {
        return null;
    }

    @Override
    public void updateScheme(AbstractScheme scheme) {

    }

    @Override
    public void deleteScheme(AbstractScheme scheme) {

    }
}
