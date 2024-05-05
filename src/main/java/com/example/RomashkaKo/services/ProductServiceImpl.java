package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.respons.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {


    private static final Map<Integer, Product> productMap = new HashMap<>();

    private static final AtomicInteger idGenerator = new AtomicInteger();


    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product getProduct(int id) {
        return productMap.get(id);
    }

    @Override
    public BaseResponse createProduct(Product product) {

        if (product.getName().length() > 255)
            return new BaseResponse("Too large name",413);
        if (product.getDescription().length() > 4096)
            return new BaseResponse("Too large description",413);
        if (product.getPrice() < 0)
            return new BaseResponse("Price is negative",449);

        final int productId = idGenerator.incrementAndGet();
        product.setId(productId);
        productMap.put(productId, product);
        return new BaseResponse("OK",200);
    }



    @Override
    public boolean updateProduct(Product product,int id) {
        if (productMap.containsKey(id)){
            product.setId(id);
            productMap.put(id,product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productMap.containsKey(id)){
            productMap.remove(id);
            return true;
        }
        return false;
    }
}
