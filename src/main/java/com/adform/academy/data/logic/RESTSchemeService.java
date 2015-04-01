package com.adform.academy.data.logic;


import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;
import com.adform.academy.data.entity.Scheme;
import com.google.gson.Gson;


import javax.ws.rs.*;
import java.util.List;

@Path("v1/scheme")
public class RESTSchemeService {
    DAOClient clientDB;
    Gson gson;

    public RESTSchemeService() {
        gson = new Gson();
        try {
            clientDB = DAOFactory.getInstance().getDAO(DAOClientType.AE);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/{version}")
    public String getSchemeByVersion(@PathParam("group") String groupName,
                                     @PathParam("name") String schemeName,
                                     @PathParam("version") Double version) {
        return gson.toJson(clientDB.getScheme(groupName, schemeName, version));
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/latest")
    public String getLatestScheme(@PathParam("group") String groupName,
                                  @PathParam("name") String schemeName) {
        return gson.toJson(clientDB.getScheme(groupName, schemeName));
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/")
    public String getAllSchemesInGroup(@PathParam("group") String groupName) {
        List<Scheme> result = clientDB.getGroupOfScheme(groupName);
        return gson.toJson(result);

    }

    @DELETE
    @Path("/{group}/{name}/{version}")
    public void deleteScheme(@PathParam("group") String groupName,
                             @PathParam("name") String schemeName,
                             @PathParam("version") Double version) {
        clientDB.deleteScheme(clientDB.getScheme(groupName, schemeName, version));
    }

    @PUT
    @Consumes("application/json")
    @Path("/add/{groupName}/{jsonScheme}")
    public void putScheme(@PathParam("jsonScheme") String jsonScheme,
                          @PathParam("groupName") String groupName) {
        clientDB.addScheme(groupName, gson.fromJson(jsonScheme, Scheme.class));
    }

}
