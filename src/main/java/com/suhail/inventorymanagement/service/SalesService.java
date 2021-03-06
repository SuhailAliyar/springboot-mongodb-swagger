package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.model.SaleItem;
import com.suhail.inventorymanagement.model.SaleOrder;
import com.suhail.inventorymanagement.repository.SalesRepository;
import com.suhail.inventorymanagement.utils.constant.SaleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private StockService stockService;

    public SaleOrder addSaleOrder(SaleOrder saleOrder) {
        saleOrder.setSalesStatus(SaleStatus.INPROGRESS);
        List<SaleItem> saleItemList = saleOrder.getSaleItems();
        stockService.checkStockAvailability(saleItemList);
        saleOrder.setBillAmount(stockService.findTotalBillAmount(saleItemList));
        return salesRepository.save(saleOrder);
    }

    public ResponseEntity getAllSaleOrders() {
        return ResponseEntity.ok(salesRepository.findAll());
    }

    public ResponseEntity getSaleOrder(String id) {
        Optional<SaleOrder> saleOrder = salesRepository.findById(id);
        if (saleOrder.isPresent()) {
            return ResponseEntity.ok().body(saleOrder);
        }
        return ResponseEntity.status(403).body("Invalid sale order id");
    }

    public ResponseEntity cancelSaleOrder(String id) {
        Optional<SaleOrder> saleOrder = salesRepository.findById(id);
        if (saleOrder.isPresent()) {
            SaleOrder updateSaleOrder = saleOrder.get();
            updateSaleOrder.setSalesStatus(SaleStatus.CANCELLED);
            return ResponseEntity.ok().body(saleOrder);
        }
        return ResponseEntity.status(403).body("Invalid sale order id");
    }

    public ResponseEntity updateSaleOrder(String id, SaleOrder saleOrder) {
        Optional<SaleOrder> existingSaleOrder = salesRepository.findById(id);
        if (!existingSaleOrder.isPresent()) {
            return ResponseEntity.status(403).body("Invalid sale order id");
        }
        SaleOrder updateSaleOrder = existingSaleOrder.get();
        if (null != saleOrder.getCustomerId()) {
            updateSaleOrder.setCustomerId(saleOrder.getCustomerId());
        }
        final SaleOrder updatedSaleOrder = salesRepository.save(updateSaleOrder);
        return ResponseEntity.ok().body(updatedSaleOrder);
    }

    public ResponseEntity completeSaleOrder(String id) {
        Optional<SaleOrder> existingSalesOrder = salesRepository.findById(id);
        if (!existingSalesOrder.isPresent()) {
            return ResponseEntity.status(403).body("Invalid purchase order id");
        }

        SaleOrder saleOrder = existingSalesOrder.get();
        saleOrder.setSalesStatus(SaleStatus.COMPLETED);
        salesRepository.save(saleOrder);

        stockService.updateSaleToStock(saleOrder.getSaleItems());
        salesRepository.save(saleOrder);
        return ResponseEntity.ok("Salee order successfully placed and updated stock with "
                + saleOrder.getSaleItems().size() + " items");
    }
}
