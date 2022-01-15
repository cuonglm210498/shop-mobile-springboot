package com.lecuong.repository;

import com.lecuong.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends BaseRepository<Orders, Long> {
}
