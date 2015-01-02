/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.controller;

import com.pm.card.domain.CardVerification;
import com.pm.card.domain.UserAuthentication;
import com.pm.card.utils.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sunil
 */
@RestController
@Controller
public class LoginController {

    private boolean checkStatus = false;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String checkAuthenticate() {
//        System.out.println("CheckAuthenticate");
//        RestTemplate restTemplate = new RestTemplate();
//        UserAuthentication authentication = restTemplate.getForObject("http://localhost:8080/MUMFood/resttest", UserAuthentication.class);
//        System.out.println("Username is "+authentication.getUsername()+" Password "+authentication.getPassword());
//        return "";
//    }
//    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model, Principal principal) {
//        System.out.println("WELCOME PAGE ");
//        String name = principal.getName();
//        
//        System.out.println("Card UserName "+principal.getName()+"PASSWORD "+principal);
//        RestTemplate restTemplate = new RestTemplate();
//        UserAuthentication authentication = restTemplate.getForObject("http://localhost:8080/MUMFood/resttest", UserAuthentication.class);
//        System.out.println("Username is "+authentication.getUsername()+" Password "+authentication.getPassword());
//        if(authentication.getUsername().equals(name)){
//            System.out.println("EQUAL*************");
//            return "success";
//        }
//        model.addAttribute("username", name);
//        model.addAttribute("message", "Spring Security Custom Form example");
//        return "hello";
//    }    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list() {
        try {
            System.out.println("AUTH::::::::::::::");
            UserAuthentication apiDomainTest = new UserAuthentication();
            apiDomainTest.setUsername("admin");
            apiDomainTest.setPassword("admin");
            System.out.println(":::::::::::::::");
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

            System.out.println("CARD INFO::::::::::::::");

//            System.out.println("In the Password Function ");
//            String strEncrypt="3214567891234567";
//            String hased = BCrypt.hashpw(strEncrypt, BCrypt.gensalt(12));
//            System.out.println("Name is " + hased);
//            
//            if (BCrypt.checkpw("sunil123sunil123", hased)) {
//                System.out.println("It matches");
//            } else {
//                System.out.println("It does not match");
//            }
            {
                if (checkStatus == true) {
                    System.out.println("Status is " + checkStatus);
                    CardVerification cardVerification = new CardVerification();
                    cardVerification.setCardNumber("123456");
                    cardVerification.setCardType("Visa");
                    cardVerification.setTotalBalance(200);
                    System.out.println(":::::::::::::::");
                    return new ResponseEntity<>(cardVerification, HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
//    public String verificationByCardDetails(ModelMap model) {
//        System.out.println("WELCOME PAGE ");
//        
//        RestTemplate restTemplate = new RestTemplate();
//        CardVerification cardVerification = restTemplate.getForObject("http://localhost:8080/MUMFood/validateCard", CardVerification.class);
//        
//        System.out.println("CARD NUMBER is "+cardVerification.getCardNumber()+" TYPE "+cardVerification.getCardType());
//        
//        return "hello";
//    }  
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        System.out.println("Login method");
        return "login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        System.out.println("Login error");
        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        System.out.println("LOGIN");
        return "login";

    }

}
