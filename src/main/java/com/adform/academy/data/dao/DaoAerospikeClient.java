package com.adform.academy.data.dao;

import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DaoAerospikeClient implements DAOClient {

    private static final String URI = "src/main/resources/config.properties";

    private static String host;
    private static int port;
    private static String dataBaseName;
    private static WritePolicy policy = new WritePolicy();

    //private static DaoAerospikeClient instance = new DaoAerospikeClient();

    private AerospikeClient client = new AerospikeClient(host, port);

    public DaoAerospikeClient() throws DaoException {
        Properties property = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(URI)) {
            property.load(fileInputStream);
            host = property.getProperty("db.host");
            port = Integer.parseInt(property.getProperty("db.port"));
            dataBaseName = property.getProperty("db.name");
        } catch (IOException e) {
            throw new DaoException("Cant read property file", e);
        }
    }

//    public static DaoAerospikeClient getInstance(){
//        return instance;
//    }

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
        Key schemeKey = new Key(dataBaseName, group, schemeName + schemeVersion);

        Bin nameBin = new Bin("name", schemeName);
        Bin versBin = new Bin("version", schemeVersion);
        Bin countBin = new Bin("fieldcount", schemeFieldCount);

        client.put(policy, schemeKey, nameBin, versBin, countBin);

        for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
            Bin fieldBinName = new Bin("name", scheme.getField(fieldIndex).getName());
            Bin fieldBinPattern = new Bin("pattern", scheme.getField(fieldIndex).getPattern());
            Key fieldKey = new Key(dataBaseName, schemeName, fieldIndex);
            client.put(policy, fieldKey, fieldBinName, fieldBinPattern);
        }
    }

    @Override
    public Scheme getScheme(String group, String name, int version) {
        Key schemeKey = new Key(dataBaseName, group, name + version);

        Record schemeRecord = client.get(policy, schemeKey);

        int schemeFieldCount = schemeRecord.getInt("fieldcount");

        Field[] fields = new Field[schemeFieldCount];

        for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
            Key fieldKey = new Key(dataBaseName, name, fieldIndex);
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
            final  List <Scheme> schemes = new LinkedList<Scheme>();

            client.scanAll(null, dataBaseName, groupName, new ScanCallback() {
                @Override
                public void scanCallback(Key key, Record record) throws AerospikeException {
                    String schemeName = record.getString("name");
                    int schemeVersion = record.getInt("version");
                    int schemeFieldCount = record.getInt("fieldcount");

                    Field[] fields = new Field[schemeFieldCount];

                    for (int fieldIndex = 0; fieldIndex < schemeFieldCount; fieldIndex++) {
                        Key fieldKey = new Key(dataBaseName, schemeName, fieldIndex);
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
    }
}
