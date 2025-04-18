package com.bookstore.service;

import com.bookstore.config.EntityManagerConfig;
import com.bookstore.entity.Admin;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AdminService {
    private final EntityManagerFactory emf;
    private final BookService bookService;

    public AdminService() {
        emf = EntityManagerConfig.getInstance().getEntityManagerFactory();
        this.bookService = new BookService();
    }

    public void createUser(User user) {
        switch (user) {
            case Admin admin -> createAdmin(admin);
            case Customer customer -> createCustomer(customer);
            default -> throw new IllegalArgumentException("Unsupported user type: " + user.getClass());
        }
    }

    public User getUser(Integer userId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(User.class, userId);
        }
    }

    public void updateUser(User updatedUser) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(updatedUser);
            em.getTransaction().commit();
        }
    }

    public void deleteUser(Integer userId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        }
    }

    public List<User> getAllUsers() {
        try (EntityManager em = emf.createEntityManager()) {
            List<User> result = new java.util.ArrayList<>();
            result.addAll(em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList());
            result.addAll(em.createQuery("SELECT a FROM Admin a", Admin.class).getResultList());
            return result;
        }
    }


    private void createCustomer(Customer customer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        }
    }

    private void createAdmin(Admin admin) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        }
    }


    public void createBook(Book book) {
        bookService.createBook(book);
    }

    public Book getBook(int bookId) {
        return bookService.getBookById(bookId);
    }

    public void updateBook(Book book) {
        bookService.updateBook(book);
    }

    public void deleteBook(Book book) {
        bookService.deleteBook(book);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
