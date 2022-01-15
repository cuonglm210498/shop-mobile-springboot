package com.lecuong.service;

import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.modal.request.cart.CartUpdateRequest;
import com.lecuong.modal.response.cart.CartItemResponse;


import java.util.List;

public interface CartItemService {

    void addCart(CartSaveRequest cartSaveRequest);

    void delete(Long id);

    void update(CartUpdateRequest cartUpdateRequest, Long id);

    long getCount();

    List<CartItemResponse> getAllItem();
}
