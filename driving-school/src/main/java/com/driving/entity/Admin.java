
package com.driving.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Admin {
    @SuppressWarnings("checkstyle:JavadocVariable")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private String username;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private String password;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private String email;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private LocalDateTime createdAt;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private LocalDateTime updatedAt;
}
