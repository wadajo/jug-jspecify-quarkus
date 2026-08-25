package org.wadajo.controller;

import io.quarkus.logging.Log;
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

    ObraClient obraClient;

    public GaleriaResource(@RestClient ObraClient obraClient) {
        this.obraClient = obraClient;
    }

    private static final JsonMapper JSON_MAPPER = JsonMapper.builder()
        .build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObraResponse getObras() {
        Log.info("Fetching obras from external API");
        var responseRaw = obraClient.getResponseWrapper(2);

        if (responseRaw == null) {
            Log.info("Remote response is null, returning empty ObraResponse");
            return new ObraResponse(List.of());
        }

        return new ObraResponse(List.of(
            getObra(responseRaw, 0),
            getObra(responseRaw,1)));
    }

    private static Obra getObra(String rawResponse, Integer position) {
        var dataRawField = JSON_MAPPER.readTree(rawResponse)
                                .get("data")
                                .get(position)
                                .toPrettyString();
        var obraObtenida = JSON_MAPPER.readValue(dataRawField, Obra.class);
        Log.infof("Título de la obra: %s", obraObtenida.title());
        Log.infof("Artista de la obra: %s", obraObtenida.artist_title());
        Log.infof("¿El año de la obra fue bisiesto?: %b", obraObtenida.date_end().isLeap());
        Log.infof("Descripción de la obra en mayúscula: %s", obraObtenida.description().toUpperCase());
        return obraObtenida;
    }

}