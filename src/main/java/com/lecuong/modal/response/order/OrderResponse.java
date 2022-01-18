package com.lecuong.modal.response.order;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponse {

    private long id;
    private String orderer;
    private String shippingAddress;
    private String status;
    private LocalDate receivingDate;
    private String recipient;
    private String phoneNumber;
    private Double totalAmount;
    private List<OrderItemResponse> orderItemResponses;
}
