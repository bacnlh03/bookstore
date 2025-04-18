package com.bookstore.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerConfig {
    private static EntityManagerConfig instance;
    private final EntityManagerFactory emf;

    private EntityManagerConfig() {
        emf = Persistence.createEntityManagerFactory("BookStorePU");
    }

    public static synchronized EntityManagerConfig getInstance() {
        if (instance == null) {
            instance = new EntityManagerConfig();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
