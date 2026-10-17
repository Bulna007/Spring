package org.bulna.consumer.dto;

public record Order(
        String orderId,
        String userId,
        double amount
) {
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
