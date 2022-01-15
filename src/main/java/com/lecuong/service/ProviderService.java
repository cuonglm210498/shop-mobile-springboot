package com.lecuong.service;

import com.lecuong.modal.request.provider.ProviderSaveRequest;
import com.lecuong.modal.response.provider.ProviderResponse;
import com.lecuong.repository.specification.ProviderSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProviderService {

    void save(ProviderSaveRequest providerSaveRequest);

    void update(ProviderSaveRequest providerSaveRequest, Long id);

    void delete(Long id);

    ProviderResponse findById(Long id);

    Page<ProviderResponse> getAll(Pageable pageable);

    Page<ProviderResponse> filter(ProviderSaveRequest providerSaveRequest);
}
