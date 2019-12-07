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

import com.peixeurbano.pablo.desafio.dto.DealDTO;
import com.peixeurbano.pablo.desafio.dto.DealWithBuyOptionsDTO;
import com.peixeurbano.pablo.desafio.exception.NoContentException;
import com.peixeurbano.pablo.desafio.service.DealService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping("/v1/deals")
    @ApiOperation("Endpoint to list all deals")
    public List<DealDTO> findAll() {
        return dealService.findAll()
                .stream()
                .map(DealDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/deals/valid")
    @ApiOperation("Endpoint to list all valid deals")
    public List<DealDTO> findAllValidDeals() {
        return dealService.findAllValidDeals()
                .stream()
                .map(DealDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/deals/{id}")
    @ApiOperation("Endpoint to search a deal and its buy option by id")
    public DealWithBuyOptionsDTO findById(@PathVariable Integer id) {
        return new DealWithBuyOptionsDTO(dealService.findById(id).orElseThrow(NoContentException::new));
    }

    @PostMapping("/v1/deals")
    @ApiOperation("Endpoint to insert a new deal")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody DealDTO dto) {
        return dealService.insert(dto.getDeal());
    }

    @PutMapping("/v1/deals/{id}")
    @ApiOperation("Endpoint to update an existing deal")
    public void update(@RequestBody DealDTO dto) {
        dealService.update(dto.getDeal());
    }

    @PostMapping("/v1/deals/{id}/addOption/{optionId}")
    @ApiOperation("Endpoint to associate a deal with one buy option")
    public void addOption(@PathVariable Integer id, @PathVariable Integer optionId) {
        dealService.addOption(id, optionId);
    }

    @DeleteMapping("/v1/deals/{id}")
    @ApiOperation("Endpoint to delete a deal using id")
    public void delete(@PathVariable Integer id) {
        dealService.delete(id);
    }
}
