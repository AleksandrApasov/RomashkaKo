package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.repositories.ProductsRepository;
import com.example.RomashkaKo.respons.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;



    @Override
    public List<Product> getProducts(String name, Integer limit, Boolean isLowLimit,
                                     Boolean isInStock, Boolean sortByName,Boolean sortByPrice, Integer limitElements) {
        List<Product> products;
        if(name != null)
            products = productsRepository.findByNameContainingIgnoreCase(name);
        else if (limit != null) {
            if (isLowLimit == null)
                products = productsRepository.findByPrice(limit);
            else if (isLowLimit)
                products = productsRepository.findByPriceGreaterThan(limit);
            else products = productsRepository.findByPriceLessThan(limit);
        }
        else if ((isInStock != null) && (isInStock)) {
            products = productsRepository.findByInStock(isInStock);
        }
        else products = productsRepository.findAll();
        if (sortByName != null)
            products = products.stream().sorted(compareByName).collect(Collectors.toCollection(ArrayList::new));
        else if (sortByPrice != null)
            products = products.stream().sorted(compareByPrice).collect(Collectors.toCollection(ArrayList::new));
        if (limitElements != null)
        products = products.stream().limit(limitElements).collect(Collectors.toCollection(ArrayList::new));
        return products;

    }

    Comparator<Product> compareByName = Comparator
            .comparing(Product::getName, String.CASE_INSENSITIVE_ORDER);
    Comparator<Product> compareByPrice = Comparator
            .comparing(Product::getPrice);

    @Override
    public Product getProduct(int id) {
        if(productsRepository.findById(id).isPresent())
        return productsRepository.findById(id).get();
        return null;
    }

    @Override
    public BaseResponse createProduct(Product product) {

        if (product.getName().length() > 255)
            return new BaseResponse("Too large name",413);
        if (product.getDescription().length() > 4096)
            return new BaseResponse("Too large description",413);
        if (product.getPrice() < 0)
            return new BaseResponse("Price is negative",449);

        productsRepository.save(product);
        return new BaseResponse("OK",200);
    }



    @Override
    public boolean updateProduct(Product product,int id) {
        if (productsRepository.findById(id).isPresent()){
            product.setId(id);
            productsRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productsRepository.findById(id).isPresent()){
            productsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
