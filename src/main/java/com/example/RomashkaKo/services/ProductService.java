package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.respons.ErrorsListResponse;
import com.example.RomashkaKo.respons.ParentResponse;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String name, Integer limit, Boolean isLowLimit,
                              Boolean isInStock,Boolean sortByName,Boolean sortByPrice, Integer limitElements);
    Product getProduct(int id);
    ParentResponse createProduct(Product product);
    boolean updateProduct(Product product, int id);
    boolean deleteProduct(int id);
}
