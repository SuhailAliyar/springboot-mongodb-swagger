package com.suhail.inventorymanagement.repository;

import com.suhail.inventorymanagement.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
