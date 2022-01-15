package com.lecuong.controller.user;

import com.lecuong.modal.request.user.UserSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/api/v1/users/register")
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@RequestBody UserSaveRequest userSaveRequest) {
        userService.save(userSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }
}
