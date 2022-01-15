package com.lecuong.repository.specification;

import com.lecuong.entity.Contact;
import com.lecuong.modal.request.contact.ContactFilterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {

    public static Specification<Contact> filter(ContactFilterRequest filterRequest) {
        return Specification.where(withName(filterRequest.getName()))
                .and(withEmail(filterRequest.getEmail()))
                .and(withPhoneNumber(filterRequest.getPhoneNumber()))
                .or(withNameLike(filterRequest.getName()));
    }

    public static Specification<Contact> withName(String name) {
        if (StringUtils.isBlank(name))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Contact> withEmail(String email) {
        if (StringUtils.isBlank(email))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Contact> withPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
    }

    public static Specification<Contact> withNameLike(String nameLike) {
        if (StringUtils.isBlank(nameLike))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + nameLike + "%");
    }
}
