/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.card.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sunil
 */
@Controller
public class CardValidation {

    
    @RequestMapping(value = "/login")
    public String getCardValidation(){
        System.out.println("CARD VALIDAION");
        return "index";
    }
}
