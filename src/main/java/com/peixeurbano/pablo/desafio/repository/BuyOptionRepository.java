package com.peixeurbano.pablo.desafio.repository;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peixeurbano.pablo.desafio.model.BuyOption;

public interface BuyOptionRepository extends JpaRepository<BuyOption, Integer> {

    void setCouponQuantity(Integer id, Long actualQuantityCoupon);

    void setCouponQuantityAndSetEndDate(Integer id, Long actualQuantityCoupon, ZonedDateTime now);

}
