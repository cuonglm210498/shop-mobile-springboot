package com.lecuong.modal.request.order;

import lombok.Data;

@Data
public class OrderItemSaveRequest extends OrderSaveRequest {

    private Long quantity;
    private Double price;
    private Double totalPrice;
    private Long orderId;
    private Long productId;
}
