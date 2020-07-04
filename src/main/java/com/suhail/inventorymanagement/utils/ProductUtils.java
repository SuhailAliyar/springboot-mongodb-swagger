package com.suhail.inventorymanagement.utils;

import com.suhail.inventorymanagement.exception.OutOfStockException;
import com.suhail.inventorymanagement.bean.Product;
import com.suhail.inventorymanagement.bean.SaleItem;
import com.suhail.inventorymanagement.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.util.List;
import java.util.Optional;

@Service
public class ProductUtils {

    @Autowired
    ProductRepository productRepository;

    public String generateProductId() {
        return new ObjectId().toHexString();
    }

    public double findTotalBillAmount(List<SaleItem> saleItems) {
        return saleItems.stream()
                .mapToDouble(
                        item -> item.getQuantity() * productRepository.findById(item.getProductId()).get().getSellingPrice())
                .sum();
    }

    public void checkStockAvailability(List<SaleItem> saleItemList) {
        saleItemList.forEach(saleItem -> {
            Optional<Product> product = productRepository.findById(saleItem.getProductId());
            if (!product.isPresent() || product.get().getQuantity() < saleItem.getQuantity()) {
                throw new OutOfStockException(saleItem.getProductId() + " is out of stock");
            }
        });
    }
}
