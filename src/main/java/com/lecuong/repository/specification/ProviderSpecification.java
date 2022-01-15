package com.lecuong.repository.specification;

import com.lecuong.entity.Provider;
import com.lecuong.modal.request.provider.ProviderSaveRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {

    public static Specification<Provider> filter(ProviderSaveRequest filterRequest) {
        return Specification.where(withProviderName(filterRequest.getName()));
    }

    public static Specification<Provider> withProviderName(String name) {
        if (StringUtils.isBlank(name))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }


}
