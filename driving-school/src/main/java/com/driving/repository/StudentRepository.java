// src/main/java/com/driving/repository/StudentRepository.java
package com.driving.repository;

import com.driving.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}