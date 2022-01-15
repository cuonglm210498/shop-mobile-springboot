package com.lecuong.controller.user;

import com.lecuong.modal.request.user.UserFilterRequest;
import com.lecuong.modal.request.user.UserSaveRequest;
import com.lecuong.modal.request.user.UserUpdateRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.user.UserResponse;
import com.lecuong.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User APIs")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Find user by id", response = List.class)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> findById(@PathVariable Long id) {
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(userResponse));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Page<UserResponse>>> getAll(@RequestParam int index,
                                                                   @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<UserResponse> userResponses = userService.getAll(pageRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(userResponses));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<UserResponse>>> filter(@ModelAttribute UserFilterRequest userFilterRequest) {
        Page<UserResponse> userResponses = userService.filter(userFilterRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(userResponses));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> update(@RequestBody UserUpdateRequest userUpdateRequest,
                                                     @PathVariable Long id) {
        userService.update(userUpdateRequest, id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }
}
