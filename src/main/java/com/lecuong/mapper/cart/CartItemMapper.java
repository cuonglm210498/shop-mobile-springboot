package com.lecuong.mapper.cart;

import com.lecuong.entity.CartItem;
import com.lecuong.entity.Product;
import com.lecuong.mapper.product.ProductMapper;
import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.modal.response.cart.CartItemResponse;
import com.lecuong.modal.response.product.ProductResponse;
import com.lecuong.repository.CartRepository;
import com.lecuong.repository.ProductRepository;
import com.lecuong.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartItemMapper {

    private final ProductMapper productMapper;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItem to(CartSaveRequest cartSaveRequest) {
        CartItem cartItem = new CartItem();
        BeanUtils.refine(cartSaveRequest, cartItem, BeanUtils::copyNonNull);
        cartItem.setCart(cartRepository.findById(cartSaveRequest.getCartId()).get());
        cartItem.setProduct(productRepository.findById(cartSaveRequest.getProductId()).get());
        return cartItem;
    }

    public CartItemResponse to(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();
        BeanUtils.refine(cartItem, cartItemResponse, BeanUtils::copyNonNull);

        Product product = productRepository.findById(cartItem.getProduct().getId()).get();
        ProductResponse productResponse = productMapper.to(product);

        cartItemResponse.setProductResponse(productResponse);
        return cartItemResponse;
    }
}
