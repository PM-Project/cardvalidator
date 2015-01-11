/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.controller;

import com.pm.card.dao.CardDao;
import com.pm.card.domain.CardDetails;
import com.pm.card.domain.UserAuthentication;
import com.pm.card.service.impl.CardServiceImpl;
import com.pm.card.utils.ShoppingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sunil
 */
@RestController
@Controller
public class LoginController {

    
    @Autowired
    private CardDao cardDao;

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public @ResponseBody String testMethod(@RequestParam("cardNo") String cardNo, 
            @RequestParam("balance") double balance,
            @RequestParam("cvv") String cvv) 
    {
            CardDetails details = cardDao.findByCardDetails(cardNo,balance,cvv);
            if(details != null)
                return "success";
            else
                return "fail";
    }


}
