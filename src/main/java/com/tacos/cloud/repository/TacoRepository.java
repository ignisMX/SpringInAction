/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.repository;

import com.tacos.cloud.model.Taco;
/**
 *
 * @author ignis
 */
public interface TacoRepository {
    
    Taco save(Taco design);
    
}
