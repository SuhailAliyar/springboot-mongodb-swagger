package com.suhail.inventorymanagement.controller;

import com.suhail.inventorymanagement.bean.SaleOrder;
import com.suhail.inventorymanagement.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping
    public SaleOrder addSaleOrder(@Valid @RequestBody SaleOrder saleOrder) {
        return salesService.addSaleOrder(saleOrder);
    }

    @GetMapping
    public ResponseEntity getAllSaleOrders() {
        return salesService.getAllSaleOrders();

    }

    @GetMapping("/{id}")
    public ResponseEntity getSaleOrder(@PathVariable(value = "String sale-order-id") String id) {
        return salesService.getSaleOrder( id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSaleOrder(@PathVariable(value = "sale-order-id") String id, @RequestBody SaleOrder saleOrder) {
        return salesService.updateSaleOrder(id.trim(), saleOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancelSaleOrder(@PathVariable(value = "id") String id) {
        return salesService.cancelSaleOrder(id.trim());
    }
}
