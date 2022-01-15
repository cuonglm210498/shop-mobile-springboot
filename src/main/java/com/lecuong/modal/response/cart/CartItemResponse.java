package com.lecuong.modal.response.cart;

import com.lecuong.modal.response.product.ProductResponse;
import lombok.Data;

@Data
public class CartItemResponse {

    private long id;
    private long quantity;
    private ProductResponse productResponse;
}
