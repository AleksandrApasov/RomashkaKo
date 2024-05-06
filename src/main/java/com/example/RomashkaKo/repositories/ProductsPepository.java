package com.example.RomashkaKo.repositories;

import com.example.RomashkaKo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsPepository extends CrudRepository<Product,Integer> {
    List<Product> findAll();
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceLessThan(int limit);
    List<Product> findByPriceGreaterThan(int limit);
    List<Product> findByPrice(int limit);
    List<Product> findByInStock(boolean isInStock);

}
