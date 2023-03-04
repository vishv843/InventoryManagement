package com.example.inventorymanagement.entities;

public class CartItem {
    public String ItemId;
    public int quantity;

    public CartItem(String itemId, int quantity) {
        ItemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return ItemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
