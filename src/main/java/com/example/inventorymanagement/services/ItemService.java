package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping ("/item")
    public ArrayList<Item> getAllItem(){
        ArrayList<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }
    @PostMapping("/item")
    public void addItem(@RequestBody Item item){
        itemRepository.save(item);
    }
    @GetMapping("/item/{id}")
    public Optional<Item> getItemById(@PathVariable String id){
        Optional<Item> item = itemRepository.findById(id);
        return item;
    }
    @PutMapping("/item")
    public void updateItem(@RequestBody Item item){
        itemRepository.save(item);
    }
    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable String id){
        itemRepository.deleteById(id);
    }
}
