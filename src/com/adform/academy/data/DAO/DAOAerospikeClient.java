package src.com.adform.academy.data.DAO;

import com.aerospike.client.AerospikeClient;

import java.util.List;

public class DAOAerospikeClient implements DAOClient {

    private static DAOAerospikeClient instance = new DAOAerospikeClient();

    private AerospikeClient client = new AerospikeClient("localhost", 3000);

    private DAOAerospikeClient() {
    }

    public static DAOAerospikeClient getInstance(){
        return instance;
    }

    @Override
    public void addScheme(String group, String name, String version) {

    }

    @Override
    public String getScheme(String group, String name, String version) {
        return null;
    }

    @Override
    public String getScheme(String group, String name) {
        return null;
    }

    @Override
    public List getGroupOfScheme(String groupName) {
        return null;
    }

    @Override
    public void updateScheme(String group, String name, String version) {

    }

    @Override
    public void deleteScheme(String group, String name, String version) {

    }
}
