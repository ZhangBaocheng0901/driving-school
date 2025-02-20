// src/main/java/com/driving/entity/Admin.java
package com.driving.entity;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "ADMIN";
}