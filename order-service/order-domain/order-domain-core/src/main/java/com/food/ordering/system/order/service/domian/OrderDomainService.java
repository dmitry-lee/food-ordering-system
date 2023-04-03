package com.food.ordering.system.order.service.domian;

import com.food.ordering.system.order.service.domian.entity.Order;
import com.food.ordering.system.order.service.domian.entity.Restaurant;
import com.food.ordering.system.order.service.domian.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domian.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domian.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
