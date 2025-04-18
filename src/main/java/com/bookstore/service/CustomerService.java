package com.bookstore.service;

import com.bookstore.config.EntityManagerConfig;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CustomerService {

    private final EntityManagerFactory emf;
    private final BookService bookService;

    public CustomerService() {
        this.emf = EntityManagerConfig.getInstance().getEntityManagerFactory();
        this.bookService = new BookService();
    }

    public List<Book> browseBooks() {
        return bookService.getAllBooks();
    }

    public void placeOrder(Customer customer, Order order) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            order.setCustomer(customer);
            customer.getOrders().add(order);

            em.merge(customer);
            em.getTransaction().commit();
        }
    }

    public void cancelOrder(Customer customer, Order order) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            
            customer.getOrders().remove(order);
            em.remove(em.contains(order) ? order : em.merge(order));

            em.merge(customer);
            em.getTransaction().commit();
        }
    }
}
