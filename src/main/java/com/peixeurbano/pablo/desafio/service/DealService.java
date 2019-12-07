package com.peixeurbano.pablo.desafio.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peixeurbano.pablo.desafio.exception.DesafioBusinessException;
import com.peixeurbano.pablo.desafio.model.BuyOption;
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
        deal.setUrl(deal.getTitle().replace(" ", "+"));
        return dealRepository.save(deal).getId();
    }

    public void update(Deal deal) {
        Deal dealFromDb = dealRepository.findById(deal.getId()).orElseThrow(() -> new DesafioBusinessException("cant_find_deal"));
        dealFromDb.setPublishDate(deal.getPublishDate());
        dealFromDb.setEndDate(deal.getEndDate());
        dealFromDb.setTitle(deal.getTitle());
        dealFromDb.setText(deal.getText());
        dealFromDb.setType(deal.getType());
        dealRepository.save(dealFromDb);
    }

    @Transactional
    public void delete(Integer id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new DesafioBusinessException("cant_find_deal"));
        if (buyOptionRepository.existsByDealId(id)) {
            throw new DesafioBusinessException("cant_delete_deal_with_buy_options");
        }
        dealRepository.delete(deal);
    }

    @Transactional
    public void addOption(Integer id, Integer optionId) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new DesafioBusinessException("cant_find_deal"));
        BuyOption buyOption = buyOptionRepository.findById(optionId).orElseThrow(() -> new DesafioBusinessException("cant_find_buy_option"));
        buyOption.setDeal(deal);
        buyOptionRepository.save(buyOption);
    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    public Optional<Deal> findById(Integer id) {
        return dealRepository.findById(id);
    }

}
