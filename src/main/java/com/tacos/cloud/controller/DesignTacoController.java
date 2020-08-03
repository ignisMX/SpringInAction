/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.controller;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.tacos.cloud.model.Taco;
import com.tacos.cloud.model.Ingredient;
import com.tacos.cloud.repository.IngredientRepository;
import java.util.ArrayList;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/design")
public class DesignTacoController {
    
    private IngredientRepository ingredientRepository;
    
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));
        
        Ingredient.Type [] types = Ingredient.Type.values();
        for (Ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        
        model.addAttribute("design", new Taco());
        return "design";
    }
    
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        
        if(errors.hasErrors()){
            return "design";
        }
        
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
    }
}
