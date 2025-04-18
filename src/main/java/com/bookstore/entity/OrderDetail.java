package com.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public OrderDetail() {}

    public OrderDetail(Order order, Book book, int quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.unitPrice = book.getPrice();
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Order getOrder() {
        return order;
    }

    public Book getBook() {
        return book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail [Book=" + book.getTitle() + ", Quantity=" + quantity +
                ", Unit Price=" + unitPrice + ", Total=" + getTotalPrice() + "]";
    }
}
