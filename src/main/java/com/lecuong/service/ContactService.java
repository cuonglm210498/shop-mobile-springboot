package com.lecuong.service;

import com.lecuong.modal.request.contact.ContactFilterRequest;
import com.lecuong.modal.request.contact.ContactSaveRequest;
import com.lecuong.modal.response.contact.ContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    void save(ContactSaveRequest contactSaveRequest);

    Page<ContactResponse> getAll(Pageable pageable);

    Page<ContactResponse> filter(ContactFilterRequest contactFilterRequest);
}
