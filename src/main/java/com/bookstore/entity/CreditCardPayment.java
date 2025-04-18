package com.bookstore.entity;

public class CreditCardPayment implements Payment {
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
