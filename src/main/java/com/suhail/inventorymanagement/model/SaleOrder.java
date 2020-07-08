package com.suhail.inventorymanagement.model;

import com.suhail.inventorymanagement.utils.constant.SaleStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "sale-orders")
public class SaleOrder {

    @Id
    private String id;
    @NotNull
    private String customerId;
    @NotEmpty
    private List<SaleItem> saleItems;
    @NotNull
    private double billAmount;
    private SaleStatus saleStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public SaleStatus getSalesStatus() {
        return saleStatus;
    }

    public void setSalesStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }
}
