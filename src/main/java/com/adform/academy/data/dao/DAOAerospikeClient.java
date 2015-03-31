package com.adform.academy.data.dao;

import com.adform.academy.data.entity.Scheme;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.WritePolicy;

import java.util.List;

public class DAOAerospikeClient implements DAOClient {

    private static final String HOST = "localhost";
    private static final int PORT = 3000;

    private static final String DBNAME = "first";
    private static WritePolicy policy = new WritePolicy();

    private static DAOAerospikeClient instance = new DAOAerospikeClient();

    private AerospikeClient client = new AerospikeClient(HOST, PORT);

    private DAOAerospikeClient() {
    }

    public static DAOAerospikeClient getInstance(){
        return instance;
    }

    @Override
    public void addScheme(String group, Scheme scheme) {
        String name = scheme.getName();
        double version = scheme.getVersion();

        Key key = new Key(DBNAME, group, name + version);

        Bin nameBin = new Bin("name", name);
        Bin versBin = new Bin("version", version);

        client.put(policy, key, nameBin, versBin);
    }

    @Override
    public Scheme getScheme(String group, String name, double version) {
        return null;
    }

    @Override
    public Scheme getScheme(String group, String name) {
        return null;
    }

    @Override
    public List<Scheme> getGroupOfScheme(String groupName) {
        return null;
    }

    @Override
    public void updateScheme(Scheme scheme) {

    }

    @Override
    public void deleteScheme(Scheme scheme) {

    }
}
