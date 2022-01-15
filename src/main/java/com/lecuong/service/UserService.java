package com.lecuong.service;

import com.lecuong.modal.request.user.UserAuthRequest;
import com.lecuong.modal.request.user.UserFilterRequest;
import com.lecuong.modal.request.user.UserSaveRequest;
import com.lecuong.modal.request.user.UserUpdateRequest;
import com.lecuong.modal.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void save(UserSaveRequest userSaveRequest);

    UserResponse auth(UserAuthRequest userAuthRequest);

    void update(UserUpdateRequest userUpdateRequest, Long id);

    void delete(Long id);

    UserResponse findById(Long id);

    Page<UserResponse> getAll(Pageable pageable);

    Page<UserResponse> filter(UserFilterRequest userFilterRequest);
}
