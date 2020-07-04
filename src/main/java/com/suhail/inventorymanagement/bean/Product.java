package com.suhail.inventorymanagement.bean;

import com.suhail.inventorymanagement.utils.constant.ProductCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "products")
public class Product {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private ProductCategory productCategory;
    private Double costPrice;
    private Double sellingPrice;
    private Double quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.costPrice, costPrice) == 0 &&
                Double.compare(product.sellingPrice, sellingPrice) == 0 &&
                Double.compare(product.quantity, quantity) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                productCategory == product.productCategory;
    }

}
