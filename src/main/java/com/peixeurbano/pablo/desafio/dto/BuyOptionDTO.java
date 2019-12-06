package com.peixeurbano.pablo.desafio.dto;

import java.time.ZonedDateTime;

import com.peixeurbano.pablo.desafio.model.BuyOption;

public class BuyOptionDTO {

    private BuyOption buyOption;

    public BuyOptionDTO(BuyOption buyOption) {
        this.buyOption = buyOption;
    }

    public Integer getId() {
        return buyOption.getId();
    }

    public String getTitle() {
        return buyOption.getTitle();
    }

    public double getNormalPrice() {
        return buyOption.getNormalPrice();
    }

    public double getSalePrice() {
        return buyOption.getSalePrice();
    }

    public double getPercentageDiscount() {
        return buyOption.getPercentageDiscount();
    }

    public long getQuantityCoupon() {
        return buyOption.getQuantityCoupon();
    }

    public ZonedDateTime getStartDate() {
        return buyOption.getStartDate();
    }

    public ZonedDateTime getEndDate() {
        return buyOption.getEndDate();
    }
}
