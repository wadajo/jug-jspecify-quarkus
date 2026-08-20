package org.wadajo.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(configKey = "obras-api")
@Path("artworks")
public interface ObraClient {

    @GET
    String getResponseWrapper(@QueryParam("limit") Integer limit);

}