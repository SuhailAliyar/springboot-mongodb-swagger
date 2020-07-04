package com.suhail.inventorymanagement.repository;

import com.suhail.inventorymanagement.bean.SaleOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesRepository extends MongoRepository<SaleOrder, String> {

}
