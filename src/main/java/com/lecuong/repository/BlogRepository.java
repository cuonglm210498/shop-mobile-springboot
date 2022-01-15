package com.lecuong.repository;

import com.lecuong.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends BaseRepository<Blog, Long> {

    List<Blog> findByIdIn(List<Long> ids);
}
