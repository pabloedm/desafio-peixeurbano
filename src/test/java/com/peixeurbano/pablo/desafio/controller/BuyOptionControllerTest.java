package com.peixeurbano.pablo.desafio.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.peixeurbano.pablo.desafio.BaseControllerEnviroment;

public class BuyOptionControllerTest extends BaseControllerEnviroment {

    @Test
    public void listShouldReturnOK() {
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/buyOptions", String.class);
        assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

}
