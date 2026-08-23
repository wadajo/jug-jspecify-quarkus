package org.wadajo.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.wadajo.client.ObraClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@QuarkusTest
class GaleriaResourceTest {

    @InjectMock
    @RestClient
    ObraClient obraClient;

    @Inject
    GaleriaResource galeriaResource;

    @Test
    void debeDevolverListaVaciaSiLaResponseDelRemotoEsNull() {
        when(obraClient.getResponseWrapper(2)).thenReturn(null);

        var obras = galeriaResource.getObras();

        assertThat(obras.data()).isEmpty();
    }
}