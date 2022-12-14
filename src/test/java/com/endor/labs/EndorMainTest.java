package com.endor.labs;

import com.endor.labs.controllers.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndorMainTest {

    @Autowired
    private PersonController personController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
        Simple sanity check test, that will fail if the application context cannot start.
     */
    @Test
    public void checkContextLoads() {
        assertThat(personController).isNotNull();
    }

    /*
        Sending an HTTP Request and assert the response. This is a default end-point to check application started or not.
     */
    @Test
    public void ping() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ping", String.class)).contains("PONG");
    }
}
