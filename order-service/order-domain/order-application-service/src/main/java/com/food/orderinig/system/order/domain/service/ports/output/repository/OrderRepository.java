package com.food.orderinig.system.order.domain.service.ports.output.repository;

import com.food.ordering.system.order.service.domian.entity.Order;
import com.food.ordering.system.order.service.domian.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
