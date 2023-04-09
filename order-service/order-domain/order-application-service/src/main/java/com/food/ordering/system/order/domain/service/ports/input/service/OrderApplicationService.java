package com.food.ordering.system.order.domain.service.ports.input.service;

import com.food.ordering.system.order.domain.service.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.domain.service.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.domain.service.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.domain.service.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
