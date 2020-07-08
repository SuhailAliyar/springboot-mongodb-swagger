package com.suhail.inventorymanagement.controller;

import com.suhail.inventorymanagement.model.User;
import com.suhail.inventorymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@Valid @RequestBody User customer) {
        return userService.addUser(customer);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable(value = "id") String id) {
        return userService.getUser(id.trim());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") String id, @RequestBody User userUpdate) {
        return userService.updateUser(id.trim(), userUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") String id) {
        return userService.deleteUser(id.trim());
    }
}
