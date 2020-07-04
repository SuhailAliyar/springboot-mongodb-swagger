package com.suhail.inventorymanagement.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String errorMessage) {
        super(errorMessage);
    }
}
