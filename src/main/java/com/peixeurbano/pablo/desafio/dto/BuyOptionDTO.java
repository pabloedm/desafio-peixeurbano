package com.peixeurbano.pablo.desafio.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peixeurbano.pablo.desafio.model.BuyOption;

public class BuyOptionDTO {

    private BuyOption buyOption;

    public BuyOptionDTO() {
        this.buyOption = new BuyOption();
    }

    public BuyOptionDTO(BuyOption buyOption) {
        this.buyOption = buyOption;
    }

    @JsonIgnore
    public BuyOption getBuyOption() {
        return buyOption;
    }

    public Integer getId() {
        return buyOption.getId();
    }

    public void setId(Integer id) {
        buyOption.setId(id);
    }

    public String getTitle() {
        return buyOption.getTitle();
    }

    public void setTitle(String title) {
        buyOption.setTitle(title);
    }

    public double getNormalPrice() {
        return buyOption.getNormalPrice();
    }

    public void setNormalPrice(double normalPrice) {
        buyOption.setNormalPrice(normalPrice);
    }

    public Double getSalePrice() {
        return buyOption.getSalePrice();
    }

    public double getPercentageDiscount() {
        return buyOption.getPercentageDiscount();
    }

    public void setPercentageDiscount(double percentageDiscount) {
        buyOption.setPercentageDiscount(percentageDiscount);
    }

    public long getQuantityCoupon() {
        return buyOption.getQuantityCoupon();
    }

    public void setQuantityCoupon(long quantityCoupon) {
        buyOption.setQuantityCoupon(quantityCoupon);
    }

    public ZonedDateTime getStartDate() {
        return buyOption.getStartDate();
    }

    public ZonedDateTime getEndDate() {
        return buyOption.getEndDate();
    }
}
