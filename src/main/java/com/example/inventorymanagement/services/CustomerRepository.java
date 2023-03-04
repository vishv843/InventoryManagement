package com.example.inventorymanagement.services;

import com.example.inventorymanagement.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
