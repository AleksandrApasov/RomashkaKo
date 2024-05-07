package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.respons.BaseResponse;

import java.util.List;

public interface SuppliesOfProductsService {

    List<SupplyOfProducts> getSupplies();
    SupplyOfProducts getSupply(int id);
    BaseResponse createSupply(SupplyOfProducts supplyOfProducts,int productId);
    boolean updateSupply(SupplyOfProducts supplyOfProducts, int id);
    boolean deleteSupply(int id);
}
