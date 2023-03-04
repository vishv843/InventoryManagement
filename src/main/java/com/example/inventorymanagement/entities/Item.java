package com.example.inventorymanagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    public String Id;
    public String name;
    public String description;
    public int price;

    public Item(String id, String name, String description, int price) {
        Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item() {

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
