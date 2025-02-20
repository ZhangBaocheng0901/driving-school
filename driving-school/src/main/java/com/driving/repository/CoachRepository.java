// src/main/java/com/driving/repository/CoachRepository.java
package com.driving.repository;

import com.driving.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}