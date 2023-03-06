package com.example.inventorymanagement.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Map;

public class Invoice {

    public String invoiceId;
    public ArrayList<InvoiceItem> receipt;
    public int total;
    public Invoice(String invoiceId){
        this.invoiceId = invoiceId;
        receipt = new ArrayList<>();
    }
    public void addToReceipt(InvoiceItem invoiceItem){
        receipt.add(invoiceItem);
    }
}
