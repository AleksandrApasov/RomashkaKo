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
    @Query("select * from Product p where p.price > limit")
    List<Product> findAll(@Param("limit") int limit);
}
