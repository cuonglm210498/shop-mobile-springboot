package com.lecuong.modal.request.blog;

import com.lecuong.modal.request.BaseRequest;
import lombok.Data;

@Data
public class BlogFilterRequest extends BaseRequest {

    private String name;
    private String url;
    private long categotyId;
    private long userId;
    private String keyWord;
}
