package com.peixeurbano.pablo.desafio.dto;

import com.peixeurbano.pablo.desafio.model.Deal;

public class DealDTO {

    private Deal deal;

    public DealDTO(Deal deal) {
        this.deal = deal;
    }

    public Integer getId() {
        return deal.getId();
    }

}
