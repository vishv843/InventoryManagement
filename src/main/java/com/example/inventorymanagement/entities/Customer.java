package com.example.inventorymanagement.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

public class Customer {
    public String customerId;
    private String name;
    private String contact;
    public Map<String, Integer> cart = new HashMap<>();
    public Customer(String customerId, String name, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
    }
    public Customer(){

    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void addToCart(CartItem cartItem){
        this.cart.put(cartItem.ItemId, cartItem.quantity);
    }
    public void deleteFromCart(String itemId){
        this.cart.remove(itemId);
    }

    public void deleteCart(){
        this.cart.clear();
    }

}
