package com.example.RomashkaKo.repositories;

import com.example.RomashkaKo.model.SaleOfProducts;
import com.example.RomashkaKo.model.SupplyOfProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOfProductsRepository extends CrudRepository<SaleOfProducts,Integer> {
    List<SaleOfProducts> findAll();

}
