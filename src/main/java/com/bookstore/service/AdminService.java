package com.bookstore.service;

import com.bookstore.config.EntityManagerConfig;
import com.bookstore.entity.Admin;
import com.bookstore.entity.Customer;
import com.bookstore.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AdminService {
    private final EntityManagerFactory emf;

    public AdminService() {
        emf = EntityManagerConfig.getInstance().getEntityManagerFactory();
    }

    public void createUser(User user) {
        switch (user) {
            case Admin admin -> createAdmin(admin);
            case Customer customer -> createCustomer(customer);
            default -> throw new IllegalArgumentException("Unsupported user type: " + user.getClass());
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
}
