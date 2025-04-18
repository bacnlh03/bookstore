package com.bookstore.factory;

import com.bookstore.enums.PaymentMethod;

interface Payment {
    boolean process();
}

class MomoPayment implements Payment {
    private final String phoneNumber;
    private final double amount;

    public MomoPayment(String phoneNumber, double amount) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    @Override
    public boolean process() {
        System.out.println("Processing Momo payment of " + amount +
                " via phone number: " + phoneNumber);
        return true;
    }
}

class CreditCardPayment implements Payment {
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;
    private final double amount;

    public CreditCardPayment(String cardNumber, String expiryDate, String cvv, double amount) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.amount = amount;
    }

    @Override
    public boolean process() {
        System.out.println("Processing credit card payment of " + amount +
                " via card: " + maskCardNumber(cardNumber));
        return true;
    }

    private String maskCardNumber(String number) {
        if (number.length() <= 4) return number;
        String lastFour = number.substring(number.length() - 4);
        return "XXXX-XXXX-XXXX-" + lastFour;
    }
}

class PaymentFactory {
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
