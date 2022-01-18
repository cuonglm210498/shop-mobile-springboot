package com.lecuong.service.impl;

import com.lecuong.entity.CartItem;
import com.lecuong.entity.Orders;
import com.lecuong.entity.OrdersItem;
import com.lecuong.mapper.order.OrderItemMapper;
import com.lecuong.mapper.order.OrderMapper;
import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.order.OrderItemResponse;
import com.lecuong.modal.response.order.OrderResponse;
import com.lecuong.repository.*;
import com.lecuong.security.UserDetails;
import com.lecuong.service.OrderService;
import com.lecuong.utils.UserUtils;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserUtils userUtils;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    public void createOrder(OrderItemSaveRequest ordersItemSaveRequest) {

        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();
        LocalDate localDate = LocalDate.now();

        List<CartItem> cartItems = cartItemRepository.getAllCartItemByUserId(userDetails.getUser().getId());
        List<OrdersItem> ordersItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrdersItem ordersItem = new OrdersItem();
            ordersItem.setQuantity(cartItem.getQuantity());
            ordersItem.setProduct(productRepository.findById(cartItem.getProduct().getId()).get());
            ordersItem.setPrice(cartItem.getProduct().getPrice());
            ordersItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            ordersItems.add(ordersItem);
            cartItemRepository.delete(cartItem);
        }

        Orders orders = new Orders();
        orders.setShippingAddress(ordersItemSaveRequest.getShippingAddress());
        orders.setStatus(ordersItemSaveRequest.getStatus());
        orders.setReceivingDate(localDate.plusDays(3));
        orders.setRecipient(ordersItemSaveRequest.getRecipient());
        orders.setPhoneNumber(ordersItemSaveRequest.getPhoneNumber());
        orders.setTotalAmount(totalAmount(ordersItems));
        orders.setUser(userRepository.findById(userDetails.getUser().getId()).get());

        orderRepository.save(orders);

        for (OrdersItem ordersItem : ordersItems) {
            ordersItemSaveRequest.setOrderId(orderRepository.findOrdersIdDescByUserId(userDetails.getUser().getId()));
            ordersItem.setOrders(orderRepository.findById(ordersItemSaveRequest.getOrderId()).get());
            orderItemRepository.save(ordersItem);
        }
    }

    @Override
    public OrderResponse getOrder() {

        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();
        Long userId = userDetails.getUser().getId();
        Long orderId = orderRepository.findOrdersIdDescByUserId(userId);

        List<OrdersItem> ordersItems = orderItemRepository.findAllByUserIdAndOrderId(userId, orderId);
        List<OrderItemResponse> orderItemResponses = ordersItems.stream().map(orderItemMapper::to).collect(Collectors.toList());

        Orders orders = orderRepository.findOrdersDescByUserId(userId);
        OrderResponse orderResponse = orderMapper.to(orders);
        orderResponse.setOrderItemResponses(orderItemResponses);

        return orderResponse;
    }

    public double totalAmount(List<OrdersItem> list) {
        double total = 0;
        for (OrdersItem ordersItem : list) {
            total += ordersItem.getTotalPrice();
        }
        return total;
    }
}
