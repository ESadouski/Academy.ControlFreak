package com.adform.academy.data.restClient;



import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
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
import java.util.List;


public class RestClient {
    String homeUrl = "http://localhost:8080/adf/";
    HttpClient client;
    Gson gson = new Gson();


    RestClient() {
        client = new DefaultHttpClient();
    }

    public Scheme getSchemeByVersion(String groupName, String schemeName, Double version) {
        String jsonLine = getJSONLineFromServer(groupName + "/" +  schemeName + "/" + version);
        return  gson.fromJson(jsonLine, Scheme.class);
    }


    public Scheme getLatestScheme(String groupName, String schemeName) {
        String jsonLine = getJSONLineFromServer(groupName + "/" +  schemeName + "/latest" );

        return   gson.fromJson(jsonLine, Scheme.class);

    }


    public Group getAllSchemesInGroup(String groupName) {
        String jsonLine = getJSONLineFromServer(groupName);
        return gson.fromJson(jsonLine, Group.class);

    }


    public void deleteScheme(String groupName, String schemeName, Double version) {
        HttpDelete deleteRequest = new HttpDelete(homeUrl + groupName + "/" +  schemeName + "/" + version);
        try {
            client.execute(deleteRequest);
            deleteRequest.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void putScheme(Scheme scheme, String groupName) {
        String jsonScheme = gson.toJson(scheme);

        HttpPut putRequest = new HttpPut(homeUrl +  "add/" + groupName +  "/" + jsonScheme);
        try {
            putRequest.addHeader("Content-Type", "application/json");
            //putRequest.setEntity(new StringEntity(gson.toJson(scheme)));
            client.execute(putRequest);
            putRequest.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getJSONLineFromServer(String url){
        String line = "";
        HttpGet request = new HttpGet(homeUrl + url);
        HttpResponse response = null;
        try {
            response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            line = rd.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }


}




