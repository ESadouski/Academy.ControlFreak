package com.adform.academy.data.logic;

import com.adform.academy.data.restclient.RestClient;
import com.adform.academy.data.restclient.restexeption.ClientOperationException;
import org.junit.Before;
import org.junit.Test;

public class RESTSchemaServiceTest {

    private RestClient restClient;

    @Before
    public void initClient() {
        restClient = new RestClient("localhost:8080/adf/v1/scheme/");
    }

    @Test
    public void getLatestSchemeTest() throws ClientOperationException {
            restClient.getLatestScheme("2", "3");

    }

}
