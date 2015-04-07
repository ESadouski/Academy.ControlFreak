package com.adform.academy.data.runnable;

import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;
import com.adform.academy.data.entity.Field;
import com.adform.academy.data.entity.Scheme;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;

import javax.swing.*;


public class Main {
    AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);

    public static void main(String[] args) throws DaoException {
//        DAOFactory factory = DAOFactory.getInstance();
//        DAOClient clientAE = factory.getDAO(DAOClientType.AE);
//
////        Field[] fields = {new Field("range", "[a-b]")};
////        Scheme schemeInput = new Scheme("advertising", 1.0, fields);
////
////        clientAE.addScheme("click", schemeInput);
//        Scheme schemeOutput = clientAE.getScheme("click", "advertising", 1);
//        clientAE.getGroupOfScheme("New");
//
//        System.out.println("name : " + schemeOutput.getName() + "\nversion : " + schemeOutput.getVersion() + "\n");
//        System.out.println("Fields:\n" + schemeOutput.getField(0).getName() + schemeOutput.getField(0).getPattern());
        new  Main().test();

    }




    private void test() {
        Field f1 = new Field("Field1", "p1");
        Field f2 = new Field("Field2", "p2");
        Field f3 = new Field("Field3", "p2");
        Field f4 = new Field("Field4", "p4");
//        putReckord(f1, "Sch1");
//        putReckord(f2, "Sch1");
//        putReckord(f3, "Sch1");
//        putReckord(f4, "Sch2");
        getFieldsByScheme("Sch1");
    }


    private void putReckord(Field field, String str) {
        Key key = new Key("test", "fields", field.getName() + str);
        Bin nameB = new Bin("name", field.getName());
        Bin patternB = new Bin("pattern", field.getPattern());
        Bin schB = new Bin("scheme", str);

        client.put(null, key, nameB, patternB,  schB);
        client.close();
    }

    private void getFieldsByScheme(String sch){

        Statement stmt = new Statement();
        stmt.setFilters(Filter.equal("scheme", sch));
        RecordSet rs = client.query(null, stmt);
        stmt.setNamespace("test");
        try {
            while (rs.next()) {
                Key key = rs.getKey();
                Record record = rs.getRecord();
                System.out.println(record.toString());
            }
        }
        finally {
            rs.close();
        }

    }

}
