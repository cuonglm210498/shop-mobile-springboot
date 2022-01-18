package com.lecuong.modal.response.order;
import lombok.Data;

@Data
public class OrderItemResponse {

    private long id;
    private long orderId;
    private Long quantity;
    private Double price;
    private Double totalPrice;
    private String productName;
}
