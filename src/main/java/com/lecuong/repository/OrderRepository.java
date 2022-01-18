package com.lecuong.repository;

import com.lecuong.entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends BaseRepository<Orders, Long> {

    @Query(value = "SELECT * FROM orders o where o.user_id=:userId", nativeQuery = true)
    Optional<Orders> findOrdersByUserId(Long userId);

    @Query(value = "select o.id from orders o\n" +
            "where o.user_id = :userId\n" +
            "order by o.id desc limit 1;", nativeQuery = true)
    Long findOrdersIdDescByUserId(Long userId);

    @Query(value = "select o.* from orders o\n" +
            "where o.user_id = :userId\n" +
            "order by o.id desc limit 1;", nativeQuery = true)
    Orders findOrdersDescByUserId(Long userId);
}
