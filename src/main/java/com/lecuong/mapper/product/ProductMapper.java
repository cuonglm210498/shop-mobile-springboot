package com.lecuong.mapper.product;

import com.lecuong.entity.Product;
import com.lecuong.modal.request.product.ProductSaveRequest;
import com.lecuong.modal.response.product.ProductResponse;
import com.lecuong.repository.CategoryRepository;
import com.lecuong.repository.ProductRepository;
import com.lecuong.repository.ProviderRepository;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductMapper {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final CategoryRepository categoryRepository;

    public Product to(ProductSaveRequest productSaveRequest) {
        Product product = new Product();
        BeanUtils.refine(productSaveRequest, product, BeanUtils::copyNonNull);
        product.setProvider(providerRepository.findById(productSaveRequest.getProviderIds()).get());
        product.setCategory(categoryRepository.findById(productSaveRequest.getCategoryIds()).get());
        return product;
    }

    public ProductResponse to(Product product) {
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.refine(product, productResponse, BeanUtils::copyNonNull);

        if (product.getCategory() != null)
            productResponse.setCategoryName(product.getCategory().getName());
        if (product.getProvider() != null)
            productResponse.setProviderName(product.getProvider().getName());

        return productResponse;
    }

}
