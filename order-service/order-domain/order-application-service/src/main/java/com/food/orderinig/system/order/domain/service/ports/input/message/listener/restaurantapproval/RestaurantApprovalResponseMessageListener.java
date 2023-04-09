package com.food.orderinig.system.order.domain.service.ports.input.message.listener.restaurantapproval;

import com.food.orderinig.system.order.domain.service.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}