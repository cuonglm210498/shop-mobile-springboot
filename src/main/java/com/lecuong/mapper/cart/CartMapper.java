package com.lecuong.mapper.cart;

import com.lecuong.entity.Cart;
import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.repository.UserRepository;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartMapper {

    private final UserRepository userRepository;

    public Cart to(CartSaveRequest cartSaveRequest) {
        Cart cart = new Cart();
        BeanUtils.refine(cartSaveRequest, cart, BeanUtils::copyNonNull);
        cart.setUser(userRepository.findById(cartSaveRequest.getUserId()).get());
        return cart;
    }
}
