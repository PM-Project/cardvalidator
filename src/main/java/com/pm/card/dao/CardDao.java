/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.dao;

import com.pm.card.domain.CardDetails;
import java.util.List;

/**
 *
 * @author sunil
 */
public interface CardDao {
    public List<CardDetails> getCardDetails();
     public void updateCurrentBalance(CardDetails cd) ;
}
