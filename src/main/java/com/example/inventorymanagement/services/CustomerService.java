package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.CartItem;
import com.example.inventorymanagement.entities.Customer;
import com.example.inventorymanagement.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemService itemService;
    @GetMapping("/customer")
    public ArrayList<Customer> getAllCustomer(){
        ArrayList<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }
    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }
    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }
    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id){
        customerRepository.deleteById(id);
    }
    @PutMapping("customer/{id}")
    @ResponseBody
    public String addItemToCart(@RequestBody CartItem cartItem, @PathVariable String id){
        Optional<Customer> customer = customerRepository.findById(id);
        Optional<Item> item = itemService.getItemById(cartItem.ItemId);
        return checkCustomer(customer, item, cartItem);
    }
    @GetMapping("customer/{id}/cart")
    public Map<String, Integer> getCart(@PathVariable String id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get().getCart();
        }
        return null;
    }

    @GetMapping("customer/{id}/cart/total")
    @ResponseBody
    public String getTotal(@PathVariable String id){
        Map<String, Integer> cart = getCart(id);
        int checkoutCost = 0;
        for (Map.Entry<String, Integer> cartItem : cart.entrySet()) {
            Optional<Item> item = itemService.getItemById(cartItem.getKey());
            if(item.isPresent())
                checkoutCost += item.get().price*cartItem.getValue();
        }
        return "Total cost: " + checkoutCost;
    }

    @DeleteMapping("customer/{id}/cart/{itemId}")
    @ResponseBody
    public String deleteItem(@PathVariable String id, @PathVariable String itemId){
        Map<String, Integer> cart = getCart(id);
        if(cart.containsKey(itemId)){
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer.isPresent()){
                customer.get().deleteFromCart(itemId);
                customerRepository.save(customer.get());
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
            customerRepository.save(customer.get());
        }
        else
            return "No such item";
        return "Successful";
    }
}
