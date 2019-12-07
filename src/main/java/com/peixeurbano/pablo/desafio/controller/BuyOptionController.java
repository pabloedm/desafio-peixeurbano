package com.peixeurbano.pablo.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.peixeurbano.pablo.desafio.dto.BuyOptionDTO;
import com.peixeurbano.pablo.desafio.service.BuyOptionService;

@RestController
public class BuyOptionController {

    @Autowired
    private BuyOptionService buyOptionService;

    @GetMapping("/v1/buyOptions")
    public List<BuyOptionDTO> findAll() {
        return buyOptionService.findAll()
                .stream()
                .map(BuyOptionDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/v1/buyOptions/{buyOptionId}/sellUnit")
    public void sellUnit(@PathVariable Integer buyOptionId) {
        buyOptionService.sellUnit(buyOptionId);
    }

    @DeleteMapping("/v1/buyOptions/{id}")
    public void delete(@PathVariable Integer id) {
        buyOptionService.delete(id);
    }

    @PostMapping("/v1/buyOptions")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody BuyOptionDTO dto) {
        buyOptionService.insert(dto.getBuyOption());
    }

    @PutMapping("/v1/buyOptions/{id}")
    public void update(@RequestBody BuyOptionDTO dto) {
        buyOptionService.update(dto.getBuyOption());
    }

}
