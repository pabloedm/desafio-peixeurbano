package com.peixeurbano.pablo.desafio.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.peixeurbano.pablo.desafio.BaseControllerEnviroment;

public class BuyOptionControllerTest extends BaseControllerEnviroment {

    @Test
    public void listShouldReturnOK() {
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/buyOptions", String.class);
        assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    public void sellUnitShouldReturnOK() {
        final ResponseEntity<String> forEntity = restTemplate.postForEntity("http://localhost:" + port + "/v1/buyOptions/1/sellUnit", null, String.class);
        assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    public void deleteShouldReturnOk() {
        Integer idToDelete = 1;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/buyOptions/" + idToDelete, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteShouldReturnBadRequest() {
        Integer idToDelete = 99;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/buyOptions/" + idToDelete, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
