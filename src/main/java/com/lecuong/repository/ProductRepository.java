package com.lecuong.repository;

import com.lecuong.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    List<Product> findByIdIn(List<Long> ids);

    Product findByName(String name);
}
