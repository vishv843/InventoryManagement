package com.example.inventorymanagement.entities;

import java.util.ArrayList;
import java.util.Optional;

public class Memory {
    public static ArrayList<Customer> customerMemory;
    public static ArrayList<Item> itemMemory;
    public Memory(){
        customerMemory = new ArrayList<>();
        itemMemory = new ArrayList<>();
    }

    public static ArrayList<Customer> getAllCustomers(){
        return customerMemory;
    }
    public static void addCustomer(Customer customer){
        customerMemory.add(customer);
    }

    public static void deleteCustomer(String customerId){
        for (Customer customer: customerMemory) {
            if(customer.customerId.equals(customerId)){
                customerMemory.remove(customer);
                break;
            }
        }
    }

    public static void updateCustomer(Customer customer){
        for (Customer iterateCustomer: customerMemory) {
            if(iterateCustomer.customerId.equals(customer.customerId)){
                customerMemory.remove(iterateCustomer);
                customerMemory.add(customer);
                break;
            }
        }
    }

    public static Customer getCustomer(String customerId){
        for (Customer customer: customerMemory) {
            if (customer.customerId.equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public static ArrayList<Item> getAllItems(){
        return itemMemory;
    }

    public static void addItem(Item item){
        itemMemory.add(item);
    }

    public static void deleteItem(String itemId){
        for (Item item:itemMemory) {
            if(item.Id.equals(itemId)){
                itemMemory.remove(item);
                return;
            }
        }
    }

    public  static void updateItem(Item item){
        for (Item iterateItem: itemMemory) {
            if(iterateItem.Id.equals(item.Id)){
                itemMemory.remove(iterateItem);
                itemMemory.add(item);
                break;
            }
        }
    }

    public static Item getItem(String itemId){
        for (Item item: itemMemory) {
            if(item.Id.equals(itemId)){
                return item;
            }
        }
        return null;
    }


}
