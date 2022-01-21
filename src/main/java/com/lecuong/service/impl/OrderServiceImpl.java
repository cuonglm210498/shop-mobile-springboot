package com.lecuong.service.impl;

import com.lecuong.constant.JasperParam;
import com.lecuong.entity.CartItem;
import com.lecuong.entity.Orders;
import com.lecuong.entity.OrdersItem;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
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
    public OrderResponse getOrderByUser() {

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

    @Override
    public void deleteOrder(Long id) {
        Optional<Orders> orders = orderRepository.findById(id);
        orders.orElseThrow(() -> new BusinessException(StatusTemplate.ORDERS_NOT_FOUND));
        orderRepository.deleteById(id);
    }

    @Override
    public void exportOrderToPdf() {
        String path = "C:\\Users\\Administrator\\Downloads";
        OrderResponse orderResponse = this.getOrderByUser();
        try {
            File file = ResourceUtils.getFile("classpath:order.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put(JasperParam.P_ORDER_ID, orderResponse.getId());
            mapParams.put(JasperParam.P_RECEIVER, orderResponse.getRecipient());
            mapParams.put(JasperParam.P_ADDRESS, orderResponse.getShippingAddress());
            mapParams.put(JasperParam.P_ORDERER, orderResponse.getOrderer());
            mapParams.put(JasperParam.P_STATUS, orderResponse.getStatus());
            mapParams.put(JasperParam.P_PHONE_NUMBER, orderResponse.getPhoneNumber());
            mapParams.put(JasperParam.P_TOTAL_AMOUNT, new BigDecimal(orderResponse.getTotalAmount()));

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderResponse.getOrderItemResponses());
            mapParams.put(JasperParam.P_ORDER_ITEM, dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParams, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\order.pdf");

        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }

    public double totalAmount(List<OrdersItem> list) {
        double total = 0;
        for (OrdersItem ordersItem : list) {
            total += ordersItem.getTotalPrice();
        }
        return total;
    }
}
