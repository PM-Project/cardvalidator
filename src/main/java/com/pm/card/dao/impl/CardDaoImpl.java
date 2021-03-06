/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.dao.impl;

import com.pm.card.dao.CardDao;
import com.pm.card.domain.CardDetails;
import com.pm.card.utils.SessionUtil;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sunil
 */
@Repository
public class CardDaoImpl extends SessionUtil implements CardDao{

    @Override
    public List<CardDetails> getCardDetails() {
        return getSession().createCriteria(CardDetails.class).list();
    }
    
    @Override
    public void updateCurrentBalance(CardDetails cd) {      
        System.out.println("BALANCE IS "+cd.getTotalBalance());
        
         CardDetails details=new CardDetails();
         details.setTotalBalance(10);
          getSession().update(details);
         System.out.println("Update is ");
    }

    @Override
    public CardDetails findByCardDetails(String cardNumber, double balance, String cvv) {
//         return (CardDetails) getSession().get(CardDetails.class, cardNumber); 
         return (CardDetails) getSession().createQuery("SELECT c FROM CardDetails c WHERE c.cardNumber = ? AND c.cvv = ? AND c.totalBalance > ?").setFetchSize(1)
                 .setParameter(0, cardNumber)
                 .setParameter(1, cvv)
                 .setParameter(2, balance)
                 .uniqueResult();
    }
    
}
