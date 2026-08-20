package org.wadajo.acceptance;

import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.wadajo.controller.GaleriaResource;

@QuarkusTest
@TestHTTPEndpoint(GaleriaResource.class)
@ConnectWireMock(restClient = "obras-api")
class GaleriaAcceptanceTest {

    @Test
    void debeDevolverUnaListaDeObras() {
        RestAssured
            .when()
            .get()
            .then()
            .statusCode(200)
            .body("data", Matchers.iterableWithSize(Matchers.equalTo(2)))
            .body("data[0].title", Matchers.equalTo("Fragment Depicting a Personification of a Season"));
    }

}