package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.model.PurchaseOrder;
import com.suhail.inventorymanagement.repository.PurchaseRepository;
import com.suhail.inventorymanagement.utils.constant.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    StockService stockService;

    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setPurchaseStatus(PurchaseStatus.INPROGRESS);
        return purchaseRepository.save(purchaseOrder);
    }

    public ResponseEntity getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseRepository.findAll());
    }

    public ResponseEntity getPurchaseOrder(String id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseRepository.findById(id);
        if (purchaseOrder.isPresent()) {
            return ResponseEntity.ok().body(purchaseOrder);
        }
        return ResponseEntity.status(403).body("Invalid purchase order id");
    }

    public ResponseEntity updatePurchaseOrder(String id, PurchaseOrder purchaseOrder) {
        Optional<PurchaseOrder> existingPurchaseOrder = purchaseRepository.findById(id);
        if (!existingPurchaseOrder.isPresent()) {
            return ResponseEntity.status(403).body("Invalid purchase order id");
        }
        PurchaseOrder updatePurchaseOrder = existingPurchaseOrder.get();
        if (null != purchaseOrder.getCustomerId()) {
            updatePurchaseOrder.setCustomerId(purchaseOrder.getCustomerId());
        }
        final PurchaseOrder updatedPurchaseOrder = purchaseRepository.save(updatePurchaseOrder);
        return ResponseEntity.ok().body(updatedPurchaseOrder);
    }

    public ResponseEntity cancelPurchaseOrder(String id) {
        Optional<PurchaseOrder> saleOrder = purchaseRepository.findById(id);
        if (saleOrder.isPresent()) {
            PurchaseOrder updatePurchaseOrder = saleOrder.get();
            updatePurchaseOrder.setPurchaseStatus(PurchaseStatus.CANCELLED);
            return ResponseEntity.ok().body(saleOrder);
        }
        return ResponseEntity.status(403).body("Invalid purchase order id");
    }

    public ResponseEntity completePurchaseOrder(String id) {
        Optional<PurchaseOrder> existingPurchaseOrder = purchaseRepository.findById(id);
        if (!existingPurchaseOrder.isPresent()) {
            return ResponseEntity.status(403).body("Invalid purchase order id");
        }
        //TODO
        //Logic for Bill payment
        //If payment failed, then update purchaseOrder with PurchaseStatus.Failed

        //If payment is success
        PurchaseOrder purchaseOrder = existingPurchaseOrder.get();
        purchaseOrder.setPurchaseStatus(PurchaseStatus.COMPLETED);
        purchaseRepository.save(purchaseOrder);
        stockService.updatePurchaseToStock(purchaseOrder.getPurchaseItems());
        purchaseRepository.save(purchaseOrder);
        return ResponseEntity.ok("Purchase order successfully placed and updated stock with "
                + purchaseOrder.getPurchaseItems().size() + "items");
    }
}
