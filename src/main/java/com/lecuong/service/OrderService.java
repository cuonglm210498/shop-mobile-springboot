package com.lecuong.service;

import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.order.OrderResponse;

import java.util.List;

public interface OrderService {

    void createOrder(OrderItemSaveRequest ordersItemSaveRequest);

    OrderResponse getOrder();
}
