package com.lecuong.repository.specification;

import com.lecuong.entity.Category;
import com.lecuong.modal.request.category.CategoryFilterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    public static Specification<Category> filter(CategoryFilterRequest filterRequest) {
        return Specification.where(withName(filterRequest.getName()));
    }

    public static Specification<Category> withName(String name) {
        if (StringUtils.isBlank(name))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }
}
