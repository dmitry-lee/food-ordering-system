package com.food.orderinig.system.order.domain.service.ports.output.repository;

import com.food.ordering.system.order.service.domian.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
