package com.lecuong.service.impl;

import com.lecuong.entity.Cart;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.cart.CartMapper;
import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.repository.CartItemRepository;
import com.lecuong.repository.CartRepository;
import com.lecuong.service.CartService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    @Override
    public void createCart(CartSaveRequest cartSaveRequest) {
        Cart cart = cartMapper.to(cartSaveRequest);
        cartRepository.save(cart);
    }

    @Override
    public void delete(Long userId) {
        Optional<Cart> cart = cartRepository.findCartByUserId(userId);
        cart.orElseThrow(() -> new BusinessException(StatusTemplate.CATEGORY_NOT_FOUND));
        cartRepository.deleteById(cart.get().getId());
    }
}
