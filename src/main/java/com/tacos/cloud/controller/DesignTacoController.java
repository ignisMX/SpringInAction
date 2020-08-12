/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.tacos.cloud.model.Taco;
import com.tacos.cloud.model.Order;
import com.tacos.cloud.model.Ingredient;
import com.tacos.cloud.repository.TacoRepository;
import com.tacos.cloud.repository.IngredientRepository;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author ignis
 */
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    
    private TacoRepository tacoRepository;
    
    private final IngredientRepository ingredientRepository;
    
    
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository,
            TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }
    
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));
        
        Ingredient.Type [] types = Ingredient.Type.values();
        for (Ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        
        return "design";
    }
    
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        
            if(errors.hasErrors()){
            return "design";
        }
        
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
    }
    
    @ModelAttribute( name = "order")
    public Order order(){
        return new Order();
    }
    
    @ModelAttribute( name = "taco")
    public Taco taco(){
        return new Taco();
    }
}
