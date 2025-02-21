
package com.driving.repository;

import com.driving.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 按学生ID和时间范围查询预约
    @Query("SELECT a FROM Appointment a WHERE a.student.id = :studentId AND a.startTime BETWEEN :startDate AND :endDate")
    List<Appointment> findByStudentIdAndTimeRange(Long studentId, LocalDateTime startDate, LocalDateTime endDate);

    // 按教练ID和时间范围查询预约
    @Query("SELECT a FROM Appointment a WHERE a.coach.id = :coachId AND a.startTime BETWEEN :startDate AND :endDate")
    List<Appointment> findByCoachIdAndTimeRange(Long coachId, LocalDateTime startDate, LocalDateTime endDate);
}