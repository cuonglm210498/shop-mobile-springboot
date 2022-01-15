package com.lecuong.service;

import com.lecuong.modal.request.cart.CartSaveRequest;

public interface CartService {

    void createCart(CartSaveRequest cartSaveRequest);

    void delete(Long userId);
}
