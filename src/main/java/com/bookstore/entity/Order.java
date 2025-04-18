package com.bookstore.entity;

import com.bookstore.enums.PaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {}

    public Order(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderDetail orderDetail : orderDetails) {
            total += orderDetail.getTotalPrice();
        }
        return total;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
    }

    public void updateOrderDetail(Book book, int quantity) {
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getBook().getId().equals(book.getId())) {
                orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order [ID=").append(id)
                .append(", Date=").append(date)
                .append(", Total=").append(calculateTotal())
                .append(", Payment Method=").append(paymentMethod)
                .append("]\n");

        for (OrderDetail detail : orderDetails) {
            sb.append(" - ").append(detail.toString()).append("\n");
        }
        return sb.toString();
    }
}
