package com.food.orderinig.system.order.domain.service.ports.output.repository;

import com.food.ordering.system.order.service.domian.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
}
