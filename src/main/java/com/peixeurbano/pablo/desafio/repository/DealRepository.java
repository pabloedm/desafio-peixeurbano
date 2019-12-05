package com.peixeurbano.pablo.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peixeurbano.pablo.desafio.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    void increaseTotalSold(Integer buyOptionId);

}
