package com.peixeurbano.pablo.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.peixeurbano.pablo.desafio.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    @Query("update Deal set totalSold = totalSold + 1 where id = :buyOptionId ")
    @Modifying
    void increaseTotalSold(Integer buyOptionId);

    @Query("SELECT d "
            + "FROM Deal d "
            + "WHERE d.publishDate < current_time()"
            + "AND d.endDate > current_time() ")
    List<Deal> findAllValidDeals();

}
