/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.repository;

import com.tacos.cloud.model.Ingredient;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ignis
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository{
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Iterable<Ingredient> findAll() {
        
        return jdbcTemplate.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbcTemplate.queryForObject("SELECT id, name, type FROM Ingredient WHERE id = ?", 
                this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("INSERT INTO Ingredient (id, name, type) values (?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
    
    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException{
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}
