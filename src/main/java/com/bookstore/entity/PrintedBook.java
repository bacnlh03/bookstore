package com.bookstore.entity;

import com.bookstore.enums.BookType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRINTED")
public class PrintedBook extends Book {
    public PrintedBook() {}

    public PrintedBook(String title, String author, double price, int stock) {
        super(title, author, price, stock);
    }

    @Override
    public BookType getBookType() {
        return BookType.PRINTED;
    }
}
