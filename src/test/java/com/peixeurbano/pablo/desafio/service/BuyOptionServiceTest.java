package com.peixeurbano.pablo.desafio.service;

import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.peixeurbano.pablo.desafio.exception.DesafioBusinessException;
import com.peixeurbano.pablo.desafio.model.BuyOption;
import com.peixeurbano.pablo.desafio.model.Deal;
import com.peixeurbano.pablo.desafio.repository.BuyOptionRepository;
import com.peixeurbano.pablo.desafio.repository.DealRepository;

@RunWith(MockitoJUnitRunner.class)
public class BuyOptionServiceTest {

    @InjectMocks
    private BuyOptionService buyOptionService;
    @Mock
    private BuyOptionRepository buyOptionRepository;
    @Mock
    private DealRepository dealRepository;

    @Test(expected = DesafioBusinessException.class)
    public void sellUnit_inexistentBuyOptionShouldThrowException() {
        Integer id = 1;
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.empty());
        buyOptionService.sellUnit(id);
    }

    @Test(expected = DesafioBusinessException.class)
    public void sellUnit_buyOptionWithoutDealShouldThrowException() {
        Integer id = 1;
        BuyOption buyOption = new BuyOption();
        buyOption.setId(id);
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.of(buyOption));
        buyOptionService.sellUnit(id);
    }

    @Test(expected = DesafioBusinessException.class)
    public void sellUnit_buyOptionWithDealExpiredShouldThrowException() {
        Integer id = 1;
        Deal deal = new Deal();
        deal.setId(1);
        deal.setEndDate(ZonedDateTime.of(2019, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        BuyOption buyOption = new BuyOption();
        buyOption.setId(id);
        buyOption.setDeal(deal);
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.of(buyOption));
        buyOptionService.sellUnit(id);
    }

    @Test(expected = DesafioBusinessException.class)
    public void sellUnit_buyOptionExpiredShouldThrowException() {
        Integer id = 1;
        Deal deal = new Deal();
        deal.setId(1);
        deal.setEndDate(ZonedDateTime.of(2021, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        BuyOption buyOption = new BuyOption();
        buyOption.setId(id);
        buyOption.setEndDate(ZonedDateTime.of(2019, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        buyOption.setDeal(deal);
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.of(buyOption));
        buyOptionService.sellUnit(id);
    }

    @Test(expected = DesafioBusinessException.class)
    public void sellUnit_buyOptionSoldOutShouldThrowException() {
        Integer id = 1;
        Deal deal = new Deal();
        deal.setId(1);
        deal.setEndDate(ZonedDateTime.of(2021, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        BuyOption buyOption = new BuyOption();
        buyOption.setId(id);
        buyOption.setQuantityCoupon(0L);
        buyOption.setEndDate(ZonedDateTime.of(2021, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        buyOption.setDeal(deal);
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.of(buyOption));
        buyOptionService.sellUnit(id);
    }

    @Test
    public void sellUnit_buyOptionWithOneItemShouldSetEndDate() {
        Integer id = 1;
        Integer dealId = 1;

        Deal deal = new Deal();
        deal.setId(dealId);
        deal.setEndDate(ZonedDateTime.of(2021, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        BuyOption buyOption = new BuyOption();
        buyOption.setId(id);
        buyOption.setQuantityCoupon(1L);
        buyOption.setEndDate(ZonedDateTime.of(2021, 12, 1, 1, 1, 1, 1, ZoneOffset.UTC));
        buyOption.setDeal(deal);
        Mockito.when(buyOptionRepository.findById(id)).thenReturn(Optional.of(buyOption));
        buyOptionService.sellUnit(id);

        Mockito.verify(buyOptionRepository).setCouponQuantityAndSetEndDate(ArgumentMatchers.eq(1), ArgumentMatchers.eq(0L), ArgumentMatchers.any());
        Mockito.verify(dealRepository).increaseTotalSold(ArgumentMatchers.eq(1));
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
