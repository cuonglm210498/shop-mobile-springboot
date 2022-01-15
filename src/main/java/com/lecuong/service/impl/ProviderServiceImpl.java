package com.lecuong.service.impl;

import com.lecuong.entity.Provider;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.provider.ProviderMapper;
import com.lecuong.modal.request.provider.ProviderSaveRequest;
import com.lecuong.modal.response.provider.ProviderResponse;
import com.lecuong.repository.ProviderRepository;
import com.lecuong.repository.specification.ProviderSpecification;
import com.lecuong.service.ProviderService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Override
    public void save(ProviderSaveRequest providerSaveRequest) {

        if (providerRepository.findByName(providerSaveRequest.getName()) != null) {
            throw new BusinessException(StatusTemplate.PROVIDER_EXIST);
        }

        Provider provider = providerMapper.to(providerSaveRequest);
        providerRepository.save(provider);
    }

    @Override
    public void update(ProviderSaveRequest providerSaveRequest, Long id) {
        providerRepository.findById(id)
                .map(provider -> {
                    provider.setName(provider.getName());
                    return providerRepository.save(provider);
                })
                .orElseThrow(() -> new BusinessException(StatusTemplate.PROVIDER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        provider.orElseThrow(() -> new BusinessException(StatusTemplate.PROVIDER_NOT_FOUND));

        providerRepository.deleteById(id);
    }

    @Override
    public ProviderResponse findById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        provider.orElseThrow(() -> new BusinessException(StatusTemplate.PROVIDER_NOT_FOUND));

        ProviderResponse providerResponse = providerMapper.to(provider.get());

        return providerResponse;
    }

    @Override
    public Page<ProviderResponse> getAll(Pageable pageable) {
        Page<Provider> providers = providerRepository.findAll(pageable.previousOrFirst());
        return providers.map(providerMapper::to);
    }

    @Override
    public Page<ProviderResponse> filter(ProviderSaveRequest providerSaveRequest) {
        PageRequest pageRequest = PageRequest.of(providerSaveRequest.getPageIndex(),providerSaveRequest.getPageSize());
        Page<Provider> providers = providerRepository.findAll(ProviderSpecification.filter(providerSaveRequest), pageRequest.previousOrFirst());
        return providers.map(providerMapper::to);
    }


}
