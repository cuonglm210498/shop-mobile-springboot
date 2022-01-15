package com.lecuong.mapper.category;

import com.lecuong.entity.Blog;
import com.lecuong.entity.Category;
import com.lecuong.entity.Product;
import com.lecuong.modal.request.category.CategorySaveRequest;
import com.lecuong.modal.response.category.CategoryResponse;
import com.lecuong.repository.BlogRepository;
import com.lecuong.repository.CategoryRepository;
import com.lecuong.repository.ProductRepository;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class CategoryMapper {

    private final BlogRepository blogRepository;
    private final ProductRepository productRepository;

    public Category to(CategorySaveRequest categorySaveRequest) {
        Category category = new Category();
        BeanUtils.refine(categorySaveRequest, category, BeanUtils::copyNonNull);
//        List<Blog> listBlogId = new ArrayList<>(blogRepository.findByIdIn(categorySaveRequest.getBlogIds()));
//        List<Product> listProductId = new ArrayList<>(productRepository.findByIdIn(categorySaveRequest.getProductIds()));
//        category.setBlogs(listBlogId);
//        category.setProducts(listProductId);
        return category;
    }

    public CategoryResponse to(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.refine(category, categoryResponse, BeanUtils::copyNonNull);
//        List<String> blogs = category.getBlogs().stream().map(Blog::getName).collect(Collectors.toList());
//        List<String> products = category.getProducts().stream().map(Product::getName).collect(Collectors.toList());
//        categoryResponse.setBlogName(blogs);
//        categoryResponse.setProductName(products);
        return categoryResponse;
    }
}
