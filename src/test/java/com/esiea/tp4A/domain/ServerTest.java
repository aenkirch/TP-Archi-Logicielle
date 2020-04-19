package com.esiea.tp4A.domain;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import server.springboot.Controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Controller.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1CreatePlayerSuccess() throws URISyntaxException {
        ResponseEntity<String> success = restTemplate.exchange("/player/Marwan", HttpMethod.POST, null, String.class);
        Assertions.assertEquals(201, success.getStatusCodeValue());
    }

    @Test
    public void test2CreatePlayerTestFail() throws URISyntaxException {
        ResponseEntity<String> fail = restTemplate.exchange("/player/Marwan", HttpMethod.POST, null, String.class);
        Assertions.assertEquals(409, fail.getStatusCodeValue());
    }

    @Test
    public void test3GetPlayerTest() throws URISyntaxException {
        ResponseEntity<String> response = restTemplate.exchange("/player/Marwan", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void test4PatchTest() throws URISyntaxException {
        allowMethods("PATCH");
        ResponseEntity<String> response = restTemplate.exchange("/player/Marwan/ff", HttpMethod.PATCH, null, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
