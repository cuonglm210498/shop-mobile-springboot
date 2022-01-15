package com.lecuong.modal.request.contact;

import lombok.Data;

@Data
public class ContactSaveRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String content;
}
