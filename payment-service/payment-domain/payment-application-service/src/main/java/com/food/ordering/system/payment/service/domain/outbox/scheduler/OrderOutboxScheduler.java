package com.food.ordering.system.payment.service.domain.outbox.scheduler;

import com.food.ordering.system.outbox.OutboxScheduler;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentResponseMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderOutboxScheduler implements OutboxScheduler {

    private final OrderOutboxHelper orderOutboxHelper;
    private final PaymentResponseMessagePublisher publisher;

    public OrderOutboxScheduler(OrderOutboxHelper orderOutboxHelper, PaymentResponseMessagePublisher publisher) {
        this.orderOutboxHelper = orderOutboxHelper;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${payment-service.outbox-scheduler-fixed-rate}",
            initialDelayString = "${payment-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() {
        Optional<List<OrderOutboxMessage>> orderOutboxMessagesResponse = orderOutboxHelper
                .getOrderOutboxMessageByOutboxStatus(OutboxStatus.STARTED);
        if (orderOutboxMessagesResponse.isPresent() && orderOutboxMessagesResponse.get().size() > 0) {
            List<OrderOutboxMessage> orderOutboxMessages = orderOutboxMessagesResponse.get();
            log.info("Received {} OrderOuboxMessage with ids {}, sending to kafka!", orderOutboxMessages.size(),
                    orderOutboxMessages.stream().map(orderOutboxMessage ->
                            orderOutboxMessage.getId().toString()).collect(Collectors.joining(",")));
            orderOutboxMessages.forEach(orderOutboxMessage ->
                    publisher.publish(orderOutboxMessage, orderOutboxHelper::updateOutboxMessage));
            log.info("{} OrderOutboxMessage sent to message bus!", orderOutboxMessages.size());
        }
    }
}
