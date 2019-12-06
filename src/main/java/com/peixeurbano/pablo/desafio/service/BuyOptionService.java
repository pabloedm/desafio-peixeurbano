package com.peixeurbano.pablo.desafio.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peixeurbano.pablo.desafio.exception.DesafioBusinessException;
import com.peixeurbano.pablo.desafio.model.BuyOption;
import com.peixeurbano.pablo.desafio.repository.BuyOptionRepository;
import com.peixeurbano.pablo.desafio.repository.DealRepository;

@Service
public class BuyOptionService {

    @Autowired
    private BuyOptionRepository buyOptionRepository;
    @Autowired
    private DealRepository dealRepository;

    public List<BuyOption> findAll() {
        return buyOptionRepository.findAll();
    }

    public void delete(Integer id) {
        buyOptionRepository.deleteById(id);
    }

    @Transactional
    public void sellUnit(Integer id) {
        BuyOption buyOption = buyOptionRepository.findById(id).orElseThrow(() -> new DesafioBusinessException("buy_option_not_found"));
        ZonedDateTime now = ZonedDateTime.now();
        if (now.isAfter(buyOption.getDeal().getEndDate())) {
            throw new DesafioBusinessException("deal_is_expired");
        }
        if (buyOption.getEndDate() != null && now.isAfter(buyOption.getEndDate())) {
            throw new DesafioBusinessException("buy_option_is_expired");
        }
        if (NumberUtils.LONG_ZERO.equals(buyOption.getQuantityCoupon())) {
            throw new DesafioBusinessException("buy_option_is_sold_out");
        }

        Long actualQuantityCoupon = buyOption.getQuantityCoupon() - 1;
        if (NumberUtils.LONG_ZERO.equals(actualQuantityCoupon)) {
            buyOptionRepository.setCouponQuantityAndSetEndDate(id, actualQuantityCoupon, now);
        } else {
            buyOptionRepository.setCouponQuantity(id, actualQuantityCoupon);
        }

        dealRepository.increaseTotalSold(id);
    }
}
