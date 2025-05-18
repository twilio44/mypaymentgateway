package com.paymentgateway.service;

import com.paymentgateway.payload.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface PaymentService {
    Charge charge(PaymentRequest paymentRequest) throws StripeException;
}
