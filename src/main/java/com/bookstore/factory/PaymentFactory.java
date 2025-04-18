package com.bookstore.factory;

import com.bookstore.entity.CreditCardPayment;
import com.bookstore.entity.MomoPayment;
import com.bookstore.entity.Payment;
import com.bookstore.enums.PaymentMethod;

public class PaymentFactory {
    public static Payment createPayment(PaymentMethod method, double amount, String... details) {
        return switch (method) {
            case MOMO -> {
                if (details.length == 0) {
                    throw new IllegalArgumentException("Missing details provided");
                }
                yield new MomoPayment(details[0], amount);
            }
            case CREDIT_CARD -> {
                if (details.length < 3) {
                    throw new IllegalArgumentException("Missing details provided");
                }
                yield new CreditCardPayment(details[0], details[1], details[2], amount);
            }
        };
    }
}
