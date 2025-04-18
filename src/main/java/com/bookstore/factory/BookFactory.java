package com.bookstore.factory;

import com.bookstore.entity.AudioBook;
import com.bookstore.entity.Book;
import com.bookstore.entity.Ebook;
import com.bookstore.entity.PrintedBook;
import com.bookstore.enums.BookType;

public class BookFactory {
    public static Book createBook(BookType type, String title, String author, double price, int stock) {
        Book book;

        switch (type) {
            case EBOOK -> book = new Ebook(title, author, price, stock);
            case AUDIOBOOK -> book = new AudioBook(title, author, price, stock);
            case PRINTED -> book = new PrintedBook(title, author, price, stock);
            default -> throw new IllegalArgumentException("Invalid book type: " + type);
        }
        return book;
    }
}
