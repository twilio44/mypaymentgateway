package com.paymentgateway.controller;

import com.paymentgateway.payload.PaymentRequest;
import com.paymentgateway.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000") // Change the origin as needed
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // http://localhost:8080/payment/charge
    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestBody PaymentRequest paymentRequest) {
        try {
            Charge charge = paymentService.charge(paymentRequest);
            return ResponseEntity.ok("Payment successful with charge ID: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(500).body("Error processing payment: " + e.getMessage());
        }
    }
}

//   To connect with html file


//    @Value("${stripe.api.key}")
//    private String stripeApiKey;
//    @PostMapping("/charge")
//    public String charge(@RequestBody Map<String, Object> data) {
//        Stripe.apiKey = stripeApiKey;
//
//        String token = (String) data.get("stripeToken"); // Extract the token sent from the frontend
//        int amount = (Integer) data.get("amount"); // Amount in cents
//        String currency = (String) data.get("currency"); // Currency (e.g., "usd")
//        String description = (String) data.get("description"); // Payment description
//
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", amount); // Amount in cents
//        chargeParams.put("currency", currency); // Currency (e.g., "usd")
//        chargeParams.put("description", description);
//        chargeParams.put("source", token); // The Stripe token generated on the client side
//
//        try {
//            Charge charge = Charge.create(chargeParams);
//            return "Payment successful!";
//        } catch (StripeException e) {
//            return "Payment failed: " + e.getMessage();
//        }
//    }
//}