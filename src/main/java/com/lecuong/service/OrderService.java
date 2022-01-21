package com.lecuong.service;

import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.order.OrderResponse;

public interface OrderService {

    void createOrder(OrderItemSaveRequest ordersItemSaveRequest);

    OrderResponse getOrderByUser();

    void deleteOrder(Long id);

    void exportOrderToPdf();
}
