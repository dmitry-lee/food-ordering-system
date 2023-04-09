package com.food.ordering.system.order.domain.service;

import com.food.ordering.system.order.domain.service.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.domain.service.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.domain.service.mapper.OrderDataMapper;
import com.food.ordering.system.order.domain.service.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("Couldn't find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Couldn't find order with tracking id: " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
