/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.cloud.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author ignis
 */
public class Order {
    
    private Long Id;
    
    @NotBlank(message="Name is required")
    private String deliveryName;
    
    @NotBlank(message="Name is required")
    private String deliveryStreet;
    
    @NotBlank(message="Name is required")
    private String deliveryCity;
    
    @NotBlank(message="Name is required")
    private String deliveryState;
    
    @NotBlank(message="Name is required")
    private String deliveryZip;
    
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    
    private Date placeddAt;
    
    private List<Taco> tacos = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public Date getPlaceddAt() {
        return placeddAt;
    }

    public void setPlaceddAt(Date placeddAt) {
        this.placeddAt = placeddAt;
    }
    
    public void addDesign(Taco taco){
        this.tacos.add(taco);
    }

    public List<Taco> getTacos() {
        return tacos;
    }
    
    @Override
    public String toString() {
        return "Order{" + "Id=" + Id + ", name=" + deliveryName + ", street=" + deliveryStreet +
                ", city=" + deliveryCity + ", state=" + deliveryState + ", zip=" + deliveryZip + 
                ", ccNumber=" + ccNumber + ", ccExpiration=" + ccExpiration + 
                ", ccCVV=" + ccCVV + ", createdAt=" + placeddAt + '}';
    }
}
