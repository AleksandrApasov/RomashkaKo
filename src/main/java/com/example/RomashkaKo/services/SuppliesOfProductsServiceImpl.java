package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.repositories.ProductsRepository;
import com.example.RomashkaKo.repositories.SuppliesOfProductsRepository;
import com.example.RomashkaKo.respons.BaseResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
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
    public BaseResponse createSupply(SupplyOfProducts supplyOfProducts, int productId) {
        if (productsRepository.findById(productId).isPresent())
            supplyOfProducts.setProduct(productsRepository.findById(productId).get());
        else return new BaseResponse("Non-existing product", 449);
        try {
            suppliesOfProductsRepository.save(supplyOfProducts);
        } catch (ConstraintViolationException e) {
            return new BaseResponse(e.getConstraintViolations().stream().
                    map(ConstraintViolation::getMessage).collect(Collectors.toList()), 500);
        }
        return new BaseResponse("OK", 200);
    }

    @Override
    public boolean updateSupply(SupplyOfProducts supplyOfProducts, int id) {
        return false;
    }

    @Override
    public boolean deleteSupply(int id) {
        return false;
    }
}
