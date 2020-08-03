/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.model;

import java.util.List;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
/**
 *
 * @author ignis
 */
public class Taco {
    
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    
    
    @Size(min=1, message="Name must choose at leasst 1 ingredient")
    private List<String> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Taco{" + "name=" + name + ", ingredients=" + ingredients + '}';
    }   
    
}
