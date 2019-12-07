package com.peixeurbano.pablo.desafio.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.peixeurbano.pablo.desafio.BaseControllerEnviroment;
import com.peixeurbano.pablo.desafio.dto.DealDTO;

public class DealControllerTest extends BaseControllerEnviroment {

    @Test
    public void listShouldReturnOK() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void listAllValidDealsShouldReturnOK() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals/valid", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void listShouldReturnCorrectValues() throws JSONException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals", String.class);
        String expected = "[" +
                "  {" +
                "    \"createDate\": \"2019-12-04T19:00:00-02:00\"," +
                "    \"endDate\": \"2020-12-31T19:00:00-02:00\"," +
                "    \"id\": 1," +
                "    \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "    \"text\": \"Frigideira de Alumínio com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "    \"title\": \"Frigideira de Alumínio\"," +
                "    \"totalSold\": 0," +
                "    \"type\": \"PRODUCT\"," +
                "    \"url\": \"/frigideira+de+aluminio\"," +
                "    \"expiration\": 392" +
                "  }," +
                "  {" +
                "    \"createDate\": \"2019-12-04T19:00:00-02:00\"," +
                "    \"endDate\": \"2020-12-31T19:00:00-02:00\"," +
                "    \"id\": 2," +
                "    \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "    \"text\": \"Frigideira de Barro com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "    \"title\": \"Frigideira de Barro\"," +
                "    \"totalSold\": 0," +
                "    \"type\": \"PRODUCT\"," +
                "    \"url\": \"/frigideira+de+barro\"," +
                "    \"expiration\": 392" +
                "  }" +
                "]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    public void insertShouldReturnCreated() throws JsonMappingException, JsonProcessingException {
        String json = "  {" +
                "    \"endDate\": \"2019-12-14T19:00:00-02:00\"," +
                "    \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "    \"text\": \"Frigideira de Metal com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "    \"title\": \"Frigideira de Metal\"," +
                "    \"type\": \"PRODUCT\"" +
                "  }";

        DealDTO dto = objectMapper.readValue(json, DealDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/v1/deals", dto, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteShouldReturnOk() {
        Integer idToDelete = 2;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/deals/" + idToDelete, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteShouldReturnBadRequest() {
        Integer idToDelete = 99;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/deals/" + idToDelete, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void deleteWithBuyOptionsShouldReturnBadRequest() {
        Integer idToDelete = 1;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/deals/" + idToDelete, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void findByIdShouldReturnCorrectValues() throws JSONException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals/1", String.class);
        String expected = "{" +
                "  \"buyOptions\": [" +
                "    {" +
                "      \"endDate\": null," +
                "      \"id\": 1," +
                "      \"normalPrice\": 100.0," +
                "      \"percentageDiscount\": 10.0," +
                "      \"quantityCoupon\": 30," +
                "      \"salePrice\": 90.0," +
                "      \"startDate\": \"2019-12-04T21:00:00-02:00\"," +
                "      \"title\": \"Tamanho 20cm - R$ 90\"" +
                "    }" +
                "  ]," +
                "  \"createDate\": \"2019-12-04T19:00:00-02:00\"," +
                "  \"endDate\": \"2020-12-31T19:00:00-02:00\"," +
                "  \"expiration\": 392," +
                "  \"id\": 1," +
                "  \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "  \"text\": \"Frigideira de Alumínio com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "  \"title\": \"Frigideira de Alumínio\"," +
                "  \"totalSold\": 0," +
                "  \"type\": \"PRODUCT\"," +
                "  \"url\": \"/frigideira+de+aluminio\"" +
                "}";
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    public void addBuyOptionShouldReturnOk() {
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/v1/deals/1/addOption/1", null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateShouldReturnOk() throws Exception {
        Integer id = 1;
        String json = "  {" +
                "    \"id\": " + 1 + "," +
                "    \"endDate\": \"2019-12-14T19:00:00-02:00\"," +
                "    \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "    \"text\": \"Frigideira de Metal com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "    \"title\": \"Frigideira de Metal\"," +
                "    \"type\": \"PRODUCT\"" +
                "  }";
        final DealDTO dto = objectMapper.readValue(json, DealDTO.class);

        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/v1/deals/" + id, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
