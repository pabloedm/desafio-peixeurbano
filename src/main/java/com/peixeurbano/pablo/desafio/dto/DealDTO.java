package com.peixeurbano.pablo.desafio.dto;

import java.time.ZonedDateTime;

import com.peixeurbano.pablo.desafio.enumeration.DealTypeEnum;
import com.peixeurbano.pablo.desafio.model.Deal;

public class DealDTO {

    private Deal deal;

    public DealDTO(Deal deal) {
        this.deal = deal;
    }

    public Integer getId() {
        return deal.getId();
    }

    public String getTile() {
        return deal.getTitle();
    }

    public String getText() {
        return deal.getText();
    }

    public ZonedDateTime getCreateDate() {
        return deal.getCreateDate();
    }

    public ZonedDateTime getPublishDate() {
        return deal.getPublishDate();
    }

    public ZonedDateTime getEndDate() {
        return deal.getEndDate();
    }

    public String getUrl() {
        return deal.getUrl();
    }

    public Long getTotalSold() {
        return deal.getTotalSold();
    }

    public DealTypeEnum getType() {
        return deal.getType();
    }

}
