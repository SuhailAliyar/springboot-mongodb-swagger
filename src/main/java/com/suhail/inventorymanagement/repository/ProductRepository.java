package com.suhail.inventorymanagement.repository;

import com.suhail.inventorymanagement.bean.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
}
