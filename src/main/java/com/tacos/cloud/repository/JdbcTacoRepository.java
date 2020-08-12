/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.repository;

import com.tacos.cloud.model.Taco;
import com.tacos.cloud.model.Ingredient;

import java.util.Date;
import java.util.Arrays;

import java.sql.Types;
import java.sql.Timestamp;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

/**
 *
 * @author ignis
 */
@Repository
public class JdbcTacoRepository implements TacoRepository{
    
    private JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(Ingredient ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        
        return taco;
    }
    
    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
        "INSERT INTO taco (name, createdAt) values (?,?)",
                Types.VARCHAR, Types.TIMESTAMP)
                .newPreparedStatementCreator(Arrays.asList(
                        taco.getName(), 
                        new Timestamp(taco.getCreatedAt().getTime())));
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
    
    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
    
        jdbcTemplate.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?,?)",
                tacoId, ingredient.getId());
    }
}
