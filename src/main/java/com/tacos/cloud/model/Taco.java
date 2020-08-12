/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.model;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
/**
 *
 * @author ignis
 */
public class Taco {
    
    private Long Id;
    
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
        
    @Size(min=1, message="Name must choose at leasst 1 ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Taco{" + "Id=" + Id + ", name=" + name + ", ingredients=" + 
                ingredients + ", createdAt=" + createdAt + '}';
    }
    
}
