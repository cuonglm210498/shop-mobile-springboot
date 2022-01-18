package com.lecuong.mapper.order;

import com.lecuong.entity.Orders;
import com.lecuong.modal.response.order.OrderResponse;
import com.lecuong.security.UserDetails;
import com.lecuong.utils.BeanUtils;
import com.lecuong.utils.UserUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderMapper {

    private final UserUtils userUtils;

    public OrderResponse to(Orders orders) {
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.refine(orders, orderResponse, BeanUtils::copyNonNull);

        UserDetails userDetails = userUtils.getUserDetailsFromSecurityContext();
        String orderer = userDetails.getUser().getUserName();
        orderResponse.setOrderer(orderer);

        return orderResponse;
    }
}
