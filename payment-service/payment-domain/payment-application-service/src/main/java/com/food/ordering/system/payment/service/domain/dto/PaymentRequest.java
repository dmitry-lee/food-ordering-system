package com.food.ordering.system.payment.service.domain.dto;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.PaymentOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {

    private String id;
    private String sagaId;
    private String orderId;
    private CustomerId customerId;
    private BigDecimal price;
    private PaymentOrderStatus paymentOrderStatus;
    private Instant createdAt;

    public void setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }
}
