package com.suhail.inventorymanagement.controller;

import com.suhail.inventorymanagement.bean.Product;
import com.suhail.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productservice;

    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return productservice.addProducts(product);
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return productservice.getAllProducts();

    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable(value = "id") String id) {
        return productservice.getProduct(id.trim());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") String id, @RequestBody Product productUpdate) {
        return productservice.updateProduct(id.trim(), productUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") String id) {
        return productservice.deleteProduct(id.trim());
    }

}
