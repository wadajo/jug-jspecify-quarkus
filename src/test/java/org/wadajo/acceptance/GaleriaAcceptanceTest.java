package org.wadajo.acceptance;

import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
@ConnectWireMock
class GaleriaAcceptanceTest {

    @Test
    void debeDevolverUnaListaDeObras() {
        RestAssured
            .when()
            .get("/obras")
            .then()
            .statusCode(200)
            .body("data", Matchers.iterableWithSize(Matchers.equalTo(2)))
            .body("data[0].title", Matchers.equalTo("Fragment Depicting a Personification of a Season"));
    }

}