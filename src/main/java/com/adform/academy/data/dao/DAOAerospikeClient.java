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
    public void addScheme(Scheme scheme) {
        String schemeGroup = null;
        String schemeName = null;
        double schemeVersion = 0.0;

        if (null != scheme.getGroup() && null != scheme.getName() && null != scheme.getFields()) {
            schemeGroup = scheme.getGroup().getName();
            schemeName = scheme.getName();
            schemeVersion = scheme.getVersion();
        }
        Key schemeKey = new Key(DBNAME, "SCHEMA", schemeGroup + schemeName + schemeVersion);

        Bin groupBin = new Bin("group", scheme.getGroup().getName());
        Bin nameBin = new Bin("name", schemeName);
        Bin versBin = new Bin("version", schemeVersion);
        Bin listBin = new Bin("fields", scheme.getFields());

        client.put(policy, schemeKey, groupBin, nameBin, versBin, listBin);

        addSchemeToGroup(scheme);
    }

    @Override
    public Scheme getScheme(String group, String name, int version) {
        Key schemeKey = new Key(DBNAME, group, name + version);

        Record schemeRecord = client.get(policy, schemeKey);

        List test = (List)schemeRecord.getValue("fields");

        int schemeFieldCount = schemeRecord.getInt("fieldcount");

        Field[] fields = new Field[schemeFieldCount];

        for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
            Key fieldKey = new Key(DBNAME, name, fieldIndex);
            Record fieldRecord = client.get(policy, fieldKey);
            fields[fieldIndex] = new Field(fieldRecord.getString("name"), fieldRecord.getString("pattern"));
        }
        return null;
    }

//    @Override
//    public Scheme getScheme(String group, String name) {
//        return null;
//    }


    @Override
    public Group getGroupOfScheme(Scheme scheme) {
        return null;
    }

    @Override
    public Group getGroupOfScheme(String groupName) {
            final  List <Scheme> schemes = new LinkedList<>();

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
//                    schemes.add(new Scheme(schemeName, schemeVersion, fields));
                }
            });
        return new Group(groupName, schemes);
    }

    @Override
    public void updateScheme(Scheme scheme) {
    }

    @Override
    public void deleteScheme(Scheme scheme) {
    }

    private void addSchemeToGroup(Scheme scheme){
        Key groupKey = new Key(DBNAME, "GROUPS", scheme.getGroup().getName());
        Group group = scheme.getGroup();
        group.addScheme(scheme);
        Bin nameBin = new Bin("name", group.getName());
        Bin schemaBin = new Bin("schema", group.getList());
        client.put(policy, groupKey, nameBin, schemaBin);
    }
}
