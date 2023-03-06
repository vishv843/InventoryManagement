package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.Item;
import com.example.inventorymanagement.entities.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ItemService {
    @GetMapping ("/item")
    public ArrayList<Item> getAllItem(){
        return Memory.getAllItems();
    }
    @PostMapping("/item")
    public void addItem(@RequestBody Item item){
        Memory.addItem(item);
    }
    @GetMapping("/item/{id}")
    public Optional<Item> getItemById(@PathVariable String id){
        Optional<Item> item = Optional.of(Memory.getItem(id));
        return item;
    }
    @PutMapping("/item")
    public void updateItem(@RequestBody Item item){
        Memory.updateItem(item);
    }
    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable String id){
        Memory.deleteItem(id);
    }
}
