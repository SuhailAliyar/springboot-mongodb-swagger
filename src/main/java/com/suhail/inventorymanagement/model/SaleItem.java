package com.suhail.inventorymanagement.model;

import javax.validation.constraints.NotEmpty;

public class SaleItem {

    @NotEmpty
    private String productId;
    @NotEmpty
    private double quantity;

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
