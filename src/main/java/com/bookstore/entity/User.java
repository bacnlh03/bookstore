package com.bookstore.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Login successful for user: " + name);
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public void logout() {
        System.out.println("User " + name + " logged out successfully.");
    }
}
