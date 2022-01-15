package com.lecuong.service.impl;

import com.lecuong.entity.Role;
import com.lecuong.entity.User;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.user.UserMapper;
import com.lecuong.modal.request.user.UserAuthRequest;
import com.lecuong.modal.request.user.UserFilterRequest;
import com.lecuong.modal.request.user.UserSaveRequest;
import com.lecuong.modal.request.user.UserUpdateRequest;
import com.lecuong.modal.response.user.UserResponse;
import com.lecuong.repository.UserRepository;
import com.lecuong.repository.specification.UserSpecification;
import com.lecuong.service.UserService;
import com.lecuong.utils.AlgorithmMd5;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(UserSaveRequest userSaveRequest) {
        User user = userMapper.to(userSaveRequest);
        userRepository.save(user);
    }

    @Override
    public UserResponse auth(UserAuthRequest userAuthRequest) {
        String password = AlgorithmMd5.getMd5(userAuthRequest.getPassword());
        userAuthRequest.setPassword(password);

        Optional<User> user = userRepository.findByUserNameAndPassword(userAuthRequest.getUserName(), userAuthRequest.getPassword());
        user.orElseThrow(() -> new BusinessException(StatusTemplate.USER_NOT_FOUND));

        UserResponse userResponse = userMapper.to(user.get());

        List<String> roles = user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList());
        userResponse.setRoleName(roles);

        return userResponse;
    }

    @Override
    public void update(UserUpdateRequest userUpdateRequest, Long id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setPassword(AlgorithmMd5.getMd5(userUpdateRequest.getPassword()));
                    user.setEmail(userUpdateRequest.getEmail());
                    user.setPhone(userUpdateRequest.getPhone());
                    user.setAddress(userUpdateRequest.getAddress());
                    user.setFullName(userUpdateRequest.getFullName());
                    user.setDateOfBirth(userUpdateRequest.getDateOfBirth());
                    user.setAvatar(userUpdateRequest.getAvatar());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new BusinessException(StatusTemplate.USER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new BusinessException(StatusTemplate.USER_NOT_FOUND));

        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new BusinessException(StatusTemplate.USER_NOT_FOUND));

        UserResponse userResponse = userMapper.to(user.get());

        return userResponse;
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable.previousOrFirst());
        return users.map(userMapper::to);
    }

    @Override
    public Page<UserResponse> filter(UserFilterRequest userFilterRequest) {
        PageRequest pageRequest = PageRequest.of(userFilterRequest.getPageIndex(), userFilterRequest.getPageSize());
        Page<User> users = userRepository.findAll(UserSpecification.filter(userFilterRequest), pageRequest.previousOrFirst());
        return users.map(userMapper::to);
    }
}
