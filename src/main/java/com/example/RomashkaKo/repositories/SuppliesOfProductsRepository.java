package com.example.RomashkaKo.repositories;

import com.example.RomashkaKo.model.SupplyOfProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliesOfProductsRepository extends CrudRepository<SupplyOfProducts, Integer> {
    List<SupplyOfProducts> findAll();
}
