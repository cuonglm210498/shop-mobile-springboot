package com.lecuong.mapper.contact;

import com.lecuong.entity.Contact;
import com.lecuong.modal.request.contact.ContactSaveRequest;
import com.lecuong.modal.response.contact.ContactResponse;
import com.lecuong.utils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact to(ContactSaveRequest contactSaveRequest) {
        Contact contact = new Contact();
        BeanUtils.refine(contactSaveRequest, contact, BeanUtils::copyNonNull);
        return contact;
    }

    public ContactResponse to(Contact contact) {
        ContactResponse contactResponse = new ContactResponse();
        BeanUtils.refine(contact, contactResponse, BeanUtils::copyNonNull);
        return contactResponse;
    }
}
