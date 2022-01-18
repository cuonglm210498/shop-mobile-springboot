package com.lecuong.service.impl;

import com.lecuong.entity.CartItem;
import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.mapper.cart.CartItemMapper;
import com.lecuong.mapper.product.ProductMapper;
import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.modal.request.cart.CartUpdateRequest;
import com.lecuong.modal.response.cart.CartItemResponse;
import com.lecuong.repository.CartItemRepository;
import com.lecuong.repository.CartRepository;
import com.lecuong.security.UserDetails;
import com.lecuong.service.CartItemService;
import com.lecuong.service.CartService;
import com.lecuong.utils.UserUtils;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class CartItemServiceImpl implements CartItemService {

    private final UserUtils userUtils;
    private final ProductMapper productMapper;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addCart(CartSaveRequest cartSaveRequest) {

        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();

        //Lay ra id cua user dang login
        if (cartRepository.findCartByUserId(userDetails.getUser().getId()).isPresent()) {
            //neu co thong tin cua user trong cart thi sẽ them moi cart item
            cartSaveRequest.setCartId(cartRepository.findCartIdByUserId(userDetails.getUser().getId()));
            addCartItem(cartSaveRequest);
        } else {
            //nguoc lai neu chua co thong tin cua user -> se tao cart cho user và them moi cartitem
            cartService.createCart(cartSaveRequest);
            cartSaveRequest.setCartId(cartRepository.findCartIdByUserId(userDetails.getUser().getId()));
            addCartItem(cartSaveRequest);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<CartItem> cartItemDelete = cartItemRepository.findById(id);
        cartItemDelete.orElseThrow(() -> new BusinessException(StatusTemplate.CART_ITEM_NOT_FOUND));
        cartItemRepository.deleteById(id);
    }

    @Override
    public void update(CartUpdateRequest cartUpdateRequest, Long id) {
        cartItemRepository.findById(id)
                .map(cartItem -> {
                    cartItem.setQuantity(cartUpdateRequest.getQuantity() + cartItem.getQuantity());
                    return cartItemRepository.save(cartItem);
                })
                .orElseThrow(() -> new BusinessException(StatusTemplate.CART_ITEM_NOT_FOUND));
    }

    @Override
    public long getCount() {
        return cartItemRepository.count();
    }

    @Override
    public List<CartItemResponse> getAllItem() {

        //Lay ra id cua user dang login
        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();

        //Lay ra danh sach cac CartItem cua user dang login
        List<CartItem> cartItem = cartItemRepository.getAllCartItemByUserId(userDetails.getUser().getId());
        List<CartItemResponse> cartItemResponses = cartItem.stream().map(cartItemMapper::to).collect(Collectors.toList());

        return cartItemResponses;
    }

    public void addCartItem(CartSaveRequest cartSaveRequest) {

        CartItem cartItem = cartItemMapper.to(cartSaveRequest);
        Optional<CartItem> cartItemOptional = cartItemRepository.findByProductIdAndCartId(cartSaveRequest.getProductId(), cartSaveRequest.getCartId());

        if (cartItemOptional.isPresent()) {
            cartItemRepository.findById(cartItemOptional.get().getId())
                    .map(cartItemUpdateQuantity -> {
                        cartItemUpdateQuantity.setQuantity(cartItemUpdateQuantity.getQuantity() + 1);
                        return cartItemRepository.save(cartItemUpdateQuantity);
                    })
                    .orElseThrow(() -> new BusinessException(StatusTemplate.CART_ITEM_NOT_FOUND));
        } else {
            cartItemRepository.save(cartItem);
        }
    }
}
