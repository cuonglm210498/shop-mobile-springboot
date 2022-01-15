package com.lecuong.modal.response.blog;

import com.lecuong.entity.Category;
import com.lecuong.entity.User;
import lombok.Data;

@Data
public class BlogResponse {

    private long id;
    private String thumbnail;
    private String url;
    private String name;
    private String description;
    private String title;
    private String content;
    private String categoryName;
    private String author;
}
