package com.lecuong.repository;

import com.lecuong.entity.OrdersItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends BaseRepository<OrdersItem, Long> {

//    @Query(value = "SELECT * FROM order_item oi WHERE oi.order_id = :orderId AND oi.product_id = :productId", nativeQuery = true)
//    Optional<OrdersItem> findByCartIdAndProductId(Long order_id, Long productId);
}
