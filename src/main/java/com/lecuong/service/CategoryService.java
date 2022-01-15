package com.lecuong.service;

import com.lecuong.modal.request.category.CategoryFilterRequest;
import com.lecuong.modal.request.category.CategorySaveRequest;
import com.lecuong.modal.response.category.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    void save(CategorySaveRequest categorySaveRequest);

    void update(CategorySaveRequest categorySaveRequest, Long id);

    void delete(Long id);

    CategoryResponse findById(Long id);

    Page<CategoryResponse> getAll(Pageable pageable);

    Page<CategoryResponse> filter(CategoryFilterRequest categoryFilterRequest);
}
