package com.lecuong.modal.request.product;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class ProductFilterRequest extends BaseRequest {

    private String name;
    private String color;
    private String batteryCapacity;
    private String operationSystem;
    private String screenSize;
    private String ram;
    private String cpu;
    private String memory;
    private String realCamera;
    private String frontCamera;
    private double price;
    private long providerIds;
    private long categoryIds;
}
