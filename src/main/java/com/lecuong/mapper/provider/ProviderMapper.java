package com.lecuong.mapper.provider;

import com.lecuong.entity.Provider;
import com.lecuong.modal.request.provider.ProviderSaveRequest;
import com.lecuong.modal.response.provider.ProviderResponse;
import com.lecuong.utils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public ProviderResponse to(Provider provider) {
        ProviderResponse providerResponse = new ProviderResponse();
        BeanUtils.refine(provider, providerResponse, BeanUtils::copyNonNull);
        return providerResponse;
    }

    public Provider to(ProviderSaveRequest providerSaveRequest) {
        Provider provider = new Provider();
        BeanUtils.refine(providerSaveRequest, provider, BeanUtils::copyNonNull);
        return provider;
    }
}
