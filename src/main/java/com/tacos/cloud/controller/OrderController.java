/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.controller;

import com.tacos.cloud.model.Order;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ignis
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        
        if(errors.hasFieldErrors()){
            return "orderForm";
        }
        
        System.out.println("Order submitted " + order);
        return "redirect:/";
    }
    
}
