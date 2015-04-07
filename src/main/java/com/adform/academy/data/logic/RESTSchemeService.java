package com.adform.academy.data.logic;


import com.adform.academy.data.dao.DAOClient;
import com.adform.academy.data.dao.DAOClientType;
import com.adform.academy.data.dao.DAOFactory;
import com.adform.academy.data.dao.DaoException;
import com.adform.academy.data.entity.Group;
import com.adform.academy.data.entity.Scheme;
import com.google.gson.Gson;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;


import javax.ws.rs.*;

@Path("/v1/scheme")
public class RESTSchemeService {
    private DAOClient clientDB;
    private Gson gson;

    public RESTSchemeService() {
        gson = new Gson();
        try {
            clientDB = DAOFactory.getInstance().getDAO(DAOClientType.AE);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }


    @GET
    @Path("/status")
    public String testConnection() {
        return "Server is running";
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/{version}")
    public String getSchemeByVersion(@PathParam("group") String groupName,
                                     @PathParam("name") String schemeName,
                                     @PathParam("version") int version) {
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
        Group result = clientDB.getGroupOfScheme(groupName);
        return gson.toJson(result);

    }

    @DELETE
    @Path("/{group}/{name}/{version}")
    public String deleteScheme(@PathParam("group") String groupName,
                             @PathParam("name") String schemeName,
                             @PathParam("version") int version) {
        clientDB.deleteScheme(clientDB.getScheme(groupName, schemeName, version));

//        TODO AddException and error code
        return  null;
    }

    @PUT
    @Consumes("application/json")
    @Path("/add/{groupName}/{jsonScheme}")
    public String putScheme(@PathParam("jsonScheme") String jsonScheme,
                          @PathParam("groupName") String groupName) {
        clientDB.addScheme(groupName, gson.fromJson(jsonScheme, Scheme.class));

//        TODO AddException and error code
        return  null;
    }


    public static void main(String[] args) throws Exception
    {
        //Path for tests localhost8081/v1/scheme
        TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
        tjws.setPort(8081);
        tjws.getDeployment().getActualResourceClasses().add(RESTSchemeService.class);
        tjws.start();
    }

}
