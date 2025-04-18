package com.bookstore.entity;

import com.bookstore.enums.BookType;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "book_type")
public abstract class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    public Book() {}

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public abstract BookType getBookType();

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int newStock) {
        this.stock = newStock;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author +
                ", Price=" + price + ", Stock=" + stock + "]";
    }
}
