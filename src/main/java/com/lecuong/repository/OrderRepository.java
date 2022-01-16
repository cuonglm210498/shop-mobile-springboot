package com.lecuong.repository;

import com.lecuong.entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends BaseRepository<Orders, Long> {

    @Query(value = "SELECT * FROM orders o where o.user_id=:userId", nativeQuery = true)
    Optional<Orders> findOrdersByUserId(Long userId);

    @Query(value = "select c.id from orders c where c.user_id = :userId", nativeQuery = true)
    Long findOrdersIdByUserId(Long userId);
}
