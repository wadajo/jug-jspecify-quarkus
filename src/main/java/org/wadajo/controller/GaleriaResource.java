package org.wadajo.controller;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.wadajo.client.ObraClient;
import org.wadajo.dto.ObraResponse;
import org.wadajo.model.Obra;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Path("obras")
public class GaleriaResource {

    @Inject
    @RestClient
    ObraClient obraClient;

    private static final JsonMapper JSON_MAPPER = JsonMapper.builder()
        .build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObraResponse getObras() {
        Log.info("Fetching obras from external API");
        var responseRaw = obraClient.getResponseWrapper(2);

        return new ObraResponse(List.of(
            getObra(responseRaw, 0),
            getObra(responseRaw,1)));
    }

    private static Obra getObra(String rawResponse, Integer position) {
        var dataRawField = JSON_MAPPER.readTree(rawResponse)
                                .get("data")
                                .get(position)
                                .toPrettyString();
        return JSON_MAPPER.readValue(dataRawField, Obra.class);
    }

}