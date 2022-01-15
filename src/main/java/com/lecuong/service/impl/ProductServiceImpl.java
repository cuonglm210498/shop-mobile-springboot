package com.lecuong.service.impl;

import com.lecuong.entity.Product;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.product.ProductMapper;
import com.lecuong.modal.request.product.ProductFilterRequest;
import com.lecuong.modal.request.product.ProductSaveRequest;
import com.lecuong.modal.response.product.ProductResponse;
import com.lecuong.repository.CategoryRepository;
import com.lecuong.repository.ProductRepository;
import com.lecuong.repository.ProviderRepository;
import com.lecuong.repository.specification.ProductSpecification;
import com.lecuong.service.ProductService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProviderRepository providerRepository;

    @Override
    public void save(ProductSaveRequest productSaveRequest) {
        Product product = productMapper.to(productSaveRequest);
        productRepository.save(product);
    }

    @Override
    public void update(ProductSaveRequest productSaveRequest, Long id) {
        productRepository.findById(id)
                .map(product -> {
                    product.setName(productSaveRequest.getName());
                    product.setColor(productSaveRequest.getColor());
                    product.setBatteryCapacity(productSaveRequest.getBatteryCapacity());
                    product.setOperationSystem(productSaveRequest.getOperationSystem());
                    product.setScreenSize(productSaveRequest.getScreenSize());
                    product.setRam(productSaveRequest.getRam());
                    product.setWarranty(productSaveRequest.getWarranty());
                    product.setCpu(productSaveRequest.getCpu());
                    product.setMemory(productSaveRequest.getMemory());
                    product.setThumbnail(productSaveRequest.getThumbnail());
                    product.setRealCamera(productSaveRequest.getRealCamera());
                    product.setFrontCamera(productSaveRequest.getFrontCamera());
                    product.setStatus(productSaveRequest.getStatus());
                    product.setQuantity(productSaveRequest.getQuantity());
                    product.setPrice(productSaveRequest.getPrice());
                    product.setCategory(categoryRepository.findById(productSaveRequest.getCategoryIds()).get());
                    product.setProvider(providerRepository.findById(productSaveRequest.getProviderIds()).get());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new BusinessException(StatusTemplate.PRODUCT_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(() -> new BusinessException(StatusTemplate.PRODUCT_NOT_FOUND));

        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(() -> new BusinessException(StatusTemplate.PRODUCT_NOT_FOUND));

        ProductResponse productResponse = productMapper.to(product.get());

        return productResponse;
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable.previousOrFirst());
        return products.map(productMapper::to);
    }

    @Override
    public Page<ProductResponse> filter(ProductFilterRequest filterRequest) {

        PageRequest pageRequest = PageRequest.of(filterRequest.getPageIndex(), filterRequest.getPageSize());
        Page<Product> products = productRepository.findAll(ProductSpecification.filter(filterRequest), pageRequest.previousOrFirst());
        return products.map(productMapper::to);
    }
}
