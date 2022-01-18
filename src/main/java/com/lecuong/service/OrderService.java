package com.lecuong.service;

import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.order.OrderResponse;

public interface OrderService {

    void createOrder(OrderItemSaveRequest ordersItemSaveRequest);

    OrderResponse getOrder();

    void deleteOrder(Long id);
}
