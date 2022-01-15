package com.lecuong.mapper.user;

import com.lecuong.entity.Role;
import com.lecuong.entity.User;
import com.lecuong.modal.request.user.UserSaveRequest;
import com.lecuong.modal.response.user.UserResponse;
import com.lecuong.repository.RoleRepository;
import com.lecuong.utils.AlgorithmMd5;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class UserMapper {

    private final RoleRepository roleRepository;

    public User to(UserSaveRequest userSaveRequest) {
        User user = new User();
        BeanUtils.refine(userSaveRequest, user, BeanUtils::copyNonNull);
        Set<Role> roles = new HashSet<>(roleRepository.findByIdIn(userSaveRequest.getIds()));
        user.setRoles(roles);
        user.setPassword(AlgorithmMd5.getMd5(userSaveRequest.getPassword()));
        return user;
    }

    public UserResponse to(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);
        return userResponse;
    }
}
