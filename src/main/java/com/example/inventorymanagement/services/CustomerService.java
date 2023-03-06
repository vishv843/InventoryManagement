package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerService {

    @GetMapping("/customer")
    public ArrayList<Customer> getAllCustomer(){
        return Memory.getAllCustomers();
    }
    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer){
        Memory.addCustomer(customer);
    }
    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id){
        return Optional.of(Memory.getCustomer(id));
    }
    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer){
        Memory.updateCustomer(customer);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id){
        Memory.deleteCustomer(id);
    }
    @PutMapping("customer/{id}")
    @ResponseBody
    public String addItemToCart(@RequestBody CartItem cartItem, @PathVariable String id){
        Optional<Customer> customer = Optional.of(Memory.getCustomer(id));
        Optional<Item> item = Optional.of(Memory.getItem(cartItem.ItemId));
        return checkCustomer(customer, item, cartItem);
    }
    @GetMapping("customer/{id}/cart")
    public Map<String, Integer> getCart(@PathVariable String id){
        Optional<Customer> customer = Optional.of(Memory.getCustomer(id));
        if(customer.isPresent()){
            return customer.get().getCart();
        }
        return null;
    }

    @GetMapping("customer/{id}/cart/total")
    @ResponseBody
    public Invoice getTotal(@PathVariable String id){
        Map<String, Integer> cart = getCart(id);
        int checkoutCost = 0;
        InvoiceItem invoiceItem = new InvoiceItem();
        Invoice invoice = new Invoice(id + "invoice");
        for (Map.Entry<String, Integer> cartItem : cart.entrySet()) {
            Optional<Item> item = Optional.of(Memory.getItem(cartItem.getKey()));
            if(item.isPresent()) {
                checkoutCost += item.get().price * cartItem.getValue();
                invoiceItem.ItemId = item.get().Id;
                invoiceItem.quantity = cartItem.getValue();
                invoiceItem.price = item.get().price;
                invoice.addToReceipt(invoiceItem);
            }
        }
        invoice.total = checkoutCost;
        return invoice;
    }

    @DeleteMapping("customer/{id}/cart")
    @ResponseBody
    public String deleteCart(@PathVariable String id){
        Optional<Customer> customer = getCustomerById(id);
        if(customer.isPresent()) {
            customer.get().deleteCart();
            Memory.updateCustomer(customer.get());
            return "Delete cart successful";
        }
        else
            return "Customer not found";
    }

    @DeleteMapping("customer/{id}/cart/{itemId}")
    @ResponseBody
    public String deleteItem(@PathVariable String id, @PathVariable String itemId){
        Map<String, Integer> cart = getCart(id);
        if(cart.containsKey(itemId)){
            Optional<Customer> customer = Optional.of(Memory.getCustomer(id));
            if(customer.isPresent()){
                customer.get().deleteFromCart(itemId);
                Memory.updateCustomer(customer.get());
            }
            else
                return "No such customer";
        }
        else
            return "No such item is present in cart";
        return "Delete Successful";
    }

    public String checkCustomer(Optional<Customer> customer, Optional<Item> item, CartItem cartItem){
        if(customer.isPresent())
            return checkItem(customer, item, cartItem);
        else
            return "No such customer";
    }
    public String checkItem(Optional<Customer> customer, Optional<Item> item, CartItem cartItem){
        if(item.isPresent()){
            customer.get().addToCart(cartItem);
            Memory.updateCustomer(customer.get());
        }
        else
            return "No such item";
        return "Successful";
    }
}
