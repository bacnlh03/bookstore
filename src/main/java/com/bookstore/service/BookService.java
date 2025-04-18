package com.bookstore.service;

import com.bookstore.config.EntityManagerConfig;
import com.bookstore.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookService {
    private final EntityManagerFactory emf;

    public BookService() {
        emf = EntityManagerConfig.getInstance().getEntityManagerFactory();
    }

    public List<Book> getAllBooks() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
            List<Book> books = query.getResultList();
            em.getTransaction().commit();
            return books;
        }
    }

    public Book getBookById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            em.getTransaction().commit();
            return book;
        }
    }

    public void createBook(Book book) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        }
    }

    public void updateBook(Book book) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
        }
    }

    public void deleteBook(Book book) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
        }
    }
}
