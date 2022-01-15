package com.lecuong.modal.request.user;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class UserFilterRequest extends BaseRequest {

    private String userName;
    private String address;
    private String email;
}
