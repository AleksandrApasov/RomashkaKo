package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.respons.BaseResponse;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String name, int lowLimit);
    Product getProduct(int id);
    BaseResponse createProduct(Product product);
    boolean updateProduct(Product product, int id);
    boolean deleteProduct(int id);
}
