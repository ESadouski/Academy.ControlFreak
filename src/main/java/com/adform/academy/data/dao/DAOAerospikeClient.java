package com.adform.academy.data.dao;

import com.adform.academy.data.dao.util.AerospikeConfig;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;

import java.util.ArrayList;
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
    public void addScheme(Scheme scheme) throws DaoException {
        if (null == scheme.getGroup() && null == scheme.getName() && 0 == scheme.getVersion() && null == scheme.getFields()) {
            throw new DaoException("Empty scheme");
        }
        else {
            String schemeGroup = scheme.getGroup();
            String schemeName = scheme.getName();
            int schemeVersion = scheme.getVersion();

            Key schemeKey = new Key(DBNAME, "SCHEMA", schemeGroup + schemeName + schemeVersion);

            Bin groupBin = new Bin("group", scheme.getGroup());
            Bin nameBin = new Bin("name", schemeName);
            Bin versBin = new Bin("version", schemeVersion);
            Bin fieldsBin = new Bin("fields", scheme.getFields());

            addSchemeToGroup(scheme);
            client.put(policy, schemeKey, groupBin, nameBin, versBin, fieldsBin);
        }
    }

    @Override
    public Scheme getScheme(String groupName, String name, int version) throws DaoException{
        if (null == groupName && null == name && 0 == version) {
            throw new DaoException("Empty query");
        }
        else {
            Key schemeKey = new Key(DBNAME, "SCHEMA", groupName + name + version);
            if (client.exists(policy, schemeKey)) {
                Record schemeRecord = client.get(policy, schemeKey);
                List<Field> fields = (ArrayList) schemeRecord.getValue("fields");
                return new Scheme(groupName, name, version, fields);
            }
            else throw new DaoException("Scheme does not found");
        }
    }

    @Override
    public Group getGroupOfScheme(String groupName) throws DaoException{
        if (null == groupName) {
            throw new DaoException("Empty query");
        }
        else {
            Key groupKey = new Key(DBNAME, "GROUPS", groupName);
            if (client.exists(policy, groupKey)) {
                Record groupRecord = client.get(policy, groupKey);
                List<Scheme> schema = (ArrayList) groupRecord.getValue("schema");
                return new Group(groupName, schema);
            }
            else throw new DaoException("Group does not found");
        }
    }

    @Override
    public void deleteScheme(Scheme scheme) {
        if (null == scheme.getGroup() && null == scheme.getName() && null == scheme.getFields()) {
        } else {
            String schemeGroup = scheme.getGroup();
            String schemeName = scheme.getName();
            int schemeVersion = scheme.getVersion();

            Key schemeKey = new Key(DBNAME, "SCHEMA", schemeGroup + schemeName + schemeVersion);

            deleteSchemeFromGroup(scheme);
            client.delete(policy, schemeKey);
        }
    }

    private void addSchemeToGroup(Scheme scheme){
        Key groupKey = new Key(DBNAME, "GROUPS", scheme.getGroup());

        if (client.exists(policy, groupKey)){
            Record groupRecord = client.get(policy, groupKey);
            List<Scheme> schema = (ArrayList) groupRecord.getValue("schema");
            if (!schema.contains(scheme)) {
                schema.add(schema.size(), scheme);
                client.put(policy, groupKey, new Bin("schema", schema));
            }
        }
        else {
            List<Scheme> schema = new ArrayList<>();
            schema.add(schema.size(), scheme);
            client.put(policy, groupKey, new Bin("name", scheme.getGroup()), new Bin("schema", schema));
        }
    }

    private void deleteSchemeFromGroup(Scheme scheme){
        Key groupKey = new Key(DBNAME, "GROUPS", scheme.getGroup());

        if (client.exists(policy, groupKey)){
            Record groupRecord = client.get(policy, groupKey);
            List<Scheme> schema = (ArrayList) groupRecord.getValue("schema");
            schema.remove(scheme);
            client.put(policy, groupKey, new Bin("schema", schema));
        }
    }


//    private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
//        List<T> r = new ArrayList<T>(c.size());
//        for(Object o: c)
//            r.add(clazz.cast(o));
//        return r;
//    }
}
