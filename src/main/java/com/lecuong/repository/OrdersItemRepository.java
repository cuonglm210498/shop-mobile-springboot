package com.lecuong.repository;

import com.lecuong.entity.OrdersItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersItemRepository extends BaseRepository<OrdersItem, Long> {
}
