package com.example.RomashkaKo.services;

import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.respons.ErrorsListResponse;
import com.example.RomashkaKo.respons.ParentResponse;

import java.util.List;

public interface SuppliesOfProductsService {

    List<SupplyOfProducts> getSupplies();
    SupplyOfProducts getSupply(int id);
    ParentResponse createSupply(SupplyOfProducts supplyOfProducts, int productId);
    boolean updateSupply(SupplyOfProducts supplyOfProducts, int id, Integer productId);
    boolean deleteSupply(int id);
}
