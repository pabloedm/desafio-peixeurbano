package com.peixeurbano.pablo.desafio.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.peixeurbano.pablo.desafio.BaseControllerEnviroment;
import com.peixeurbano.pablo.desafio.dto.BuyOptionDTO;

public class BuyOptionControllerTest extends BaseControllerEnviroment {

    @Test
    public void listShouldReturnOK() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/buyOptions", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void sellUnitShouldReturnOK() {
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/v1/buyOptions/1/sellUnit", null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
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

    @Test
    public void insertShouldReturnCreated() throws Exception {
        String json = "  {" +
                "    \"startDate\": \"2019-12-14T19:00:00-02:00\"," +
                "    \"normalPrice\": 200.0," +
                "    \"percentageDiscount\": 20.0," +
                "    \"quantityCoupon\": 100," +
                "    \"title\": \"Frigideira de Metal\"" +
                "  }";
        final BuyOptionDTO dto = objectMapper.readValue(json, BuyOptionDTO.class);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/v1/buyOptions/", dto, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateShouldReturnOk() throws Exception {
        Integer id = 1;
        String json = "  {" +
                "    \"id\": " + 1 + "," +
                "    \"startDate\": \"2019-12-14T19:00:00-02:00\"," +
                "    \"normalPrice\": 200.0," +
                "    \"percentageDiscount\": 20.0," +
                "    \"quantityCoupon\": 100," +
                "    \"title\": \"Frigideira de Metal\"" +
                "  }";
        final BuyOptionDTO dto = objectMapper.readValue(json, BuyOptionDTO.class);

        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/buyOptions/" + id, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
