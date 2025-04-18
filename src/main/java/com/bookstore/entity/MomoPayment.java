package com.bookstore.entity;

public class MomoPayment implements Payment {
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
