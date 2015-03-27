package com.adform.academy.restClient;



import com.adform.academy.entity.Scheme;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;


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
        //TODO
        return  null;
    }


    public Scheme getLatestScheme(String groupName, String schemeName) {
        String jsonLine = getJSONLineFromServer(groupName + "/" +  schemeName + "/latest" );
        //TODO
        return  null;

    }


    public List<Scheme> getAllSchemesInGroup(String groupName) {
        String jsonLine = getJSONLineFromServer(groupName);
        //TODO
        return  null;

    }


    public void deleteScheme(String groupName, String schemeName, Double version) {
        HttpDelete deleteRequest = new HttpDelete(homeUrl + groupName + "/" +  schemeName + "/" + version);
        try {
            deleteRequest.addHeader("Content-Type", "application/json");

            client.execute(deleteRequest);
            deleteRequest.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void putScheme(Scheme scheme, String groupName) {

        HttpPut putRequest = new HttpPut(homeUrl + groupName + "/" +  scheme.getName() + "/" + scheme.getVersion());
        try {
            putRequest.addHeader("Content-Type", "application/json");
            putRequest.setEntity(new StringEntity(gson.toJson(scheme)));
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




