package com.bookstore;

import com.bookstore.entity.*;
import com.bookstore.enums.BookType;
import com.bookstore.factory.BookFactory;
import com.bookstore.service.BookService;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        BookService bookService = new BookService();

        Book book = BookFactory.createBook(
                BookType.EBOOK,
                "Ebook",
                "Author",
                5.99,
                50
        );

        bookService.createBook(book);
        List<Book> books = bookService.getAllBooks();
        System.out.println(books);
    }
}