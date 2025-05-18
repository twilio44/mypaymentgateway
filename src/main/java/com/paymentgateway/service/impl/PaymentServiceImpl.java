package com.paymentgateway.service.impl;

import com.paymentgateway.payload.PaymentRequest;
import com.paymentgateway.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;
    @Override
    public Charge charge(PaymentRequest paymentRequest) throws StripeException {
        // Set the Stripe API key
        Stripe.apiKey = stripeApiKey;

        // Create the charge parameters
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount());
        chargeParams.put("currency", paymentRequest.getCurrency());
        chargeParams.put("description", paymentRequest.getDescription());
        chargeParams.put("source", paymentRequest.getStripeToken());

        System.out.println(chargeParams.keySet());
        System.out.println(chargeParams.values());

        // if i use this URL:  http://localhost:8080/payment.html  then source will be different
        // if i use this URL: http://localhost:8080/payment/charge from POSTMAN then source
        // will be different

        // Create and return the charge
        return Charge.create(chargeParams); // Call Stripe API to create a charge
        // Stripe processes the payment and returns a success or failure response to front end
        // if we integrate with front end
    }
}
