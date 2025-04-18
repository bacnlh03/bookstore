package com.bookstore.entity;

import com.bookstore.enums.BookType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EBOOK")
public class Ebook extends Book {
    public Ebook() {}

    public Ebook(String title, String author, double price, int stock) {
        super(title, author, price, stock);
    }

    @Override
    public BookType getBookType() {
        return BookType.EBOOK;
    }
}

