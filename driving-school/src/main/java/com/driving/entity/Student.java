// src/main/java/com/driving/entity/Student.java
package com.driving.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private boolean active;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("student")
    private List<Appointment> appointments;
}