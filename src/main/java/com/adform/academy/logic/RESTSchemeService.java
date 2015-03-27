package com.adform.academy.logic;

import javax.ws.rs.*;

@Path("v1/scheme")
public class RESTSchemeService {
    public RESTSchemeService() {

    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/{version}")
    public String getSchemeByVersion(@PathParam("group") String groupName,
                                            @PathParam("name") String schemeName,
                                            @PathParam("version") Double version) {
        return "TODO-GetV";
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/{name}/latest")
    public String getLatestScheme(@PathParam("group") String groupName,
                                        @PathParam("name") String schemeName) {
        return "TODO-Latest ";
    }

    @GET
    @Produces("application/json")
    @Path("/{group}/")
    public String getAllSchemesInGroup(@PathParam("group") String groupName) {
        return "TODO-GetGR";
    }

    @DELETE
    @Path("/{group}/{name}/{version}")
    public void deleteScheme(@PathParam("group") String groupName,
                                             @PathParam("name") String schemeName,
                                             @PathParam("version") Double version) {
    }

    @PUT
    @Consumes("application/json")
    @Path("/{group}/{name}/{version}")
    public void putScheme(@PathParam("group") String groupName,
                                          @PathParam("name") String schemeName,
                                          @PathParam("version") Double version) {



    }

}
