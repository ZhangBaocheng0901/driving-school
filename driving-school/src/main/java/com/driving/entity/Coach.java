// src/main/java/com/driving/entity/Coach.java
package com.driving.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;
    private boolean active;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("coach")
    private List<Appointment> appointments;
}