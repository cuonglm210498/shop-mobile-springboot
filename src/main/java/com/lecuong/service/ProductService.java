package com.lecuong.service;

import com.lecuong.modal.request.product.ProductFilterRequest;
import com.lecuong.modal.request.product.ProductSaveRequest;
import com.lecuong.modal.response.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    void save(ProductSaveRequest productSaveRequest);

    void update(ProductSaveRequest productSaveRequest, Long id);

    void delete(Long id);

    ProductResponse findById(Long id);

    Page<ProductResponse> getAll(Pageable pageable);

    Page<ProductResponse> filter(ProductFilterRequest filterRequest);
}
