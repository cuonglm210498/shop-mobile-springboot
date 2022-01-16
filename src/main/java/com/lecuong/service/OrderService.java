package com.lecuong.service;

import com.lecuong.modal.request.order.OrderItemSaveRequest;

public interface OrderService {

    void createOrder(OrderItemSaveRequest ordersItemSaveRequest);
}
