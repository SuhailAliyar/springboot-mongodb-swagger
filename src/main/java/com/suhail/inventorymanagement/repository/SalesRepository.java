package com.suhail.inventorymanagement.repository;

import com.suhail.inventorymanagement.model.SaleOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends MongoRepository<SaleOrder, String> {

}
