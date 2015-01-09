/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.service.impl;

import com.pm.card.dao.impl.CardDaoImpl;
import com.pm.card.domain.CardDetails;
import com.pm.card.service.CardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sunil
 */
@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardDaoImpl cardDaoImpl;
    @Override
    public List<CardDetails> getCardDetails() {
        return cardDaoImpl.getCardDetails();
    }

    @Override
    public void updateCurrentBalance(CardDetails cd) {
        cardDaoImpl.updateCurrentBalance(cd);
    }

    @Override
    public CardDetails findByCardDetails(String cardNumber) {
        return cardDaoImpl.findByCardDetails(cardNumber);
    }
    
}
