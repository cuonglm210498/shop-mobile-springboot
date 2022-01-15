package com.lecuong.modal.request.cart;

import lombok.Data;

@Data
public class CartSaveRequest {

    private Long quantity;
    private Long cartId;
    private Long productId;
    private Long userId;
}
