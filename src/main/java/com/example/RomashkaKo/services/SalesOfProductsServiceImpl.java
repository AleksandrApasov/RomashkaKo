package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.model.SaleOfProducts;
import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.repositories.ProductsRepository;
import com.example.RomashkaKo.repositories.SalesOfProductsRepository;
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
public class SalesOfProductsServiceImpl implements SalesOfProductsService {

    @Autowired
    private SuppliesOfProductsRepository suppliesOfProductsRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private SalesOfProductsRepository salesOfProductsRepository;

    @Override
    public List<SaleOfProducts> getSales() {
        return salesOfProductsRepository.findAll();
    }

    @Override
    public SaleOfProducts getSale(int id) {
        if (salesOfProductsRepository.findById(id).isPresent())
            return salesOfProductsRepository.findById(id).get();
        return null;
    }

    @Override
    public ParentResponse createSale(SaleOfProducts saleOfProducts, int productId) {
        Product product;
        if (productsRepository.findById(productId).isPresent()) {
            product = productsRepository.findById(productId).get();
            if (product.getCount() < saleOfProducts.getCountOfSoldProduct())
                return new StatusResponse("Count of sales product more than count of product in stock");
            product.setCount(product.getCount() - saleOfProducts.getCountOfSoldProduct());
            saleOfProducts.setProduct(product);
        } else return new StatusResponse("Non-existing product");
        try {
            salesOfProductsRepository.save(saleOfProducts);
        } catch (ConstraintViolationException e) {
            return new ErrorsListResponse("Error", e.getConstraintViolations().stream().
                    map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
        if (product.getCount() > 0)
            product.setInStock(true);
        productsRepository.save(product);
        return new StatusResponse("OK");
    }

    @Override
    public ParentResponse updateSale(SaleOfProducts saleOfProducts, int id, Integer productId) {
        SaleOfProducts saleOfProductsOld;
        Product productNew = null;
        Product productOld;
        int countDifference = 0;
        if (salesOfProductsRepository.findById(id).isPresent()) {
            saleOfProductsOld = salesOfProductsRepository.findById(id).get();
            productOld = saleOfProductsOld.getProduct();
            saleOfProducts.setId(id);
            if (productId != null) {
                if (productsRepository.findById(productId).isEmpty())
                    return new StatusResponse("Non-existing product");
                productNew = productsRepository.findById(productId).get();
                productOld.setCount(productOld.getCount() + saleOfProductsOld.getCountOfSoldProduct());
                if (productNew.getCount() < saleOfProducts.getCountOfSoldProduct())
                    return new StatusResponse("Count of sales product more than count of product in stock");
                productNew.setCount(productNew.getCount() - saleOfProducts.getCountOfSoldProduct());
                productNew.setInStock(productNew.getCount() > 0);
                saleOfProducts.setProduct(productNew);

            } else {
                countDifference = saleOfProductsOld.getCountOfSoldProduct() - saleOfProducts.getCountOfSoldProduct();
                productOld.setCount(productOld.getCount() + countDifference);
                productOld.setInStock(productOld.getCount() > 0);
                saleOfProducts.setProduct(productOld);
            }
            try {
                salesOfProductsRepository.save(saleOfProducts);
            } catch (ConstraintViolationException e) {
                return new ErrorsListResponse("Error", e.getConstraintViolations().stream().
                        map(ConstraintViolation::getMessage).collect(Collectors.toList()));
            }
            if (productNew != null)
                productsRepository.save(productNew);
            productsRepository.save(productOld);

            return new StatusResponse("OK");
        }
        return new StatusResponse("Non-existing sale");
    }

    @Override
    public boolean deleteSale(int id) {
        if (salesOfProductsRepository.findById(id).isPresent()) {
            salesOfProductsRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
