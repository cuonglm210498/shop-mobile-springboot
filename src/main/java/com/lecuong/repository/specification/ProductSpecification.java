package com.lecuong.repository.specification;

import com.lecuong.entity.Product;
import com.lecuong.modal.request.product.ProductFilterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> filter(ProductFilterRequest filterRequest) {
        return Specification.where(withName(filterRequest.getName()))
                .and(withColor(filterRequest.getColor()))
                .and(withBatteryCapacity(filterRequest.getBatteryCapacity()))
                .and(withOperationSystem(filterRequest.getOperationSystem()))
                .and(withScreenSize(filterRequest.getScreenSize()))
                .and(withRam(filterRequest.getRam()))
                .and(withCpu(filterRequest.getCpu()))
                .and(withMemory(filterRequest.getMemory()))
                .and(withRealCamera(filterRequest.getRealCamera()))
                .and(withFrontCamera(filterRequest.getFrontCamera()))
                .and(withPrice(filterRequest.getPrice()))
//                .or(withCategoryId(filterRequest.getCategoryIds()))
//                .or(withProviderId(filterRequest.getProviderIds()))
                .or(withNameLike(filterRequest.getName()));
    }

    public static Specification<Product> withName(String name) {
        if (StringUtils.isBlank(name))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Product> withNameLike(String name) {
        if (StringUtils.isBlank(name))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> withColor(String color) {
        if (StringUtils.isBlank(color))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Product> withBatteryCapacity(String batteryCapacity) {
        if (StringUtils.isBlank(batteryCapacity))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("batteryCapacity"), batteryCapacity);
    }

    public static Specification<Product> withOperationSystem(String operationSystem) {
        if (StringUtils.isBlank(operationSystem))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("operationSystem"), operationSystem);
    }

    public static Specification<Product> withScreenSize(String screenSize) {
        if (StringUtils.isBlank(screenSize))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("screenSize"), screenSize);
    }

    public static Specification<Product> withRam(String ram) {
        if (StringUtils.isBlank(ram))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ram"), ram);
    }

    public static Specification<Product> withCpu(String cpu) {
        if (StringUtils.isBlank(cpu))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpu"), cpu);
    }

    public static Specification<Product> withMemory(String memory) {
        if (StringUtils.isBlank(memory))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("memory"), memory);
    }

    public static Specification<Product> withRealCamera(String realCamera) {
        if (StringUtils.isBlank(realCamera))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("realCamera"), realCamera);
    }

    public static Specification<Product> withFrontCamera(String frontCamera) {
        if (StringUtils.isBlank(frontCamera))
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("frontCamera"), frontCamera);
    }

    public static Specification<Product> withPrice(Double price) {
        if (price <= 0 || price == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<Product> withCategoryId(Long categoryId) {
        if (categoryId == 0 || categoryId == null)
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("category").get("id"), categoryId);
    }

    public static Specification<Product> withProviderId(Long providerId) {
        if (providerId == 0 || providerId == null)
            return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("provider").get("id"), providerId);
    }
}
