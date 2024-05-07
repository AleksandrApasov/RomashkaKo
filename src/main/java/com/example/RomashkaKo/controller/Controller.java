package com.example.RomashkaKo.controller;


import com.example.RomashkaKo.model.SupplyOfProducts;
import com.example.RomashkaKo.respons.BaseResponse;
import com.example.RomashkaKo.model.Product;
import com.example.RomashkaKo.services.ProductService;
import com.example.RomashkaKo.services.SuppliesOfProductsService;
import com.example.RomashkaKo.services.SuppliesOfProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {


    private final ProductService productService;
    private final SuppliesOfProductsService suppliesOfProductsService;


    @Autowired
    public Controller(ProductService productService,SuppliesOfProductsService suppliesOfProductsService) {
        this.productService = productService;
        this.suppliesOfProductsService = suppliesOfProductsService;

    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) Integer limit,
                                                @RequestParam(required = false) Boolean isLowLimit,
                                                @RequestParam(required = false) Boolean isInStock,
                                                @RequestParam(required = false) Boolean sortByName,
                                                @RequestParam(required = false) Boolean sortByPrice,
                                                @RequestParam(required = false) Integer limitElements) {

        final List<Product> products = productService.getProducts(name, limit, isLowLimit,
                isInStock, sortByName, sortByPrice, limitElements);

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/products")
    public BaseResponse create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> get(@PathVariable(name = "id") int id) {
        final Product product = productService.getProduct(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Product> delete(@PathVariable(name = "id") int id) {
        return productService.deleteProduct(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        final boolean updated = productService.updateProduct(product, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/supplies")
    public ResponseEntity<List<SupplyOfProducts>> getAll() {

        final List<SupplyOfProducts> supplies = suppliesOfProductsService.getSupplies();

        return supplies != null && !supplies.isEmpty()
                ? new ResponseEntity<>(supplies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/supplies")
    public BaseResponse create(@RequestBody SupplyOfProducts supplyOfProducts,
                               @RequestParam(required = true) Integer productId) {
        return suppliesOfProductsService.createSupply(supplyOfProducts,productId);
    }

    @GetMapping(value = "/supplies/{id}")
    public ResponseEntity<SupplyOfProducts> getSupply(@PathVariable(name = "id") int id) {
        final SupplyOfProducts supplyOfProducts = suppliesOfProductsService.getSupply(id);

        return supplyOfProducts != null
                ? new ResponseEntity<>(supplyOfProducts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping(value = "/supplies/{id}")
//    public ResponseEntity<SupplyOfProducts> delete(@PathVariable(name = "id") int id) {
//        return productService.deleteProduct(id)
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping(value = "/supplies/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody SupplyOfProducts supplyOfProducts) {
//        final boolean updated = productService.updateProduct(SupplyOfProducts, id);
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
}
