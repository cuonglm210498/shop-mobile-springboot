package com.lecuong.service;

import com.lecuong.modal.request.blog.BlogFilterRequest;
import com.lecuong.modal.response.blog.BlogResponse;
import org.springframework.data.domain.Page;

public interface BlogService {

    Page<BlogResponse> getAll(BlogFilterRequest blogFilterRequest);
}
