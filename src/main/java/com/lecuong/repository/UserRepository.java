package com.lecuong.repository;

import com.lecuong.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUserNameAndPassword(String userName, String password);
}
