package com.adform.academy.data.dao;

import com.adform.academy.data.dao.util.AerospikeConfig;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;

import java.util.LinkedList;
import java.util.List;

public class DAOAerospikeClient implements DAOClient {

    private static final String HOST = AerospikeConfig.HOST;
    private static final int PORT = AerospikeConfig.PORT;

    private static final String DBNAME = AerospikeConfig.DBNAME;
    private static WritePolicy policy = AerospikeConfig.policy;

    private static DAOAerospikeClient instance = new DAOAerospikeClient();

    private AerospikeClient client = new AerospikeClient(HOST, PORT);

    private DAOAerospikeClient() {
    }

    public static DAOAerospikeClient getInstance(){
        return instance;
    }

    @Override
    public void addScheme(String group, Scheme scheme) {
        String schemeName = null;
        double schemeVersion = 0.0;
        int schemeFieldCount = 0;

        if (null != scheme.getName() && null != scheme.getFields()) {
            schemeName = scheme.getName();
            schemeVersion = scheme.getVersion();
            schemeFieldCount = scheme.getFields().length;
        }
        Key schemeKey = new Key(DBNAME, group, schemeName + schemeVersion);

        Bin nameBin = new Bin("name", schemeName);
        Bin versBin = new Bin("version", schemeVersion);
        Bin countBin = new Bin("fieldcount", schemeFieldCount);

        client.put(policy, schemeKey, nameBin, versBin, countBin);

        for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
            Bin fieldBinName = new Bin("name", scheme.getField(fieldIndex).getName());
            Bin fieldBinPattern = new Bin("pattern", scheme.getField(fieldIndex).getPattern());
            Key fieldKey = new Key(DBNAME, schemeName, fieldIndex);
            client.put(policy, fieldKey, fieldBinName, fieldBinPattern);
        }
    }

    @Override
    public Scheme getScheme(String group, String name, double version) {
        Key schemeKey = new Key(DBNAME, group, name + version);

        Record schemeRecord = client.get(policy, schemeKey);

        int schemeFieldCount = schemeRecord.getInt("fieldcount");

        Field[] fields = new Field[schemeFieldCount];

        for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
            Key fieldKey = new Key(DBNAME, name, fieldIndex);
            Record fieldRecord = client.get(policy, fieldKey);
            fields[fieldIndex] = new Field(fieldRecord.getString("name"), fieldRecord.getString("pattern"));
        }
        return new Scheme(name, version, fields);
    }

    @Override
    public Scheme getScheme(String group, String name) {
        return null;
    }

    @Override
    public Group getGroupOfScheme(String groupName) {
        final List <Scheme> schemes = new LinkedList<>();

            client.scanAll(null, DBNAME, groupName, new ScanCallback() {
                @Override
                public void scanCallback(Key key, Record record) throws AerospikeException {
                    String schemeName = record.getString("name");
                    Double schemeVersion = record.getDouble("version");
                    int schemeFieldCount = record.getInt("fieldcount");

                    Field[] fields = new Field[schemeFieldCount];

                    for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
                        Key fieldKey = new Key(DBNAME, schemeName, fieldIndex);
                        Record fieldRecord = client.get(policy, fieldKey);
                        fields[fieldIndex] = new Field(fieldRecord.getString("name"), fieldRecord.getString("pattern"));
                    }
                    schemes.add(new Scheme(schemeName, schemeVersion, fields));
                }
            });
        return new Group(groupName, schemes);
    }

    @Override
    public void updateScheme(Scheme scheme) {

    }

    @Override
    public void deleteScheme(Scheme scheme) {
//         Key schemeKey = new Key(DBNAME, scheme.getName(), scheme);
    }
}
