package com.peixeurbano.pablo.desafio.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peixeurbano.pablo.desafio.DesafioBusinessException;
import com.peixeurbano.pablo.desafio.model.Deal;
import com.peixeurbano.pablo.desafio.repository.BuyOptionRepository;
import com.peixeurbano.pablo.desafio.repository.DealRepository;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private BuyOptionRepository buyOptionRepository;

    @Transactional
    public Integer insert(Deal deal) {
        deal.setCreateDate(ZonedDateTime.now());
        deal.setUrl(deal.getTitle().replaceAll(" ", "+"));
        return dealRepository.save(deal).getId();
    }

    public void update() {

    }

    @Transactional
    public void delete(Integer id) {
        if (buyOptionRepository.existsByDealId(id)) {
            throw new DesafioBusinessException("cant_delete_deal_with_buy_options");
        }

        dealRepository.deleteById(id);
    }

    public void addOption() {

    }

    public void updateTotalSold() {

    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

}
