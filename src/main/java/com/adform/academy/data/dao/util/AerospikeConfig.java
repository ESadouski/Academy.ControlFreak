package com.adform.academy.data.dao.util;

import com.aerospike.client.policy.WritePolicy;

public class AerospikeConfig {

    public static final String HOST = "localhost";
    public static final int PORT = 3000;

    public static final String DBNAME = "first";
    public static WritePolicy policy = new WritePolicy();
}
