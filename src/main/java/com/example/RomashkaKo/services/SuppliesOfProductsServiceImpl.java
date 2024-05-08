package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.repositories.ProductsRepository;
import com.example.RomashkaKo.repositories.SuppliesOfProductsRepository;
import com.example.RomashkaKo.respons.ErrorsListResponse;
import com.example.RomashkaKo.respons.ParentResponse;
import com.example.RomashkaKo.respons.StatusResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuppliesOfProductsServiceImpl implements SuppliesOfProductsService {

    @Autowired
    private SuppliesOfProductsRepository suppliesOfProductsRepository;
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<SupplyOfProducts> getSupplies() {
        return suppliesOfProductsRepository.findAll();
    }

    @Override
    public SupplyOfProducts getSupply(int id) {
        if (suppliesOfProductsRepository.findById(id).isPresent())
            return suppliesOfProductsRepository.findById(id).get();
        return null;
    }

    @Override
    public ParentResponse createSupply(SupplyOfProducts supplyOfProducts, int productId) {
        Product product;
        if (productsRepository.findById(productId).isPresent()) {
            product = productsRepository.findById(productId).get();
            product.setCount(product.getCount() + supplyOfProducts.getCountOfSuppliedProduct());
            product.setInStock(true);
            supplyOfProducts.setProduct(product);
        }
        else return new StatusResponse("Non-existing product");
        try {
            suppliesOfProductsRepository.save(supplyOfProducts);
        } catch (ConstraintViolationException e) {
            return new ErrorsListResponse("Error",e.getConstraintViolations().stream().
                    map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
        productsRepository.save(product);
        return new StatusResponse("OK");
    }

    @Override
    public boolean updateSupply(SupplyOfProducts supplyOfProducts, int id, Integer productId) {
        if(suppliesOfProductsRepository.findById(id).isPresent()){
            supplyOfProducts.setId(id);
            if (productId != null) {
                if (productsRepository.findById(productId).isEmpty())
                    return false;
                supplyOfProducts.setProduct(productsRepository.findById(productId).get());
                suppliesOfProductsRepository.save(supplyOfProducts);
            }
        }
        return false;
    }

    @Override
    public boolean deleteSupply(int id) {
        if (suppliesOfProductsRepository.findById(id).isPresent()){
            suppliesOfProductsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
