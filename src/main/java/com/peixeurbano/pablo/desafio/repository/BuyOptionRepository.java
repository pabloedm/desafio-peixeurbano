package com.peixeurbano.pablo.desafio.repository;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.peixeurbano.pablo.desafio.model.BuyOption;

public interface BuyOptionRepository extends JpaRepository<BuyOption, Integer> {

    @Query("update BuyOption set quantityCoupon = :actualQuantityCoupon where id = :id")
    @Modifying
    void setCouponQuantity(Integer id, Long actualQuantityCoupon);

    @Query("update BuyOption set quantityCoupon = :actualQuantityCoupon, endDate = :now where id = :id")
    @Modifying
    void setCouponQuantityAndSetEndDate(Integer id, Long actualQuantityCoupon, ZonedDateTime now);

    boolean existsByDealId(Integer dealId);

}
