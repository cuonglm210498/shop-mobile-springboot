package com.lecuong.modal.request.product;

import lombok.Data;

@Data
public class ProductSaveRequest {

    private String name;
    private String color;
    private String batteryCapacity;
    private String operationSystem;
    private String screenSize;
    private String ram;
    private String warranty;
    private String cpu;
    private String memory;
    private String thumbnail;
    private String realCamera;
    private String frontCamera;
    private String status;
    private Integer quantity;
    private Double price;
    private Long providerIds;
    private Long categoryIds;
}
