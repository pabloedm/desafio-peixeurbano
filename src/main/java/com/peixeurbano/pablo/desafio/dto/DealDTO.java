package com.peixeurbano.pablo.desafio.dto;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peixeurbano.pablo.desafio.enumeration.DealTypeEnum;
import com.peixeurbano.pablo.desafio.model.Deal;

public class DealDTO {

    private Deal deal;

    public DealDTO() {
        deal = new Deal();
    }

    public DealDTO(Deal deal) {
        this.deal = deal;
    }

    @JsonIgnore
    public Deal getDeal() {
        return deal;
    }

    public Integer getId() {
        return deal.getId();
    }

    public void setId(Integer id) {
        deal.setId(id);
    }

    public String getTitle() {
        return deal.getTitle();
    }

    public void setTitle(String title) {
        deal.setTitle(title);
    }

    public String getText() {
        return deal.getText();
    }

    public void setText(String text) {
        deal.setText(text);
    }

    public ZonedDateTime getCreateDate() {
        return deal.getCreateDate();
    }

    public ZonedDateTime getPublishDate() {
        return deal.getPublishDate();
    }

    public void setPublishDate(ZonedDateTime publishDate) {
        deal.setPublishDate(publishDate);
    }

    public ZonedDateTime getEndDate() {
        return deal.getEndDate();
    }

    public void setEndDate(ZonedDateTime endDate) {
        deal.setEndDate(endDate);
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

    public void setType(DealTypeEnum type) {
        deal.setType(type);
    }

    public Long getExpiration() {
        if (deal.getPublishDate() == null || deal.getEndDate() == null) {
            return null;
        }
        return ChronoUnit.DAYS.between(deal.getPublishDate(), deal.getEndDate());
    }

}
