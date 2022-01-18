package com.lecuong.mapper.order;

import com.lecuong.entity.OrdersItem;
import com.lecuong.modal.response.order.OrderItemResponse;
import com.lecuong.repository.ProductRepository;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderItemMapper {

    private final ProductRepository productRepository;

    public OrderItemResponse to(OrdersItem ordersItem) {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        BeanUtils.refine(ordersItem, orderItemResponse, BeanUtils::copyNonNull);
        orderItemResponse.setOrderId(ordersItem.getOrders().getId());
        orderItemResponse.setProductName(productRepository.findById(ordersItem.getProduct().getId()).get().getName());
        return orderItemResponse;
    }
}
