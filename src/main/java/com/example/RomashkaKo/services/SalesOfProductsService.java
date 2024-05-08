package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.SaleOfProducts;
import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.respons.ParentResponse;

import java.util.List;

public interface SalesOfProductsService {

    List<SaleOfProducts> getSales();
    SaleOfProducts getSale(int id);
    ParentResponse createSale(SaleOfProducts supplyOfProducts, int productId);
    ParentResponse updateSale(SaleOfProducts saleOfProducts, int id, Integer productId);
    boolean deleteSale(int id);
}
