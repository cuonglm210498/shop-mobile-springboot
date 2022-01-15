package com.lecuong.service.impl;

import com.lecuong.entity.Contact;
import com.lecuong.mapper.contact.ContactMapper;
import com.lecuong.modal.request.contact.ContactFilterRequest;
import com.lecuong.modal.request.contact.ContactSaveRequest;
import com.lecuong.modal.response.contact.ContactResponse;
import com.lecuong.repository.ContactRepository;
import com.lecuong.repository.specification.ContactSpecification;
import com.lecuong.service.ContactService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Data
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public void save(ContactSaveRequest contactSaveRequest) {
        Contact contact = contactMapper.to(contactSaveRequest);
        contactRepository.save(contact);
    }

    @Override
    public Page<ContactResponse> getAll(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAll(pageable.previousOrFirst());
        return contacts.map(contactMapper::to);
    }

    @Override
    public Page<ContactResponse> filter(ContactFilterRequest contactFilterRequest) {
        PageRequest pageRequest = PageRequest.of(contactFilterRequest.getPageIndex(), contactFilterRequest.getPageSize());
        Page<Contact> contacts = contactRepository.findAll(ContactSpecification.filter(contactFilterRequest), pageRequest.previousOrFirst());
        return contacts.map(contactMapper::to);
    }
}
