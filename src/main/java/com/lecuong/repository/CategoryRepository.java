package com.lecuong.repository;

import com.lecuong.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    Category findByName(String name);
}
