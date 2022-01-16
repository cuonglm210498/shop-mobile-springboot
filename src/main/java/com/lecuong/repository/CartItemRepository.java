package com.lecuong.repository;

import com.lecuong.entity.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends BaseRepository<CartItem, Long> {

    @Query(value = "SELECT * FROM cart_item ct WHERE ct.product_id=:productId AND ct.cart_id=:cartId", nativeQuery = true)
    Optional<CartItem> findByProductIdAndCartId(Long productId, Long cartId);

    @Query(value = "select ct.* from cart_item ct\n" +
            "inner join cart c on ct.cart_id = c.id\n" +
            "inner join product p on p.id = ct.product_id\n" +
            "where c.user_id = :userId", nativeQuery = true)
    List<CartItem> getAllCartItemByUserId(Long userId);
}
