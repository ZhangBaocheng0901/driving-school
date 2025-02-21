
package com.driving.repository;

import com.driving.entity.Appointment;
import com.driving.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    // 根据教练ID查询时间段
    List<Appointment> findByCoachId(Long coachId);

    Optional<Coach> findByUsername(String username);
}