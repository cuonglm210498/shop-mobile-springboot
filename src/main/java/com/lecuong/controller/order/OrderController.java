package com.lecuong.controller.order;

import com.lecuong.modal.request.order.OrderItemSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
