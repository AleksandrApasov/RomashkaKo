package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.repositories.ProductsPepository;
import com.example.RomashkaKo.respons.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsPepository productsPepository;

    private static final AtomicInteger idGenerator = new AtomicInteger();


    @Override
    public List<Product> getProducts(String name, int lowLimit) {
        if(name != null)
            return productsPepository.findByNameContainingIgnoreCase(name);
        if (lowLimit != 0)
            return productsPepository.findAll(lowLimit);
        return productsPepository.findAll();
    }

    @Override
    public Product getProduct(int id) {
        if(productsPepository.findById(id).isPresent())
        return productsPepository.findById(id).get();
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

        productsPepository.save(product);
        return new BaseResponse("OK",200);
    }



    @Override
    public boolean updateProduct(Product product,int id) {
        if (productsPepository.findById(id).isPresent()){
            product.setId(id);
            productsPepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productsPepository.findById(id).isPresent()){
            productsPepository.deleteById(id);
            return true;
        }
        return false;
    }
}
