package com.peixeurbano.pablo.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peixeurbano.pablo.desafio.model.Deal;
import com.peixeurbano.pablo.desafio.repository.DealRepository;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    public void insert() {

    }

    public void update() {

    }

    public void delete() {

    }

    public void addOption() {

    }

    public void updateTotalSold() {

    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

}
