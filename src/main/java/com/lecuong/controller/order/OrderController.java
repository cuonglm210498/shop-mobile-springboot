package com.lecuong.controller.order;

import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.order.OrderResponse;
import com.lecuong.service.OrderService;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createOrder(@RequestBody OrderItemSaveRequest orderItemSaveRequest) {
        orderService.createOrder(orderItemSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<OrderResponse>> getOrder() {
        OrderResponse orderResponse = orderService.getOrder();
        return ResponseEntity.ok(BaseResponse.ofSuccess(orderResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }
}
