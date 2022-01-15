package com.lecuong.modal.request.contact;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class ContactFilterRequest extends BaseRequest {

    private String name;
    private String email;
    private String phoneNumber;
}
