package com.adform.academy.data.restclient;


import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
import com.adform.academy.data.restclient.restexeption.ClientOperationException;
import com.adform.academy.data.restclient.restexeption.EmptyJSONException;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RestClient {
    private String homeUrl;
    private HttpClient client;
    private Gson gson = new Gson();


    public RestClient(String url) {
        client = new DefaultHttpClient();
        homeUrl = url;
    }

    public Scheme getSchemeByVersion(String groupName, String schemeName, int version) throws ClientOperationException {

        String jsonLine;
        try {
            jsonLine = getJSONLineFromServer(groupName + "/" + schemeName + "/" + version);
        } catch (EmptyJSONException e) {
            throw new ClientOperationException(e.getMessage());
        }
        return gson.fromJson(jsonLine, Scheme.class);
    }


    public Scheme getLatestScheme(String groupName, String schemeName) throws ClientOperationException {
        String jsonLine;
        try {
            jsonLine = getJSONLineFromServer(groupName + "/" + schemeName + "/latest");
        } catch (EmptyJSONException e) {
            throw new ClientOperationException(e.getMessage());
        }

        return gson.fromJson(jsonLine, Scheme.class);

    }


    public Group getAllSchemesInGroup(String groupName) throws ClientOperationException {
        String jsonLine;
        try {
            jsonLine = getJSONLineFromServer(groupName);
        } catch (EmptyJSONException e) {
            throw new ClientOperationException(e.getMessage());
        }
        return gson.fromJson(jsonLine, Group.class);

    }


    public void deleteScheme(String groupName, String schemeName, int version) throws ClientOperationException {
        HttpDelete deleteRequest = new HttpDelete(homeUrl + groupName + "/" + schemeName + "/" + version);
        try {
            client.execute(deleteRequest);
            deleteRequest.releaseConnection();
        } catch (IOException e) {
            throw new ClientOperationException(e.getMessage());
        }

    }


    public void putScheme(Scheme scheme, String groupName) throws ClientOperationException {
        String jsonScheme = gson.toJson(scheme);

        HttpPut putRequest = new HttpPut(homeUrl + "add/" + groupName + "/" + jsonScheme);
        try {
            putRequest.addHeader("Content-Type", "application/json");
            //putRequest.setEntity(new StringEntity(gson.toJson(scheme)));
            client.execute(putRequest);
            putRequest.releaseConnection();
        } catch (IOException e) {
            throw new ClientOperationException(e.getMessage());
        }

    }

    private String getJSONLineFromServer(String url) throws EmptyJSONException, ClientOperationException {
        String line;
        HttpGet request = new HttpGet(homeUrl + url);
        HttpResponse response;
        try {
            response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            line = rd.readLine();
        } catch (IOException e) {
            throw new ClientOperationException(e.getMessage());
        }

        if ("".equals(line)) {
            throw new EmptyJSONException();
        }
        return line;
    }


}