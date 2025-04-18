package com.bookstore.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Order> orders = new ArrayList<>();

    public Customer() {}

    public Customer(
            String name,
            String email,
            String password,
            String phoneNumber,
            String address
    ) {
        super(name, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
    }
}
