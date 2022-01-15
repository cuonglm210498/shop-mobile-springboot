package com.lecuong.modal.request.provider;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class ProviderSaveRequest extends BaseRequest {

    private String name;
}
