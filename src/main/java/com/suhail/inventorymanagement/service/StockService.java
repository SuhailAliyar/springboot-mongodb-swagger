package com.suhail.inventorymanagement.service;

import com.suhail.inventorymanagement.model.PurchaseItem;
import com.suhail.inventorymanagement.model.SaleItem;
import com.suhail.inventorymanagement.model.Stock;
import com.suhail.inventorymanagement.exception.OutOfStockException;
import com.suhail.inventorymanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public double findTotalBillAmount(List<SaleItem> saleItems) {
        return saleItems.stream()
                .mapToDouble(
                        item -> item.getQuantity() * stockRepository.findById(item.getProductId()).get().getSellingPrice())
                .sum();
    }

    public void checkStockAvailability(List<SaleItem> saleItemList) {
        saleItemList.forEach(saleItem -> {
            Optional<Stock> stock = stockRepository.findById(saleItem.getProductId());
            if (!stock.isPresent() || stock.get().getQuantity() < saleItem.getQuantity()) {
                throw new OutOfStockException(saleItem.getProductId() + " is out of stock");
            }
        });
    }

    public void updatePurchaseToStock(List<PurchaseItem> purchasedItems) {
        Stock stock = new Stock();
        purchasedItems.forEach(item -> {
            Optional<Stock> existingStock = stockRepository.findById(item.getProductId());
            double purchaseRate = item.getPurchasePrice();
            stock.setProductId(item.getProductId());
            stock.setPurchasePrice(purchaseRate);
            stock.setSellingPrice(purchaseRate * (1 + Stock.PROFIT_MARGIN));
            stock.setQuantity(existingStock.get().getQuantity() + item.getQuantity());
            stockRepository.save(stock);
        });
    }

    public void updateSaleToStock(List<SaleItem> saleItems) {
        Stock stock = new Stock();
        saleItems.forEach(item -> {
            Optional<Stock> existingStock = stockRepository.findById(item.getProductId());
            stock.setProductId(item.getProductId());
            stock.setQuantity(existingStock.get().getQuantity() - item.getQuantity());
            stockRepository.save(stock);
        });
    }
}
