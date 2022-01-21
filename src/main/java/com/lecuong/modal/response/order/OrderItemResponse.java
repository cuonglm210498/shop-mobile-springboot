package com.lecuong.modal.response.order;
import lombok.Data;
import org.apache.xmlbeans.impl.values.JavaDoubleHolderEx;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private long id;
    private long orderId;
    private Long quantity;
    private Double price;
    private Double totalPrice;
    private String productName;
}
