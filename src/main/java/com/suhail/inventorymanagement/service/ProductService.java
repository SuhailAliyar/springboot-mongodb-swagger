package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.bean.Product;
import com.suhail.inventorymanagement.repository.ProductRepository;
import com.suhail.inventorymanagement.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUtils productUtils;

    public Product addProducts(Product product) {
        product.setId(productUtils.generateProductId());
        return productRepository.save(product);
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
        return ResponseEntity.status(403).body("Product does not exist");
    }

    public ResponseEntity updateProduct(String productId, Product product) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            return ResponseEntity.status(403).body("Product does not exist");
        }
        Product updateProduct = existingProduct.get();
        if (null != product.getProductCategory()) {
            updateProduct.setProductCategory(product.getProductCategory());
        }
        if (null != product.getCostPrice()) {
            updateProduct.setCostPrice(product.getCostPrice());
        }
        if (null != product.getSellingPrice()) {
            updateProduct.setSellingPrice(product.getSellingPrice());
        }
        if (null != product.getQuantity()) {
            updateProduct.setQuantity(product.getQuantity());
        }

        final Product updatedProduct = productRepository.save(updateProduct);
        return ResponseEntity.ok().body(updatedProduct);
    }

    public ResponseEntity deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body("Product removed successfully");
        }
        return ResponseEntity.status(403).body("Product does not exist");
    }
}
