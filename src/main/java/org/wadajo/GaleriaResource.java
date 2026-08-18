package org.wadajo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Year;
import java.util.List;

@Path("obras")
public class GaleriaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObraResponse getObras() {
        return new ObraResponse(List.of(
            new Obra(
                "Vincent van Gogh",
                "Starry Night",
                Year.of(1889),
                "A depiction of the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence."
            ),
            new Obra(
                "Maruja Mallo",
                "Verbena",
                Year.of(1927),
                "A revolutionary work that challenged traditional representations of the public space."
            )));

    }



}