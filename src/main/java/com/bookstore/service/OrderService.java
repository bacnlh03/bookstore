package com.bookstore.service;

import com.bookstore.config.EntityManagerConfig;
import com.bookstore.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrderService {
    private final EntityManagerFactory emf;

    public OrderService() {
        emf = EntityManagerConfig.getInstance().getEntityManagerFactory();
    }

    public List<Order> getAllOrders(Integer customerId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Order> query = em.createQuery(
                    "SELECT o FROM Order o WHERE o.customer.id = :customerId",
                    Order.class
            );
            query.setParameter("customerId", customerId);
            List<Order> orders = query.getResultList();
            em.getTransaction().commit();
            return orders;
        }
    }

    public Order getOrder(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Order order = em.find(Order.class, id);
            em.getTransaction().commit();
            return order;
        }
    }

    public void createOrder(Order order) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        }
    }
}
