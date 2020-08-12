/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.controller;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacos.cloud.model.Ingredient;
import com.tacos.cloud.repository.IngredientRepository;
/**
 *
 * @author ignis
 */
@Component
public class IngredientConverter implements Converter<String, Ingredient>{
    
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    
    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
