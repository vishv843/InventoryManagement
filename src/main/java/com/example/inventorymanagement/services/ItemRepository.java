package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
}
