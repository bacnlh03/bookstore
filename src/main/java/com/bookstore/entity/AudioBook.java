package com.bookstore.entity;

import com.bookstore.enums.BookType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AUDIO")
public class AudioBook extends Book {
    public AudioBook() {}

    public AudioBook(String title, String author, double price, int stock) {
        super(title, author, price, stock);
    }

    @Override
    public BookType getBookType() {
        return BookType.AUDIOBOOK;
    }
}
