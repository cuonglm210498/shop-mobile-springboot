package com.lecuong.service.impl;

import com.lecuong.entity.Category;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.category.CategoryMapper;
import com.lecuong.modal.request.category.CategoryFilterRequest;
import com.lecuong.modal.request.category.CategorySaveRequest;
import com.lecuong.modal.response.category.CategoryResponse;
import com.lecuong.repository.CategoryRepository;
import com.lecuong.repository.specification.CategorySpecification;
import com.lecuong.service.CategoryService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void save(CategorySaveRequest categorySaveRequest) {
        if (categoryRepository.findByName(categorySaveRequest.getName()) != null) {
            throw new BusinessException(StatusTemplate.CATEGORY_EXIST);
        }

        Category category = categoryMapper.to(categorySaveRequest);
        categoryRepository.save(category);
    }

    @Override
    public void update(CategorySaveRequest categorySaveRequest, Long id) {
        categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categorySaveRequest.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new BusinessException(StatusTemplate.CATEGORY_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(() -> new BusinessException(StatusTemplate.CATEGORY_NOT_FOUND));

        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(() -> new BusinessException(StatusTemplate.CATEGORY_NOT_FOUND));

        CategoryResponse categoryResponse = categoryMapper.to(category.get());
        return categoryResponse;
    }

    @Override
    public Page<CategoryResponse> getAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable.previousOrFirst());
        return categories.map(categoryMapper::to);
    }

    @Override
    public Page<CategoryResponse> filter(CategoryFilterRequest categoryFilterRequest) {
        PageRequest pageRequest = PageRequest.of(categoryFilterRequest.getPageIndex(), categoryFilterRequest.getPageSize());
        Page<Category> categories = categoryRepository.findAll(CategorySpecification.filter(categoryFilterRequest),pageRequest.previousOrFirst());

        return categories.map(categoryMapper::to);
    }
}
