package com.lecuong.mapper.blog;

import com.lecuong.entity.Blog;
import com.lecuong.modal.response.blog.BlogResponse;
import com.lecuong.utils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public BlogResponse to(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        BeanUtils.refine(blog, blogResponse, BeanUtils::copyNonNull);

        if (blog.getCategory() != null) {
            blogResponse.setCategoryName(blog.getCategory().getName());
        }

        if (blog.getUser() != null) {
            blogResponse.setAuthor(blog.getUser().getUserName());
        }

        return blogResponse;
    }
}
