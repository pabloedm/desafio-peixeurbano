package com.peixeurbano.pablo.desafio.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BuyOptionServiceTest {

    private BuyOptionService buyOptionService;

    @Before
    public void before() {
        buyOptionService = new BuyOptionService();
    }

    @Test
    public void getSalePrice_shouldReturn90() {
        assertEquals(90.0, buyOptionService.getSalePrice(100.0, 10), 0);
    }

    @Test
    public void getSalePrice_shouldReturn180() {
        assertEquals(180.0, buyOptionService.getSalePrice(200.0, 10), 0);
    }

    @Test
    public void getSalePrice_shouldReturn500() {
        assertEquals(500.0, buyOptionService.getSalePrice(1000.0, 50.0), 0);
    }

}
