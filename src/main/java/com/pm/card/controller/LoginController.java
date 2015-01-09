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

    private boolean checkStatus = false;
    
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
