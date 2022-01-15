package com.lecuong.modal.request.category;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class CategoryFilterRequest extends BaseRequest {

    private String name;
    private Long blogIds;
    private Long productIds;
}
