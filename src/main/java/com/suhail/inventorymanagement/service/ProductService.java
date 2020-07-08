package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.model.Product;
import com.suhail.inventorymanagement.repository.ProductRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockService stockService;

    public ResponseEntity addProducts(Product product) {
        product.setId(new ObjectId().toHexString());
        return ResponseEntity.status(201).body(productRepository.save(product));
    }

    public ResponseEntity getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok().body(products);
    }

    public ResponseEntity getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.status(404).body("Product does not exist");
    }

    public ResponseEntity updateProduct(String productId, Product product) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            return ResponseEntity.status(404).body("Product does not exist");
        }
        Product updateProduct = existingProduct.get();
        if (null != product.getProductCategory()) {
            updateProduct.setProductCategory(product.getProductCategory());
        }
        final Product updatedProduct = productRepository.save(updateProduct);
        return ResponseEntity.status(204).body(updatedProduct);
    }

    public ResponseEntity deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body("Product removed successfully");
        }
        return ResponseEntity.status(404).body("Product does not exist");
    }
}
