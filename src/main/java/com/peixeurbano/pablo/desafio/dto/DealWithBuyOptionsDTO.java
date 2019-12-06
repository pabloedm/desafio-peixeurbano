package com.peixeurbano.pablo.desafio.dto;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peixeurbano.pablo.desafio.enumeration.DealTypeEnum;
import com.peixeurbano.pablo.desafio.model.Deal;

public class DealWithBuyOptionsDTO {

    private Deal deal;

    public DealWithBuyOptionsDTO() {
        deal = new Deal();
    }

    public DealWithBuyOptionsDTO(Deal deal) {
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

    public long getExpiration() {
        return ChronoUnit.DAYS.between(deal.getPublishDate(), deal.getEndDate());
    }

    public List<BuyOptionDTO> getBuyOptions() {
        return deal.getBuyOptions().stream().map(BuyOptionDTO::new).collect(Collectors.toList());
    }

}
