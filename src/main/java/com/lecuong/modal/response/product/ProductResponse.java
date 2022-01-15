package com.lecuong.modal.response.product;

import lombok.Data;

@Data
public class ProductResponse {

    private long id;
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
    private int quantity;
    private double price;
    private String providerName;
    private String categoryName;
}
