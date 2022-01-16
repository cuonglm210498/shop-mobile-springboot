package com.lecuong.modal.request.order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderSaveRequest {

    private String shippingAddress;
    private String status;
    //private LocalDate receivingDate;
    private String recipient;
    private String phoneNumber;
    //private Double totalAmount;
    //private Long userId;
}
