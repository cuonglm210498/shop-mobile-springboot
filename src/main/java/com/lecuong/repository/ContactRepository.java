package com.lecuong.repository;

import com.lecuong.entity.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends BaseRepository<Contact, Long> {
}
