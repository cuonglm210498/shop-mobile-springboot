package com.lecuong.mapper.cart;

import com.lecuong.entity.Cart;
import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.repository.UserRepository;
import com.lecuong.security.UserDetails;
import com.lecuong.utils.BeanUtils;
import com.lecuong.utils.UserUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartMapper {

    private final UserUtils userUtils;
    private final UserRepository userRepository;

    public Cart to(CartSaveRequest cartSaveRequest) {
        Cart cart = new Cart();
        BeanUtils.refine(cartSaveRequest, cart, BeanUtils::copyNonNull);

        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();
        cart.setUser(userRepository.findById(userDetails.getUser().getId()).get());
        return cart;
    }
}
