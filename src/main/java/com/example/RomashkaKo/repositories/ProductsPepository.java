package com.example.RomashkaKo.repositories;

import com.example.RomashkaKo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsPepository extends CrudRepository<Product,Integer> {
    List<Product> findAll();
}
