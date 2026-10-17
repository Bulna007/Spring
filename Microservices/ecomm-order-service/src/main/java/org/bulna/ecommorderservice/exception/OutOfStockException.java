package org.bulna.ecommorderservice.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Product out of stock");
    }
}
