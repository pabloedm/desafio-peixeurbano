package com.peixeurbano.pablo.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peixeurbano.pablo.desafio.dto.BuyOptionDTO;
import com.peixeurbano.pablo.desafio.service.BuyOptionService;

@RestController
public class BuyOptionController {

    @Autowired
    private BuyOptionService buyOptionService;

    @GetMapping("/v1/buyOption")
    public List<BuyOptionDTO> findAll() {
        return buyOptionService.findAll()
                .stream()
                .map(BuyOptionDTO::new)
                .collect(Collectors.toList());
    }

}
