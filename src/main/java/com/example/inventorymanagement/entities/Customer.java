package com.example.inventorymanagement.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Customer {
    @Id
    public String customerId;
    private String name;
    private String contact;
    @ElementCollection
    public Map<String, Integer> cart;
    public Customer(String customerId, String name, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
        cart = new HashMap<>();
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

}
