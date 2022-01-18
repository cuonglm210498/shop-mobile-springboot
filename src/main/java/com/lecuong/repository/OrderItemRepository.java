package com.lecuong.repository;

import com.lecuong.entity.OrdersItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends BaseRepository<OrdersItem, Long> {

    @Query(value = "select oi.* from orders_item oi\n" +
            "inner join orders o on oi.order_id = o.id\n" +
            "where o.user_id = :userId and o.id = :orderId", nativeQuery = true)
    List<OrdersItem> findAllByUserIdAndOrderId(Long userId, Long orderId);
}
