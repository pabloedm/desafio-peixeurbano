package com.peixeurbano.pablo.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping("/v1/deals")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody DealDTO dto) {
        return dealService.insert(dto.getDeal());
    }

    @DeleteMapping("/v1/deals/{id}")
    public void delete(@PathVariable Integer id) {
        dealService.delete(id);
    }
}
