package com.lecuong.utils;

import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.security.UserAuthentication;
import com.lecuong.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public UserDetails getUserDetailsFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException(StatusTemplate.USER_MUST_LOGIN);
        }
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) userAuthentication.getDetails();

        return userDetails;
    }
}
