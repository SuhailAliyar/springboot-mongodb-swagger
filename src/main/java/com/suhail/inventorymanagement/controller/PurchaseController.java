package com.suhail.inventorymanagement.controller;

import com.suhail.inventorymanagement.model.PurchaseOrder;

import com.suhail.inventorymanagement.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public PurchaseOrder addSPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder) {
        return purchaseService.addPurchaseOrder(purchaseOrder);
    }

    @GetMapping
    public ResponseEntity getAllPurchaseOrders() {
        return purchaseService.getAllPurchaseOrders();

    }

    @GetMapping("/{id}")
    public ResponseEntity getPurchaseOrder(@PathVariable(value = "String purchase-order-id") String id) {
        return purchaseService.getPurchaseOrder(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePurchaseOrder(@PathVariable(value = "sale-order-id") String id, @RequestBody PurchaseOrder purchaseOrder) {
        return purchaseService.updatePurchaseOrder(id.trim(), purchaseOrder);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancelPurchaseOrder(@PathVariable(value = "id") String id) {
        return purchaseService.cancelPurchaseOrder(id.trim());
    }

    @PatchMapping("/{id}")
    public ResponseEntity completePurchaseOrder(@PathVariable(value = "id") String id) {
        return purchaseService.completePurchaseOrder(id.trim());
    }

}

