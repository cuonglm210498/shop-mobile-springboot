package com.lecuong.repository;

import com.lecuong.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends BaseRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart c WHERE c.user_id=:id", nativeQuery = true)
    Optional<Cart> findCartByUserId(@Param("id") Long id);
}
