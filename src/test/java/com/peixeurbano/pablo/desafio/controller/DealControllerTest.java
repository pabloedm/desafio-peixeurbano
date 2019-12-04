package com.peixeurbano.pablo.desafio.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.peixeurbano.pablo.desafio.BaseControllerEnviroment;

public class DealControllerTest extends BaseControllerEnviroment {

    @Test
    public void listShouldReturnOK() {
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals", String.class);
        assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    public void listShouldReturnCorrectValues() throws JSONException {
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/v1/deals", String.class);
        String expected = "[" +
                "  {" +
                "    \"createDate\": \"2019-12-04T19:00:00-02:00\"," +
                "    \"endDate\": \"2019-12-14T19:00:00-02:00\"," +
                "    \"id\": 1," +
                "    \"publishDate\": \"2019-12-04T20:00:00-02:00\"," +
                "    \"text\": \"Frigideira de Alumínio com Revestimento Cerâmico de 20cm, 24cm ou 28cm.\"," +
                "    \"tile\": \"Frigideira de Alumínio\"," +
                "    \"totalSold\": 0," +
                "    \"type\": \"PRODUCT\"," +
                "    \"url\": \"/frigideira+de+aluminio\"" +
                "  }" +
                "]";
        JSONAssert.assertEquals(expected, forEntity.getBody(), true);
    }

}
