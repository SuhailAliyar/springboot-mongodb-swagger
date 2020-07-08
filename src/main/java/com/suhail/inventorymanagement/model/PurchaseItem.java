package com.suhail.inventorymanagement.model;

import javax.validation.constraints.NotEmpty;

public class PurchaseItem {
    @NotEmpty
    private String productId;
    @NotEmpty
    private double quantity;
    @NotEmpty
    private double purchasePrice;

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
