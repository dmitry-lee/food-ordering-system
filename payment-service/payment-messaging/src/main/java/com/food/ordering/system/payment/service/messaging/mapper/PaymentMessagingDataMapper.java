package com.food.ordering.system.payment.service.messaging.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentStatus;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentResponseAvroModel
    paymentCompletedEventToPaymentResponseAvroModel(PaymentCompletedEvent paymentCompletedEvent) {
        return getPaymentResponseAvroModel(paymentCompletedEvent);
    }

    public PaymentResponseAvroModel
    paymentCancelledEventToPaymentResponseAvroModel(PaymentCancelledEvent paymentCancelledEvent) {
        return getPaymentResponseAvroModel(paymentCancelledEvent);
    }

    public PaymentResponseAvroModel
    paymentFailedEventToPaymentResponseAvroModel(PaymentFailedEvent paymentFailedEvent) {
        return getPaymentResponseAvroModel(paymentFailedEvent);
    }

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .id(paymentRequestAvroModel.getId())
                .sagaId("")
                .orderId(paymentRequestAvroModel.getOrderId())
                .customerId(new CustomerId(UUID.fromString(paymentRequestAvroModel.getCustomerId())))
                .price(paymentRequestAvroModel.getPrice())
                .createdAt(paymentRequestAvroModel.getCreatedAt())
                .paymentOrderStatus(com.food.ordering.system.domain.valueobject.PaymentOrderStatus
                        .valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
                .build();
    }

    private PaymentResponseAvroModel getPaymentResponseAvroModel(PaymentEvent paymentEvent) {
        Payment payment = paymentEvent.getPayment();
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setPaymentId(payment.getId().getValue().toString())
                .setSagaId("")
                .setCustomerId(payment.getCustomerId().getValue().toString())
                .setOrderId(payment.getOrderId().getValue().toString())
                .setPrice(payment.getPrice().getAmount())
                .setCreatedAt(payment.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(payment.getPaymentStatus().name()))
                .setFailureMessages(paymentEvent.getFailureMessages())
                .build();
    }
}
