package com.adform.academy.data.logic;

import javax.ws.rs.*;

@Path("v1/scheme")
public class RESTSchemeService {
    public RESTSchemeService() {

    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/{version}")
    public String getSchemeByNameAndVersion(@PathParam("group") String groupName,
                                            @PathParam("name") String schemeName,
                                            @PathParam("version") Double version) {
        return "TODO";
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/latest")
    public String getLatestSchemeByName(@PathParam("group") String groupName,
                                        @PathParam("name") String schemeName) {
        return "TODO";
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/")
    public String getAllSchemesInGroup(@PathParam("group") String groupName) {
        return "TODO";
    }

    @DELETE
    @Path("/{group}/{name}/{version}")
    public void deleteSchemeByNameAndVersion(@PathParam("group") String groupName,
                                             @PathParam("name") String schemeName,
                                             @PathParam("version") Double version) {
    }

    @PUT
    @Consumes("application/json")
    @Path("/{group}/{name}/{version}")
    public void putSchemeByNameAndVersion(@PathParam("group") String groupName,
                                          @PathParam("name") String schemeName,
                                          @PathParam("version") Double version) {

    }

}
