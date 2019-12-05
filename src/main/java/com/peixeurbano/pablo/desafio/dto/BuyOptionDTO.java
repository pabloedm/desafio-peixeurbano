package com.peixeurbano.pablo.desafio.dto;

import com.peixeurbano.pablo.desafio.model.BuyOption;

public class BuyOptionDTO {

    private BuyOption buyOption;

    public BuyOptionDTO(BuyOption buyOption) {
        this.buyOption = buyOption;
    }

    public Integer getId() {
        return buyOption.getId();
    }
}
