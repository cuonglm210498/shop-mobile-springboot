package com.lecuong.controller.cart;

import com.lecuong.modal.request.cart.CartSaveRequest;
import com.lecuong.modal.request.cart.CartUpdateRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.cart.CartItemResponse;
import com.lecuong.service.CartItemService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> addCart(@RequestBody CartSaveRequest cartSaveRequest) {
        cartItemService.addCart(cartSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> updateCart(@RequestBody CartUpdateRequest cartUpdateRequest,
                                                         @PathVariable long id) {
        cartItemService.update(cartUpdateRequest, id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable long id) {
        cartItemService.delete(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping("/get-count")
    public ResponseEntity<BaseResponse<Long>> getCount() {
        long count = cartItemService.getCount();
        return ResponseEntity.ok(BaseResponse.ofSuccess(count));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CartItemResponse>>> getAllCart() {
        List<CartItemResponse> cartItemResponses = cartItemService.getAllItem();
        return ResponseEntity.ok(BaseResponse.ofSuccess(cartItemResponses));
    }
}
