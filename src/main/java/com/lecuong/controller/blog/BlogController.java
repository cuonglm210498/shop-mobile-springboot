package com.lecuong.controller.blog;

import com.lecuong.modal.request.blog.BlogFilterRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.blog.BlogResponse;
import com.lecuong.service.BlogService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<BlogResponse>>> filter(@ModelAttribute BlogFilterRequest filter) {
        Page<BlogResponse> blogResponses = blogService.getAll(filter);
        return ResponseEntity.ok(BaseResponse.ofSuccess(blogResponses));
    }
}
