package com.peixeurbano.pablo.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peixeurbano.pablo.desafio.dto.DealDTO;
import com.peixeurbano.pablo.desafio.service.DealService;

@RestController
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping("/v1/deals")
    public List<DealDTO> findAll() {
        return dealService.findAll()
                .stream()
                .map(DealDTO::new)
                .collect(Collectors.toList());
    }
}
