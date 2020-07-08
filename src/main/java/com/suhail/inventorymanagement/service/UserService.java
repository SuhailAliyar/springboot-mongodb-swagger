package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.model.User;
import com.suhail.inventorymanagement.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setId(new ObjectId().toHexString());
        return userRepository.save(user);
    }

    public ResponseEntity getAllUsers() {
        List<User> products = userRepository.findAll();
        return ResponseEntity.ok().body(products);
    }

    public ResponseEntity getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.status(403).body("User does not exist");
    }

    public ResponseEntity updateUser(String id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(403).body("User does not exist");
        }
        User updateUser = existingUser.get();
        if (null != user.getEmail()) {
            updateUser.setEmail(user.getEmail());
        }
        if (null != user.getName()) {
            updateUser.setName(user.getName());
        }
        if (null != user.getMobileNumber()) {
            updateUser.setMobileNumber(user.getMobileNumber());
        }
        if (updateUser.equals(existingUser.get())) {
            return ResponseEntity.status(304).body("No modification done");
        }
        final User updatedUser = userRepository.save(updateUser);
        return ResponseEntity.ok().body(updatedUser);
    }


    public ResponseEntity deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body("User removed successfully");
        }
        return ResponseEntity.status(403).body("User does not exist");
    }
}
