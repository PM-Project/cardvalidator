/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.controller;

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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sunil
 */
@RestController
@Controller
public class LoginController {

    private boolean checkStatus = false;
    @Autowired
    private CardServiceImpl cardServiceImpl;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> testMethod() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ShoppingInfo shoppingInfo = restTemplate.getForObject("http://localhost:8080/myshop/cardInfo", ShoppingInfo.class);
            if (shoppingInfo.getUsername().equalsIgnoreCase("admin") && shoppingInfo.getPassword().equals("admin")) {
                for (CardDetails cardDetails : cardServiceImpl.getCardDetails()) {
                    if (cardDetails.getCardNumber().equals(shoppingInfo.getCardNumber())) {
                        if (cardDetails.getTotalBalance() >= shoppingInfo.getTotalBalance()) {
                            cardDetails.setTotalBalance(cardDetails.getTotalBalance()-shoppingInfo.getTotalBalance());
                            System.out.println("BALANCE IS "+(cardDetails.getTotalBalance()-shoppingInfo.getTotalBalance()));
                            cardServiceImpl.updateCurrentBalance(cardDetails); //update balance in database
                            return new ResponseEntity<>(shoppingInfo, HttpStatus.OK);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        model.addAttribute("message", "Spring Security Custom Form example");
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list() {
        try {
            UserAuthentication apiDomainTest = new UserAuthentication();
            apiDomainTest.setUsername("admin");
            apiDomainTest.setPassword("admin");
            checkStatus = true;
            return new ResponseEntity<>(apiDomainTest, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/cardInfo", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> validateCardInfo() {
        try {
            if (checkStatus == true) {
                CardDetails cardVerification = new CardDetails();
                cardVerification.setCardNumber("123456");
                cardVerification.setCardType("Visa");
                cardVerification.setTotalBalance(200);
                return new ResponseEntity<>(cardVerification, HttpStatus.OK);
            }

        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";

    }

}
