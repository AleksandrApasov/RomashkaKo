package com.example.RomashkaKo.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private boolean inStock;
}
