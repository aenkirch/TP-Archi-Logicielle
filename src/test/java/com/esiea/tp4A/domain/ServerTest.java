package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import server.springboot.Controller;

import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Controller.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private TestRestTemplate template;

    @Test
    public void PlayerRegistration1() throws URISyntaxException {
        ResponseEntity<String> r = template.exchange("localhost:8080/api/player/player1", HttpMethod.POST, null, String.class);
        Assertions.assertEquals(201, r.getStatusCodeValue());
    }
    @Test
    public void PlayerRegistration2() throws URISyntaxException {
        ResponseEntity<String> r = template.exchange("localhost:8080/api/player/player1", HttpMethod.POST, null, String.class);
        Assertions.assertEquals(409, r.getStatusCodeValue());
    }

}
