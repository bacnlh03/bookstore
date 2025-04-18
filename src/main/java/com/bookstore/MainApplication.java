package com.bookstore;

import com.bookstore.entity.*;
import com.bookstore.enums.BookType;
import com.bookstore.enums.PaymentMethod;
import com.bookstore.factory.BookFactory;
import com.bookstore.factory.PaymentFactory;
import com.bookstore.service.AdminService;
import com.bookstore.service.BookService;
import com.bookstore.service.OrderService;

public class MainApplication {
    public static void main(String[] args) {
        final var bookService = new BookService();
        final var adminService = new AdminService();
        final var orderService = new OrderService();

        /// Create new customer
        Customer customer1 = new Customer(
                "User 1",
                "user1@gmail.com",
                "123",
                "0987654321",
                "HCM"
        );
        adminService.createUser(customer1);

        /// Create new book
        Book book1 = BookFactory.createBook(
                BookType.EBOOK,
                "Ebook",
                "Author",
                5.99,
                50
        );
        bookService.createBook(book1);

        /// Create new order
        final var paymentMethod = PaymentMethod.MOMO;
        Order order1 = new Order(
                paymentMethod,
                customer1
        );
        order1.addOrderDetail(new OrderDetail(
                order1,
                book1,
                1
        ));
        orderService.createOrder(order1);

        /// Process payment
        Payment payment = PaymentFactory.createPayment(
                paymentMethod,
                order1.calculateTotal(),
                "0987654321"
        );
        System.out.println("Payment successfully: " + payment.process());
    }
}